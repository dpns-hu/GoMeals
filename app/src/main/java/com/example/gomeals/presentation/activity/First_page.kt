package com.example.gomeals.presentation.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gomeals.R
import com.example.gomeals.databinding.ActivityFirstPageBinding

class first_page : ComponentActivity() {
    lateinit var binding: ActivityFirstPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstPageBinding.inflate(layoutInflater)
        setContent {
            Show()

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
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_chef),
                    contentDescription = "Logo C" +
                            "chef"
                )
                Text(
                    text = "Welcome to the First Page!",
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Button(onClick = { onNextClick() }) {
                    Text("Next")
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

