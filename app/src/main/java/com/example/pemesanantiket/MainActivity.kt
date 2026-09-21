package com.example.pemesanantiket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale

const val HARGA_TIKET = 25000

fun formatRupiah(nilai: Int): String {
    val angka = NumberFormat.getNumberInstance(Locale("id", "ID")).format(nilai)
    return "Rp$angka"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PemesananTiketScreen()
            }
        }
    }
}

@Composable
fun PemesananTiketScreen() {
    // State: jumlah tiket yang dibeli
    var jumlah by remember { mutableStateOf(1) }
    val total = HARGA_TIKET * jumlah

    val biru = Color(0xFF0A2342)
    val hijau = Color(0xFF15803D)
    val orange = Color(0xFFff6a00)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FB))
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(biru)
                .statusBarsPadding()
                .padding(vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("🎫", fontSize = 48.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Pemesanan Tiket",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Pesan tiket dengan mudah!",
                color = Color.White,
                fontSize = 16.sp
            )
        }

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Card Harga Tiket
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = biru
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Harga Tiket", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFFe9eef5))
                    Text(
                        text = formatRupiah(HARGA_TIKET),
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text("per tiket", fontSize = 16.sp, color = Color.Gray)
                }
            }

            // Card Jumlah Tiket
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = biru)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Jumlah Tiket", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color (0xFFe9eef5))
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Tombol kurang (min 1 tiket)
                        Button(
                            onClick = { if (jumlah > 1) jumlah-- },
                            modifier = Modifier.size(52.dp),
                            shape = CircleShape,
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = orange)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(width = 20.dp, height = 3.dp)
                                    .background(Color.White)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(52.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFEFF1F5)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "$jumlah",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // Tombol tambah
                        Button(
                            onClick = { jumlah++ },
                            modifier = Modifier.size(52.dp),
                            shape = CircleShape,
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = orange)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Tambah",
                                tint = Color.White
                            )
                        }
                    }
                }
            }

            // Card Total Bayar
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = biru)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Total Bayar", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color (0xFFe9eef5))
                    Text(
                        text = formatRupiah(total),
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,
                        color = orange
                    )
                }
            }

            // Tombol Reset
            Button(
                onClick = { jumlah = 1 },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = orange)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Reset",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("RESET", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}