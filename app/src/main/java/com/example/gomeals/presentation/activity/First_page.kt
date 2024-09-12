package com.example.gomeals.presentation.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gomeals.R
import com.example.gomeals.databinding.ActivityFirstPageBinding
import com.example.gomeals.presentation.theme.blackBg
import com.example.gomeals.presentation.theme.brownishColor
import com.example.gomeals.presentation.theme.lightSkinColor
import com.example.gomeals.presentation.theme.skinColor

class first_page : ComponentActivity() {
    lateinit var binding: ActivityFirstPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstPageBinding.inflate(layoutInflater)
        setContent {
            FirstPageScreen{navigateToLogin()}

        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, Login_page::class.java)
        startActivity(intent)
        finish()
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FirstPageScreen(onNextClick: () -> Unit) {
    // Layout for the First Page Screen
    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(blackBg),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.first_logo),
                    contentDescription = "Logo C" +
                            "chef",
                    Modifier.padding(top = 100.dp, start = 20.dp, end = 20.dp)
                )
                Text(
                    text = "We Go Miles to Deliver Meals",
                    modifier = Modifier.padding(
                        top = 40.dp,
                        bottom = 20.dp,
                        start = 20.dp,
                        end = 20.dp
                    ),
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center,
                    color = Color.White

                )
                Spacer(modifier = Modifier.weight(1f)) // Push content to the top
                    Button(
                        onClick = { onNextClick() },
                        modifier = Modifier
                            .padding(bottom = 40.dp, start = 20.dp, end = 20.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                               backgroundColor = brownishColor
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(text = "Next",color = Color.White, fontFamily = FontFamily.SansSerif)
                    }
                }
        }
    )
}

@Composable
@Preview
fun Show() {
    FirstPageScreen {

    }

}

