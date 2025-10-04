package com.example.attendancesystem.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.attendancesystem.domain.database.tables.ClassEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassScreen(
    modifier: Modifier = Modifier,
    title: String,
    buttonText: String? = "Add",
    classes: List<ClassEntity> = emptyList(),
    onButtonClick: (ClassEntity) -> Unit,   // callback param
    onBackClick: (() -> Unit)? = null       // back button ka callback
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar( title = title)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Second bar (optional button)
            SecondBar(
                title = "Classes-(Select)",
                buttonText = buttonText,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
            ) { /* Optional action for top button */ }

            Spacer(modifier = Modifier.height(30.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                // Dynamic classes
                items(classes.size) { index ->
                    val classItem = classes[index]
                    Spacer(modifier = Modifier.height(20.dp))

                    CustomButton(
                        text = classItem.className,
                        onClick = { onButtonClick(classItem) } // ✅ proper callback
                    )
                }
            }
        }
    }
}
