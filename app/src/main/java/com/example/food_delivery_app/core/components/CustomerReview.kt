package com.example.food_delivery_app.core.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.food_delivery_app.auth.model.entity.AuthState
import com.example.food_delivery_app.core.home.model.entity.Review
import com.example.food_delivery_app.core.profile.viewmodel.ProfileViewModel
import com.example.food_delivery_app.ui.theme.Colors.defaultCustomColorScheme
import com.example.food_delivery_app.ui.theme.Typography.defaultCustomTypographyScheme


@Composable
fun CustomerReview(
     customerReview: Review,
     profileViewModel: ProfileViewModel,
     modifier : Modifier = Modifier
          .fillMaxWidth()
          .border(1.dp, defaultCustomColorScheme.ink200)
          .padding(16.dp)
) {

     val usernameFont = defaultCustomTypographyScheme.p_medium
     val bodyFont = defaultCustomTypographyScheme.p_small
     val usernameFontColor = defaultCustomColorScheme.ink500
     val bodyFontColor = defaultCustomColorScheme.ink400

     val userFields by profileViewModel.userFields.collectAsState()
     val isLoading by profileViewModel.isLoading.collectAsState()

     LaunchedEffect(1){
          profileViewModel.getUserFields(customerReview.userId.toString())
     }

     if (isLoading){
          CircularProgressIndicator()
     } else
     {
          Column(
               modifier = modifier
          ) {
               Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
               ) {
                    Column {
                         Spacer(modifier = Modifier.height(4.dp))

                         Text(
                              text = userFields?.name ?: "full name",
                              style = usernameFont,
                              color = usernameFontColor
                         )

                         Spacer(modifier = Modifier.height(8.dp))

                         Text(
                              text = customerReview.title ?: "Title",
                              style = bodyFont,
                              color = bodyFontColor
                         )
                    }

                    StarRating(rating = customerReview.rating)
               }



               Spacer(modifier = Modifier.height(12.dp))

               Text(
                    text = customerReview.reviewText ?: "description",
                    style = bodyFont,
                    color = bodyFontColor ,
                    lineHeight = 20.sp,
                    textAlign = TextAlign.Justify
               )
          }
     }
}


@Composable
fun StarRating(rating: Int, maxRating: Int = 5) {
     val starColor = defaultCustomColorScheme.utilityWarning
     Row(
          verticalAlignment = Alignment.CenterVertically
     ) {
          for (i in 1..maxRating) {
               Icon(
                    imageVector = if (i <= rating) Icons.Default.Star else Icons.Default.StarBorder,
                    contentDescription = if (i <= rating) "Filled Star" else "Empty Star",
                    tint = starColor, // star's color
                    modifier = Modifier.size(24.dp)
               )
          }
     }
}
