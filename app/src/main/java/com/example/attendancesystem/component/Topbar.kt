package com.example.attendancesystem.component

import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



@Composable
fun ScafforldScreen(){
    Scaffold(

    ) {
            padding ->
        ScafforldScreen2(modifier = Modifier.padding(padding))
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScafforldScreen2(modifier: Modifier = Modifier){
    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopBar()
        }
    ) {
        padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

//            item {
//                SecondBar(
//                    title = "Classes-(Select Class)",
//                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
//                ){
//
//                }
//            }
            item {
                MyScreen()
            }
//
//            // List of Attendance Cards
//            items(10) { index ->
//                val studentNames = listOf(
//                    "Zubair Ahmad", "Ibrahim Khan", "Umair Ashraf",
//                    "Hafiz Faizan Sajjid", "Waqas Khizra", "Musa Bhai",
//                    "Ibrahim Khan", "Zubair Ahmad", "Hafiz Faizan Sajjid",
//                    "Waqas Khizra"
//                )
//                Spacer(modifier = Modifier.height(20.dp))
//               CustomButton(text = studentNames[index], roll = (index+1).toString())
//
//                }
            }
        }
    }



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        windowInsets = WindowInsets(0.dp),
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 8.dp)
            )
        },
        navigationIcon ={
            IconButton(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(30.dp),
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,

                    contentDescription = "Back Button",
                    modifier = Modifier
                        .size(30.dp),
                    tint = MaterialTheme.colorScheme.background
                )
            }

        }
    )

}