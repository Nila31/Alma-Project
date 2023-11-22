package com.proyecto.navegacionnilanavarro
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.proyecto.navegacionnilanavarro.Repositorios.DiaryRepository
import com.proyecto.navegacionnilanavarro.Routes.alma
import com.proyecto.navegacionnilanavarro.ViewModel.ChatViewModel
import com.proyecto.navegacionnilanavarro.ViewModel.DiaryViewModel
import com.proyecto.navegacionnilanavarro.ViewModel.MiPantallaViewModel
import com.proyecto.navegacionnilanavarro.ViewModel.PendientesViewModel
import com.proyecto.navegacionnilanavarro.ViewModel.ReminderViewModel
import com.proyecto.navegacionnilanavarro.ViewModel.TicTacToeViewModel
import com.proyecto.navegacionnilanavarro.ViewModel.UserViewModel
import com.proyecto.navegacionnilanavarro.Views.Happy
import com.proyecto.navegacionnilanavarro.Views.Login.HomeScreen
import com.proyecto.navegacionnilanavarro.Views.Login.RegistrationScreen
import com.proyecto.navegacionnilanavarro.Views.Screen5.ReminderForm
import com.proyecto.navegacionnilanavarro.Views.Screen1.MenuScreen
import com.proyecto.navegacionnilanavarro.Views.Screen10.MusicScreen
import com.proyecto.navegacionnilanavarro.Views.Screen2.ListadoFundaciones
import com.proyecto.navegacionnilanavarro.Views.Screen3.ProfileDetailsScreen
import com.proyecto.navegacionnilanavarro.Views.Screen4.SleepBetterScreen
import com.proyecto.navegacionnilanavarro.Views.Screen6.AgregarPendientesScreen
import com.proyecto.navegacionnilanavarro.Views.Screen6.VerPendientesScreen
import com.proyecto.navegacionnilanavarro.Views.Screen7.PoliticaSeguridadScreen
import com.proyecto.navegacionnilanavarro.Views.Screen8.WeeklyPlansScreen
import com.proyecto.navegacionnilanavarro.Views.Screen9.AlimentacionBalanceadaScreen
import com.proyecto.navegacionnilanavarro.Views.Screen9.FrutasYVerdurasScreen
import com.proyecto.navegacionnilanavarro.Views.Screen9.FuentesDeFibraScreen
import com.proyecto.navegacionnilanavarro.Views.Screen9.GrasasSaludablesScreen
import com.proyecto.navegacionnilanavarro.Views.Screen9.HidratacionAdecuadaScreen
import com.proyecto.navegacionnilanavarro.Views.Screen9.ProteinasScreen
import com.proyecto.navegacionnilanavarro.Views.Splash.HeartbeatAnimation
import com.proyecto.navegacionnilanavarro.Views.Welcome.WelcomeScreen

class MainActivity : ComponentActivity() {
    private val viewModel: MiPantallaViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()
    private val diaryViewModel: DiaryViewModel by viewModels()
    private val reminderViewModel: ReminderViewModel by viewModels()
    private val pendientesViewModel =
        PendientesViewModel() // Mover aquí la instancia de PendientesViewModel
    private val diaryRepository = DiaryRepository()
    private val ticTacToeViewModel: TicTacToeViewModel by viewModels()
    private val ChatViewModel: ChatViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // El token se obtuvo con éxito
                    val token = task.result
                    Log.d("TokenRegistro", "Token de registro: $token")
                } else {
                    // Hubo un error al obtener el token
                    Log.e("TokenRegistro", "Error al obtener el token: ${task.exception}")
                }
            }

        setContent {
            val navController = rememberNavController()

            // Obtener el ID del usuario activo después de iniciar sesión correctamente
            val currentUser = FirebaseAuth.getInstance().currentUser
            val userId = currentUser?.uid ?: ""

            // Obtener la lista de pendientes desde Firestore
            LaunchedEffect(Unit) {
                pendientesViewModel.obtenerListaDePendientes()
            }

            NavHost(navController = navController, startDestination = "heart") {
                composable("heart") {
                    HeartbeatAnimation(navController = navController)
                }
                composable("home") {
                    HomeScreen(navController = navController)
                }
                composable("registro") {
                    RegistrationScreen(navController = navController)

                }
                composable("menu") {
                    MenuScreen(navController = navController, viewModel = viewModel)
                }
                composable("listadoFundaciones") {
                    ListadoFundaciones(navController = navController, viewModel = viewModel)
                }
                composable("SleepBetterScreen") {
                    SleepBetterScreen(navController = navController)
                }
                composable(Routes.ReminderForm) {
                    ReminderForm(
                        navController = navController,
                        viewModel = reminderViewModel, // Cambiar ReminderViewModel por la instancia que creaste en MainActivity
                        userId = userId
                    )
                }
                composable(Routes.AgregarPendientesScreen) {
                    AgregarPendientesScreen(
                        navController = navController,
                        viewModel = pendientesViewModel,
                        userId = userId
                    )
                }
                // Ruta para VerPendientesScreen
                composable("verpendientes") {
                    VerPendientesScreen(
                        navController = navController,
                        viewModel = pendientesViewModel
                    )
                }
                composable("profile") {
                    ProfileDetailsScreen(navController = navController, viewModel = userViewModel)
                }
                composable("politica") {
                    PoliticaSeguridadScreen(navController = navController)
                }
                composable("WeeklyPlansScreen") {
                    Log.d("Navigation", "Navigating to WeeklyPlansScreen")
                    WeeklyPlansScreen(navController = navController)
                }
                composable("AlimentacionBalanceadaScreen") {
                    Log.d("Navigation", "Navigating to AlimentacionBalanceadaScreen")
                    AlimentacionBalanceadaScreen(navController = navController)
                }
                composable("ProteinasScreen") {
                    Log.d("Navigation", "Navigating to ProteinasScreen")
                    ProteinasScreen(navController = navController)
                }

                composable(Routes.ProteinasScreen) {
                    ProteinasScreen(navController = navController)
                }
                composable(Routes.FrutasYVerdurasScreen) {
                    FrutasYVerdurasScreen(navController = navController)
                }
                composable(Routes.FuentesDeFibraScreen) {
                    FuentesDeFibraScreen(navController = navController)
                }
                composable(Routes.GrasasSaludablesScreen) {
                    GrasasSaludablesScreen(navController = navController)
                }
                composable(Routes.HidratacionAdecuadaScreen) {
                    HidratacionAdecuadaScreen(navController = navController)
                }
                composable(Routes.MusicScreen) {
                    MusicScreen(navController = navController)

                }
                composable(Routes.WelcomeScreen) {
                    WelcomeScreen(navController = navController, viewModel = userViewModel)
                }
                composable(Routes.Happy) {
                    Happy(navController = navController)
                }

                composable(Routes.DiaryScreen) {
                    DiaryScreen(navController = navController)
                }
                composable(Routes.DiaryScreen) {
                    DiaryScreen(navController = navController)
                }

                composable(Routes.WriteDiaryEntry) {
                    WriteDiaryEntry(
                        navController = navController,
                        viewModel = diaryViewModel
                    ) // Asegúrate de pasar el ViewModel necesario
                }
                composable("diaryEntryDatesList") {
                    DiaryEntryDatesList(
                        navController = navController,
                        diaryRepository = diaryRepository
                    )
                }
                composable("diaryEntryDetail/{date}") { backStackEntry ->
                    val date = backStackEntry.arguments?.getString("date")
                    DiaryEntryDetail(
                        navController = navController,
                        date = date.orEmpty(),
                        viewModel = diaryViewModel
                    )
                }
                composable(Routes.NerviososScreen) {
                    NerviososScreen(navController = navController) // Asegúrate de pasar el ViewModel necesario
                }
                composable(Routes.alma) {
                    alma(navController = navController) // Asegúrate de pasar el ViewModel necesario
                }
                composable(Routes.BreathingExercise) {
                    BreathingExercise(navController = navController) // Asegúrate de pasar el ViewModel necesario
                }
                composable("TicTacToeScreen") {
                    TicTacToeScreen(navController = navController, viewModel = ticTacToeViewModel)
                }

                composable(Routes.ColorMatchingGame) {
                    ColorMatchingGame(navController = navController) // Asegúrate de pasar el ViewModel necesario
                }
                composable(Routes.ColorMatchingGameInstructions) {
                    ColorMatchingGameInstructions(navController = navController) // Asegúrate de pasar el ViewModel necesario
                }
                composable(Routes.BoringScreen) {
                    BoringScreen(navController = navController) // Asegúrate de pasar el ViewModel necesario
                }
                composable("ChatScreen") {
                    ChatScreen(navController = navController, chatViewModel = ChatViewModel) // Asegúrate de pasar el ViewModel necesario
                }
                composable("SmartReplyDemo") {
                    SmartReplyDemo(navController = navController) // Asegúrate de pasar el ViewModel necesario
                }


            }
        }
    }
}