package com.example.mydogcat.feature.main.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mydogcat.R
import com.example.mydogcat.feature.about.view.AboutActivity
import com.example.mydogcat.feature.main.reducer.MainReducer
import com.example.mydogcat.feature.main.viewModel.MainViewModel
import com.example.mydogcat.ui.theme.CelestialBlue80
import com.example.mydogcat.ui.theme.MyDogCatTheme
import com.example.mydogcat.util.PetRecyclerView
import com.example.mydogcat.util.ProgressBar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.findAllPets()
        setContent {
            MyDogCatTheme {
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
        Box(modifier = Modifier.fillMaxSize()) {
            if(viewModel.state.value.isProgress) ProgressBar()
            if(viewModel.state.value.isFailure) {
                Toast.makeText(this@MainActivity,viewModel.state.value.errorMessage, Toast.LENGTH_LONG).show()
            }
            Image(
                painter = painterResource(id = R.drawable.info),
                modifier = Modifier
                    .clickable {
                        startActivity(Intent(this@MainActivity, AboutActivity::class.java))
                    }
                    .align(Alignment.TopEnd)
                    .padding(20.dp),
                contentDescription = "info"
            )
            Text(
                text = "MyDogCat",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 20.dp),
                color = CelestialBlue80,
                fontSize = 60.sp,
                fontFamily = FontFamily.Cursive
            )
            Row {
                PetRecyclerView(
                    context = this@MainActivity,
                    petsState = viewModel.state.value.dogs,
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                    xOffset = 13.dp,
                    yOffset = 120.dp
                )
                Spacer(modifier = Modifier.width(40.dp))
                PetRecyclerView(
                    context = this@MainActivity,
                    petsState = viewModel.state.value.cats,
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                    xOffset = (-13).dp,
                    yOffset = (120).dp
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun MainPreview() {
        MyDogCatTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White
            ) {
                MyApp()
            }
        }
    }

}