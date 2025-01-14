package com.example.food_delivery_app.core.favorites.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.auth.model.entity.AuthPreferences
import com.example.food_delivery_app.core.components.CustomControl
import com.example.food_delivery_app.core.components.FilledTextButton
import com.example.food_delivery_app.core.components.Filter
import com.example.food_delivery_app.core.components.FoodDeliveryTextField
import com.example.food_delivery_app.core.favorites.viewModel.FavoritesViewModel
import com.example.food_delivery_app.favorites.components.FavoritesResult
import com.example.food_delivery_app.router.Router
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesView(
    navController: NavController,
    favoritesViewModel: FavoritesViewModel
){
    var selectedIndex by remember { mutableStateOf(0) }
    var search by remember { mutableStateOf("") }

    val context = LocalContext.current
    val isLoading by favoritesViewModel.isLoading.collectAsState()
    val error by favoritesViewModel.error.collectAsState()
    val authPreferences = AuthPreferences(context)
    val coroutineScope = rememberCoroutineScope()

    var showFilterState by rememberSaveable { mutableStateOf(false) }
    var cancelShowFilterState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var userId by remember { mutableStateOf<String?>(null) }
    val favoriteRestaurants by favoritesViewModel.favoriteRestaurants.collectAsState(initial = emptyList())
    val favoriteFoods by favoritesViewModel.favoriteFoods.collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        authPreferences.userIdFlow.collect {
            userIdGot ->
            userId = userIdGot
            favoritesViewModel.getFavoriteRestaurants(userId!!.toInt())
        }
    }


    LaunchedEffect(selectedIndex) {
        if (selectedIndex != 0) {
            favoritesViewModel.getFavoriteFoods(userId!!.toInt())
        } else {
            favoritesViewModel.getFavoriteRestaurants(userId!!.toInt())
        }
    }


    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else if ( favoriteRestaurants.isNotEmpty() || favoriteFoods.isNotEmpty() ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(48.dp)
            ) {

                CustomControl(
                    options = listOf(
                        stringResource(R.string.restaurants),
                        stringResource(R.string.foods)
                    ),
                    selectedIndex = selectedIndex,
                    onOptionSelected = {
                        selectedIndex = it
                    }
                )

                FoodDeliveryTextField(
                    value = search,
                    onValueChange = { search = it },
                    placeHolderText = stringResource(R.string.input_search_restaurant),
                    leadingIconVector = Icons.Default.Search,
                    trailingIconId = R.drawable.ri_equalizer_fill,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = LocalCustomColorScheme.current.primary500,
                        unfocusedIndicatorColor = LocalCustomColorScheme.current.primary500,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        focusedLeadingIconColor = LocalCustomColorScheme.current.primary500,
                        unfocusedLeadingIconColor = LocalCustomColorScheme.current.primary500,
                        focusedTrailingIconColor = LocalCustomColorScheme.current.primary500,
                        unfocusedTrailingIconColor = LocalCustomColorScheme.current.primary500,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    changeShowFilterState = {showFilterState = !showFilterState}
                )
                FavoritesResult(
                    modifier = Modifier.padding(16.dp),
                    selectedIndex = selectedIndex,
                    favoriteFoods = favoriteFoods,
                    favoriteRestaurants = favoriteRestaurants,
                    navController = navController
                )
            }

            if (showFilterState) {
                ModalBottomSheet(
                    sheetState = cancelShowFilterState,
                    onDismissRequest = {
                        coroutineScope.launch { cancelShowFilterState.hide() }.invokeOnCompletion {
                            if (!cancelShowFilterState.isVisible) {
                                showFilterState = false
                            }
                        }
                    },
                ) {
                    Filter(
                        onDismiss = {
                            coroutineScope
                                .launch { cancelShowFilterState.hide() }
                                .invokeOnCompletion {
                                    if (!cancelShowFilterState.isVisible) {
                                        showFilterState = false
                                    }
                                }
                        }
                    )
                }
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.75f)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CustomControl(
                    options = listOf(
                        stringResource(R.string.restaurants),
                        stringResource(R.string.foods)
                    ),
                    selectedIndex = selectedIndex,
                    onOptionSelected = {
                        selectedIndex = it
                    }
                )

                Spacer(modifier = Modifier.weight(3f))

                Image(
                    painter = painterResource(R.drawable.wavy_buddies_preparing_your_food_without_bg),
                    contentDescription = "Empty Body",
                    modifier = Modifier
                        .height(220.dp)
                        .padding(20.dp)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.FillHeight

                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = stringResource(R.string.favorites_empty_title),
                    style = LocalCustomTypographyScheme.current.heading3,
                    color = LocalCustomColorScheme.current.ink500,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text =
                    if(selectedIndex == 0)
                        stringResource(R.string.favorites_empty_restaurants_subtitle)
                    else
                        stringResource(R.string.favorites_empty_foods_subtitle),
                    style = LocalCustomTypographyScheme.current.p_medium,
                    color = LocalCustomColorScheme.current.ink400,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.weight(1f))

                FilledTextButton(
                    onClick = {
                        // Navigate to home
                        navController.navigate(Router.HomeScreen.route)
                    },
                    textContent = stringResource(R.string.cta_back_home),
                    textStyle = LocalCustomTypographyScheme.current.p_largeBold
                )
            }
        }
    }
}