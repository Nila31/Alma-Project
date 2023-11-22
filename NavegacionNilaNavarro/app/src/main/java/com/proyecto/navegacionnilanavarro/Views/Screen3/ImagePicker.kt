package com.proyecto.navegacionnilanavarro.Views.Screen3

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.util.Date
import java.util.Locale
import java.util.jar.Manifest

@Composable
fun ImagePicker(
    onImageSelected: (Uri) -> Unit
) {
    val context = LocalContext.current
    val getContent = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            onImageSelected(it) // Llama a la función onImageSelected con la URI seleccionada
        }
    }

    val takePicture = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        if (bitmap != null) {
            try {
                val uri = createImageUri(context)
                val outputStream = context.contentResolver.openOutputStream(uri)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
                outputStream?.close()
                onImageSelected(uri) // Llama a la función onImageSelected con la URI de la imagen capturada
            } catch (e: Exception) {
                Log.e("ImagePicker", "Error al capturar la imagen de la cámara: ${e.message}")
                // Manejar el error aquí, mostrar un mensaje al usuario, etc.
            }
        }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            takePicture.launch(null)
        } else {
            // Permiso denegado, manejarlo según sea necesario
            Log.e("ImagePicker", "Permiso de cámara denegado")
        }
    }

    ImagePickerDialog(
        onCameraSelected = {
            Log.d("ImagePicker", "Seleccionado: Cámara")
            cameraPermissionLauncher.launch("android.permission.CAMERA")
        },
        onGallerySelected = {
            Log.d("ImagePicker", "Seleccionado: Galería")
            getContent.launch("image/*")
        }
    )
}

private fun createImageUri(context: Context): Uri {
    val imagesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val imageFileName = "JPEG_${timeStamp}_"
    val storageDir = File(imagesDir, "images")
    storageDir.mkdirs()
    val imageFile = File.createTempFile(imageFileName, ".jpg", storageDir)
    return FileProvider.getUriForFile(context, context.packageName + ".provider", imageFile)
}