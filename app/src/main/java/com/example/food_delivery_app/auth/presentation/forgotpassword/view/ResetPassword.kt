package auth.presentation.forgotpassword.view

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
import androidx.navigation.NavHostController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.auth.presentation.components.BackUpBar
import com.example.food_delivery_app.auth.presentation.components.CheckBoxBtn
import com.example.food_delivery_app.auth.presentation.components.ResetPasswordSuccessBox
import com.example.food_delivery_app.components.*
import com.example.food_delivery_app.navigation.Screen
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme


@Composable
fun ResetPassword(
    navController: NavController
) {
    val context = LocalContext.current

    var password by remember { mutableStateOf("") }
    var confirmationPassword by remember { mutableStateOf("") }
    var success by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 24.dp),
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
                .height(250.dp)
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
                    success = true

                } else {
                    Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            }
        )

        if (success) {
            ResetPasswordSuccessBox {
                navController.navigate(Screen.Login.route)
            }
        }

    }
}