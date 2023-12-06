package se.magictechnology.intromlkit

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.biometrics.BiometricManager
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import se.magictechnology.intromlkit.ui.theme.FancyViewMode
import se.magictechnology.intromlkit.ui.theme.IntroMLKitTheme



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntroMLKitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                       ui()


                    }
                }
            }
        }
    }

    @Composable
    fun ui(){
        val viewModel = FancyViewMode(context = LocalContext.current)

        val result: List<String> by viewModel.result.observeAsState(emptyList())

        Column (verticalArrangement = Arrangement.Center){

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {


                Column(verticalArrangement = Arrangement.Center) {

                    Image(
                        painter = painterResource(id = R.drawable.fanta_logo) ,
                        contentDescription = "Fanta logo",
                        modifier = Modifier.size(120.dp)
                        )

                    Button(onClick = { viewModel.runTextRecognition(R.drawable.fanta_logo) }) {
                        Text(text = "Fanta")
                    }
                }
                Column(verticalArrangement = Arrangement.Center) {

                    Image(
                        painter = painterResource(id = R.drawable.pepsilogo) ,
                        contentDescription = "pepsi logo",
                        modifier = Modifier.size(120.dp)
                    )
                    
                    Button(onClick = { viewModel.runTextRecognition(R.drawable.pepsi_logo) }) {
                        Text(text = "Pepsi")
                    }
                }
                Column(verticalArrangement = Arrangement.Center) {

                    Image(
                        painter = painterResource(id = R.drawable.cuba_cola_logo) ,
                        contentDescription = "cuba cola logo",
                        modifier = Modifier.size(120.dp)
                    )
                    Button(onClick = { viewModel.runTextRecognition(R.drawable.cuba_cola_logo)}) {
                        Text(text = "Cuba Cola")
                    }
                }



            }

            LazyColumn {
                items(result) { text ->
                    Text(text = text)
                }
            }
        }
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )


    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntroMLKitTheme {
        Greeting("Android")
    }
}

