package com.example.food_delivery_app.auth.presentation.components

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.auth.domain.AuthState
import com.example.food_delivery_app.auth.domain.AuthViewModel
import com.example.food_delivery_app.components.ButtonIcon
import com.example.food_delivery_app.components.GhostTextButton
import com.example.food_delivery_app.components.IconType
import com.example.food_delivery_app.navigation.Screen
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.*
import java.io.IOException

@Composable
fun OAuthSection(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // Collect the auth state
    val authState by authViewModel.authState.collectAsState()

    var googleId: String? = null

    // Initialize Firebase Auth
    val firebaseAuth = remember { FirebaseAuth.getInstance() }

    // Configure Google Sign-In
    val googleSignInClient = remember {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        GoogleSignIn.getClient(context, gso)
    }

    // Handle sign-in result
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.let {
                    googleId = account.id

                    // Get credential and sign in to Firebase
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    firebaseAuth.signInWithCredential(credential)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val user = firebaseAuth.currentUser
                                Log.d("GoogleSignIn", "Google ID: $googleId")

                                // Call the ViewModel's googleLogin method
                                googleId?.let { id ->
                                    authViewModel.googleLogin(id, context)
                                }

                            } else {
                                Log.w("FirebaseAuth", "signInWithCredential:failure", task.exception)
                                Toast.makeText(
                                    context,
                                    "Authentication failed: ${task.exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            } catch (e: ApiException) {
                Log.w("GoogleSignIn", "Google sign in failed", e)
                Toast.makeText(
                    context,
                    "Google Sign-In failed: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // Show loading state if necessary
    if (authState is AuthState.Loading) {
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp),
            color = LocalCustomColorScheme.current.ink500
        )
    }

    // Handle auth state changes
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Success -> {
                navController.navigate(Screen.EditProfileView.route)
            }
            is AuthState.Error -> {
                Toast.makeText(
                    context,
                    (authState as AuthState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {}
        }
    }

    GhostTextButton(
        onClick = {
            val signInIntent = googleSignInClient.signInIntent
            launcher.launch(signInIntent)
        },
        textContent = stringResource(R.string.cta_login_with_google),
        icon = ButtonIcon.Left(
            IconType.PainterIcon(
                painter = painterResource(R.drawable.google),
                iconDescription = "Google icon",
            )
        ),
        modifier = Modifier.fillMaxWidth(),
        textStyle = LocalCustomTypographyScheme.current.p_mediumBold,
        contentColor = LocalCustomColorScheme.current.ink500,
        borderColor = LocalCustomColorScheme.current.ink100
    )
}