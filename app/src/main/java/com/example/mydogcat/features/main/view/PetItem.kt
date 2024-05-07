package com.example.mydogcat.features.main.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.mydogcat.R
import com.example.mydogcat.features.details.view.DetailsActivity
import com.example.mydogcat.model.Pet
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


fun startPetDetailsActivity(context: Context, pet: Pet) {
    val intent = Intent(context, DetailsActivity::class.java)
    val bundle = Bundle()
    bundle.putSerializable("pet", pet)
    intent.putExtra("pet", bundle)
    startActivity(context, intent, null)
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PetItem(context: Context, pet: Pet) {
    val imageBitmap: MutableState<ImageBitmap?> = remember { mutableStateOf(null) }

    LaunchedEffect(pet) {
        val bitmap = withContext(Dispatchers.IO) {
            Picasso.get().load(pet.url).get()
        }
        imageBitmap.value = bitmap.asImageBitmap()
    }

    Box(
        modifier = Modifier
            .width(180.dp)
            .height(250.dp)
            .background(
                color = colorResource(id = R.color.light_cyan),
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        imageBitmap.value?.let {
            Image(
                bitmap = it,
                contentDescription = "id: ${pet.id}",
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .padding(5.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .clickable {
                        startPetDetailsActivity(context, pet)
                    },
                contentScale = ContentScale.Crop,
            )
        }

    }

}