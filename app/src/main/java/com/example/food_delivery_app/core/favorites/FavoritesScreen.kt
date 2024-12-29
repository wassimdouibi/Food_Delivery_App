package com.example.food_delivery_app.core.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.components.BottomBar
import com.example.food_delivery_app.components.CustomControl
import com.example.food_delivery_app.components.Filter
import com.example.food_delivery_app.components.FoodDeliveryTextField
import com.example.food_delivery_app.favorites.components.FavoritesResult
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    navController: NavHostController,
){
    var selectedIndex by remember { mutableStateOf(0) }
    var search by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    var showFilterState by rememberSaveable { mutableStateOf(false) }
    var cancelShowFilterState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp),
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
                modifier = Modifier.fillMaxWidth(.9f),
                changeShowFilterState = {showFilterState = !showFilterState}
            )

            FavoritesResult(
                selectedIndex = selectedIndex
            )
        }

        BottomBar(
            navController = navController,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )

        if (showFilterState) {
            ModalBottomSheet(
                sheetState = cancelShowFilterState,
                onDismissRequest = {
                    scope.launch { cancelShowFilterState.hide() }.invokeOnCompletion {
                        if (!cancelShowFilterState.isVisible) {
                            showFilterState = false
                        }
                    }
                },
            ) {
                Filter(
                    onDismiss = {
                        scope
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
}