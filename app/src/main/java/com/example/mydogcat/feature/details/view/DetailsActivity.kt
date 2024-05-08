package com.example.mydogcat.feature.details.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mydogcat.model.Pet
import com.example.mydogcat.model.PetDetails
import com.example.mydogcat.service.PetDetailsRemoteDataSource
import com.example.mydogcat.ui.theme.CelestialBlue80
import com.example.mydogcat.ui.theme.MyDogCatTheme
import com.example.mydogcat.util.PetDetailsCallback
import com.example.mydogcat.util.ProgressBar
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailsActivity : ComponentActivity(), PetDetailsCallback {

    private var progressIsVisible = mutableStateOf(true)
    private var petDetailsState: MutableState<PetDetails?> = mutableStateOf(null)
    private val dataSource = PetDetailsRemoteDataSource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pet = intent.getBundleExtra("pet")?.getSerializable("pet") as Pet?
        if (pet == null) finish()
        dataSource.findPetDetails(this, pet!!)
        setContent {
            MyDogCatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    MyApp()
                }
            }
        }
    }

    @Composable
    fun MyApp() {
        Column(modifier = Modifier.fillMaxSize()) {
            val imageBitmap: MutableState<ImageBitmap?> = remember { mutableStateOf(null) }
            petDetailsState.value?.let {
                LaunchedEffect(petDetailsState.value) {
                    withContext(Dispatchers.IO) {
                        val bitmap = Picasso.get().load(it.url).get()
                        imageBitmap.value = bitmap.asImageBitmap()
                    }
                }
            }
            ImageHeader(imageBitmap = imageBitmap.value)
            petDetailsState.value?.breeds?.let {
                if (it.isEmpty()) return@let
                Column(modifier = Modifier.fillMaxSize()) {
                    DetailsText(
                        title = "Nome: ",
                        text = it[0].name
                    )
                    DetailsText(
                        title = "Peso: ",
                        text = it[0].weight?.metric + " kg"
                    )
                    DetailsText(
                        title = "Altura: ",
                        text = it[0].height?.metric + "cm"
                    )
                    DetailsText(
                        title = "Temperamento: ",
                        text = it[0].temperament
                    )
                    DetailsText(
                        title = "Criado para: ",
                        text = it[0].bredFor
                    )
                    DetailsText(
                        title = "Grupo de Raça: ",
                        text = it[0].breedGroup
                    )
                    DetailsText(
                        title = "Tempo de vida: ",
                        text = it[0].lifeSpan
                    )
                }
            }
        }
        Box(modifier = Modifier.fillMaxSize()){
            if (progressIsVisible.value) ProgressBar()
        }
    }

    @Composable
    fun ImageHeader(imageBitmap: ImageBitmap?) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4F / 3F)
                .background(
                    color = CelestialBlue80,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 30.dp,
                        bottomEnd = 30.dp
                    )
                )
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp),
                    )
                    .fillMaxSize()
            ) {
                imageBitmap?.let {
                    Image(
                        bitmap = it,
                        contentDescription = "pet image",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(10.dp)
                            .fillMaxSize()
                    )
                }
            }
        }
    }

    @Composable
    fun DetailsText(title: String, text: String?) {
        text?.let {
            Text(
                text = title + text,
                fontSize = 22.sp,
                color = CelestialBlue80,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(
                        top = 5.dp,
                        bottom = 5.dp,
                        start = 20.dp,
                        end = 60.dp
                    )
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DetailsPreview() {
        MyDogCatTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White
            ) {
                MyApp()
            }
        }
    }

    override fun onSuccess(petDetails: PetDetails) {
        petDetailsState.value = petDetails
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, "Erro ao exibir detalhes do pet!", Toast.LENGTH_LONG).show()
    }

    override fun onComplete() {
        progressIsVisible.value = false
    }
}