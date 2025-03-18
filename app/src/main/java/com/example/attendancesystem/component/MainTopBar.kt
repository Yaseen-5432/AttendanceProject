package com.example.attendancesystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(
                color = MaterialTheme.colorScheme.primary, // Orange
                shape = RoundedCornerShape(bottomStart = 100.dp, bottomEnd = 100.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 0.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CenterAlignedTopAppBar(
                modifier = modifier,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                windowInsets = WindowInsets(0.dp),
                title = {
                    Text(
                        text = "Attendance App",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                },
                navigationIcon = {
                    IconButton(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(30.dp),
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,

                            contentDescription = "Back Button",
                            modifier = Modifier
                                .size(30.dp),
                            tint = MaterialTheme.colorScheme.background
                        )
                    }

                }
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            ) {
                Text(
                    text = "Welcome,",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.background
                )
                Text(
                    text = "MUHAMMAD YASEEN",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),

                    )
            }
            Spacer(modifier = Modifier.padding(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Profile",
                        tint = Color.Black,
                        modifier = Modifier.size(50.dp)
                    )
                    Text(
                        text = "Profile",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.background
                    )
                }

                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .clip(CircleShape)
                        .border(2.dp, MaterialTheme.colorScheme.background, CircleShape)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(1f),
                          //  .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "01",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            text = "February",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Icon(
                        Icons.Default.DateRange,
                        contentDescription = "Calendar",
                        tint = Color.Black,
                        modifier = Modifier.size(50.dp)
                    )
                    Text(
                        text = "Calendar",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.background
                    )
                }
            }
        }
    }
}