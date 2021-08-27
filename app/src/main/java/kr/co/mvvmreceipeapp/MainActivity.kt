package kr.co.mvvmreceipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.co.mvvmreceipeapp.ui.theme.MVVMReceipeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMReceipeAppTheme {
                Column {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .border(BorderStroke(width = 1.dp, color = Color.Black)),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Item1",
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = "Item2",
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                    Spacer(modifier = Modifier.size(20.dp))
                    Row(
                        modifier = Modifier
                            .width(200.dp)
                            .height(200.dp)
                            .border(BorderStroke(width = 1.dp, color = Color.Black)),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Item2",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }


//                Column (
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color(0xfff2f2f2))
//                ){
//                    Image(
//                        painterResource(R.drawable.happy_meal_small),
//                        "happy meal",
//                        modifier = Modifier.height(300.dp),
//                        contentScale = ContentScale.Crop
//                    )
//
//                    Column(modifier = Modifier.padding(16.dp)) {
//                        Text(
//                            text = "Happy Meal",
//                            fontSize = 26.sp
//                        )
//                        Spacer(modifier = Modifier.size(10.dp))
//                        Text(
//                            text = "800 Calories",
//                            fontSize = 17.sp
//                        )
//                        Spacer(modifier = Modifier.size(10.dp))
//                        Text(
//                            text = "$5.99",
//                            fontSize = 17.sp,
//                            color = Color(0xff85bb65)
//                        )
//                    }
//                }
            }
        }
    }
}

