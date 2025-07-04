package com.example.kullanicietkilesimi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnasaSayfa() {
    val scope = rememberCoroutineScope()
    val snackbarHosState = remember { SnackbarHostState() }

    val acilirKontrol = remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Ansayfa") }) },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHosState) {
                Snackbar( //snackbar özelleştirme
                    containerColor = Color.White,
                    contentColor = Color.Blue,
                    actionColor = Color.Red,
                    snackbarData = it
                )
            }
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    scope.launch {
                        //snackbarHosState.showSnackbar("Merhaba")
                        val sb = snackbarHosState.showSnackbar(
                            message = "Silmek ister misiniz?",
                            actionLabel = "Evet"
                        )
                        if (sb == SnackbarResult.ActionPerformed) { //evet demek
                            snackbarHosState.showSnackbar(message = "Silindi")
                        }

                    }
                }
            ) {
                Text(text = "Snacbar")
            }

            Button(
                onClick = {
                    acilirKontrol.value = true
                }
            ) {
                Text(text = "Alert")
            }

            if (acilirKontrol.value) {
                AlertDialog(
                    onDismissRequest = {acilirKontrol.value = false},//boş bir yer seçtiğimzde çalışacak yapı
                    title = { Text(text = "Başlık") },
                    text = { Text(text = "Mesaj") },
                    confirmButton = {
                        OutlinedButton(
                            onClick = {
                                acilirKontrol.value = false
                                scope.launch {
                                    snackbarHosState.showSnackbar(
                                        message = "Tamam Seçildi"
                                    )
                                }
                            }
                        ) {
                            Text(text = "Tamam")
                        }

                        OutlinedButton(
                            onClick = {
                                acilirKontrol.value = false
                                scope.launch {
                                    snackbarHosState.showSnackbar(
                                        message = "İptal Seçildi"
                                    )
                                }
                            }
                        ) {
                            Text(text = "İptal")
                        }
                    }
                )
            }

        }

    }
}