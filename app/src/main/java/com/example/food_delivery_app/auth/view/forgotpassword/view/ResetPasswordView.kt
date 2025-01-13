package auth.presentation.forgotpassword.view

import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.auth.viewModel.AuthViewModel
import com.example.food_delivery_app.auth.view.components.BackUpBar
import com.example.food_delivery_app.auth.view.components.ResetPasswordSuccessBox
import com.example.food_delivery_app.core.components.ButtonIcon
import com.example.food_delivery_app.core.components.FilledTextButton
import com.example.food_delivery_app.core.components.IconType
import com.example.food_delivery_app.core.components.PasswordTextField
import com.example.food_delivery_app.router.Router
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme
import com.example.food_delivery_app.utils.Resource
import kotlinx.coroutines.launch


@Composable
fun ResetPasswordView(
    navController: NavController,
    authViewModel: AuthViewModel,
    email: String
) {

    var password by remember { mutableStateOf("") }
    var confirmationPassword by remember { mutableStateOf("") }
    var success by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val verificationState = authViewModel.passwordResetState.collectAsState()
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(verificationState.value) {
        when (val state = verificationState.value) {
            is Resource.Loading -> {
                isLoading = true
            }
            is Resource.Success -> {
                success = true
            }
            is Resource.Error -> {
                isLoading = false
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            else -> {
                isLoading = false
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        BackUpBar(
            title = stringResource(R.string.reset_password_title),
            navController = navController
        )

        Image(
            painter = painterResource(id = R.drawable.password),
            contentDescription = stringResource(R.string.reset_password_title),
            modifier = Modifier
                .height(200.dp)
                .padding(20.dp)
                .align(Alignment.CenterHorizontally)
        )



        Column{
            Text(
                text = stringResource(R.string.reset_password_description),
                style = LocalCustomTypographyScheme.current.p_large,
            )
            Spacer(modifier =  Modifier.height(32.dp))
            PasswordTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                placeholder = stringResource(R.string.label_new_password),
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier =  Modifier.height(16.dp))
            PasswordTextField(
                value = confirmationPassword,
                onValueChange = {
                    confirmationPassword = it
                },
                placeholder = stringResource(R.string.label_confirm_new_password),
                modifier = Modifier.fillMaxWidth(),
            )
        }

        FilledTextButton(
            textContent = stringResource(R.string.reset_password_title),
            textStyle = LocalCustomTypographyScheme.current.p_mediumBold,
            modifier = Modifier.fillMaxWidth(),
            icon = ButtonIcon.Right(
                IconType.VectorIcon(
                    imageVector = Icons.Default.ArrowForward,
                    iconDescription = "Arrow Forward"
                )
            ),
            onClick = {
                if (password == confirmationPassword) {
                    coroutineScope.launch {
                        authViewModel.updatePassword(
                            email= email,
                            password = password
                        )
                    }

                } else {
                    Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            }
        )

        if (success) {
            ResetPasswordSuccessBox {
                navController.navigate(Router.LoginScreen.route)
            }
        }

    }
}