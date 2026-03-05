package com.example.attendancesystem.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.attendancesystem.domain.database.tables.ClassEntity
import com.example.attendancesystem.presentation.component.ClassScreen
import com.example.attendancesystem.presentation.screens.classes.ClassViewmodel

@Composable
fun ViewAttendanceScreen1(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ClassViewmodel = hiltViewModel()
) {
    // Observe class list
    val classList by viewModel.classes.collectAsState()

    // Refresh classes on screen load
    LaunchedEffect(Unit) {
        viewModel.loadClasses()
    }

    ClassScreen(
        modifier = modifier,
        title = "View Attendance",
        buttonText = null,
        classes = classList,
        onButtonClick = { selectedClass ->
            // Navigate to Screen 2 with classId
            navController.navigate("studentScreen/${selectedClass.classId}")
        }
    )
}
