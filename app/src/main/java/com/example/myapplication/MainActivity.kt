package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CharacterSheetScreen()
        }
    }
}

@Composable
fun CharacterSheetScreen() {

    var vit by remember { mutableIntStateOf(10) }
    var dex by remember { mutableIntStateOf(10) }
    var wis by remember { mutableIntStateOf(10) }

    val total = vit + dex + wis

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = "Character Stats",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        StatRow("Vitality", vit) {
            vit = (3..18).random()
        }

        StatRow("Dexterity", dex) {
            dex = (3..18).random()
        }

        StatRow("Wisdom", wis) {
            wis = (3..18).random()
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Total Score: $total",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        when {
            total < 30 -> {
                Text(
                    text = "Re-roll recommended!",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }

            total >= 50 -> {
                Text(
                    text = "Godlike!",
                    color = Color(0xFFFFD700)
                )
            }
        }
    }
}

@Composable
fun StatRow(
    name: String,
    value: Int,
    onRoll: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = value.toString(),
                fontSize = 18.sp
            )

            Button(onClick = onRoll) {
                Text("Roll")
            }
        }
    }
}
