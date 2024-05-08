package com.example.mydogcat.feature.splash.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mydogcat.R
import com.example.mydogcat.feature.main.view.MainActivity
import com.example.mydogcat.ui.theme.MyDogCatTheme
import com.example.mydogcat.util.ProgressBar

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDogCatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SplashScreen()
                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    },2000)
                }
            }
        }
    }

    @Composable
    fun SplashScreen() {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.celestial_blue))
        ) {
            Image(
                painter = painterResource(id = R.drawable.dog_and_cat_icon),
                contentDescription = "Image for cat and dog",
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = (-80).dp)
            )
            ProgressBar(yOffset = (180).dp)
            Text(
                fontSize = (20).sp,
                color = Color.Black,
                text = "Powered by H. Jordan",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding((20).dp)
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun SplashPreview() {
        MyDogCatTheme {
            Surface(modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Cyan)
            ) {
                SplashScreen()
            }
        }
    }
}