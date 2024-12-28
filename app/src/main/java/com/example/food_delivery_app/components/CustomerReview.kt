package com.example.food_delivery_app.components

import android.graphics.Picture
import android.provider.Telephony.Mms.Rate
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.food_delivery_app.ui.theme.defaultCustomColorScheme
import com.example.food_delivery_app.ui.theme.defaultCustomTypographyScheme

//takes : profil picture - customer name - stars rate - review title - review paragraph

@Composable
fun CustomerReview(
     imageResource: Int,
     username: String,
     reviewTitle: String,
     rating: Int,
     reviewText: String,
     modifier : Modifier = Modifier.padding(16.dp)
          .fillMaxWidth()
          .border(1.dp, defaultCustomColorScheme.ink200)
          .padding(16.dp)
) {

     val usernameFont = defaultCustomTypographyScheme.p_medium
     val bodyFont = defaultCustomTypographyScheme.p_small
     val usernameFontColor = defaultCustomColorScheme.ink500
     val bodyFontColor = defaultCustomColorScheme.ink400

     Column(
          modifier = modifier
     ) {
          Row(verticalAlignment = Alignment.Top) {
               ProfilPicture(imageRes = imageResource)

               Spacer(modifier = Modifier.width(8.dp))

               Column {
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                         text = username,
                         style = usernameFont,
                         color = usernameFontColor
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                         text = reviewTitle,
                         style = bodyFont,
                         color = bodyFontColor
                    )
               }
               Spacer(modifier = Modifier.width(8.dp))

               StarRating(rating = rating)
          }



          Spacer(modifier = Modifier.height(8.dp))

          Text(
               text = reviewText,
               style = bodyFont,
               color = bodyFontColor ,
               lineHeight = 20.sp,
               textAlign = TextAlign.Justify
          )
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

@Composable
fun ProfilPicture(imageRes: Int) {
     Image(
          painter = painterResource(imageRes),
          contentDescription = "Profile picture",
          contentScale = ContentScale.Crop,
          modifier = Modifier
               .size(54.dp)
               .clip(CircleShape) // Clip to the circle shape
               .border(5.dp, Color.Transparent, CircleShape) // Optional
     )
}