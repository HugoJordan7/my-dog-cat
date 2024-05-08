package com.example.mydogcat.util

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mydogcat.model.Pet
import com.example.mydogcat.feature.main.view.PetItem

@Composable
fun ProgressBar(xOffset: Dp = 0.dp, yOffset: Dp = 0.dp){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .offset(x = xOffset, y = yOffset),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = Color.Black,
            strokeWidth = 4.dp,
        )
    }
}

@Composable
fun PetRecyclerView(
    context: Context,
    petsState: State<List<Pet>>,
    verticalArrangement: Arrangement.Vertical,
    horizontalAlignment: Alignment.Horizontal,
    xOffset: Dp = 0.dp, yOffset: Dp = 0.dp
){
    LazyColumn(
        modifier = Modifier
            .offset(x =  xOffset, y = yOffset)
            .padding(0.dp,0.dp,0.dp,130.dp),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    ) {
        items(petsState.value){ pet ->
            PetItem(context = context,pet = pet)
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}