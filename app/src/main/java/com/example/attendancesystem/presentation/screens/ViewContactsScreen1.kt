//package com.example.attendancesystem.presentation.screens
//
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavHostController
//import com.example.attendancesystem.presentation.component.CustomButton
//import com.example.attendancesystem.presentation.component.InstituteName
//import com.example.attendancesystem.presentation.component.SecondBar
//import com.example.attendancesystem.presentation.component.TopBar
//import com.example.attendancesystem.presentation.screens.student.StudentViewmodel
//@Composable
//fun ViewContactsScreen1(
//    navController: NavHostController,
//    classId: Int,
//    modifier: Modifier = Modifier,
//    viewModel: StudentViewmodel = hiltViewModel()
//) {
//    val students by viewModel.students.collectAsState()
//    val filteredStudents = students.filter { it.classId == classId }
//
//    Scaffold(
//        modifier = modifier,
//        topBar = {
//            TopBar(title = "View Contacts")
//        }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//        ) {
//            SecondBar(
//                title = "Class $classId",
//                buttonText = "Add"
//            ) {
//                // Optional: navigate to AddStudentScreen
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            LazyColumn(
//                modifier = Modifier.fillMaxSize()
//            ) {
//                // Institute name header
//                item {
//                    Box(
//                        modifier = Modifier
//                            .padding(8.dp)
//                            .fillMaxWidth()
//                            .border(2.dp, Color.Black),
//                        contentAlignment = Alignment.Center,
//                    ) {
//                        InstituteName()
//                    }
//                }
//
//                // Dynamic student list
//                items(filteredStudents.size) { index ->
//                    val student = filteredStudents[index]
//                    Spacer(modifier = Modifier.height(10.dp))
//                    CustomButton(
//                        text = student.name,
//                        onClick = {
//                            // Navigate to student details or attendance screen if needed
//                            // navController.navigate("view_attendance_2/${student.classId}")
//                        },
//                     //   phone = student.phoneNumber,
//                    )
//                }
//            }
//        }
//    }
//}
//
//                /*    items(10) { index ->
//                    val studentNames = listOf(
//                        "Zubair Ahmad", "Ibrahim Khan", "Umair Ashraf",
//                        "Hafiz Faizan Sajjid", "Waqas Khizra", "Musa Bhai",
//                        "Ibrahim Khan", "Zubair Ahmad", "Hafiz Faizan Sajjid",
//                        "Waqas Khizra"
//                    )
//                    Spacer(modifier = Modifier.height(10.dp))
//                    CustomButton(
//                        text = studentNames[index],
//                        roll = (index+1).toString()
//                    )
//                }
//                */
