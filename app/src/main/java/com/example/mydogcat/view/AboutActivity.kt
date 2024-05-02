package com.example.mydogcat.view

import android.content.Intent
import android.graphics.Paint.Style
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.mydogcat.R
import com.example.mydogcat.view.ui.theme.MyDogCatTheme
import com.squareup.picasso.BuildConfig

class AboutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            Image(
                painter = painterResource(id = R.drawable.dog_and_cat_icon),
                contentDescription = "Image for cat and dog",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .offset(y = (80).dp)
            )
            Text(
                text = "MyDogCat",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .offset(y = 80.dp),
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "MyDogCat é um app que exibe fotos de cachorros e gatos aleatórios, é possível obter mais informações sobre cada pet ao clicar em sua imagem, confira o projeto no github:",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .offset(y = 80.dp)
                    .padding(30.dp, 20.dp, 30.dp, 0.dp),
                color = Color.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            val annotatedString = buildAnnotatedString {
                append("https://github.com/HugoJordan7/MyDogCat")
            }
            ClickableText(
                text = annotatedString,
                style = TextStyle(
                    color = Color.Blue,
                    fontSize = 16.sp,
                ),
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally)
                    .offset(y = 100.dp),
                onClick = { offset ->
                    val url = "https://github.com/HugoJordan7/MyDogCat"
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                    startActivity(this@AboutActivity, intent, null)
                }
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        MyDogCatTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                MyApp()
            }
        }
    }
}