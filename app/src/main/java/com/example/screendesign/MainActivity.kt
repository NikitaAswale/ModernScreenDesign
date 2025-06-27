package com.example.screendesign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.screendesign.data.Event
import com.example.screendesign.data.EventRepository
import com.example.screendesign.screens.EventDetailScreen
import com.example.screendesign.screens.ProfileScreen
import com.example.screendesign.screens.SearchScreen
import com.example.screendesign.ui.theme.ScreenDesignTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScreenDesignTheme {
                EventsApp()
            }
        }
    }
}

@Composable
fun EventsApp() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "events_home"
    ) {
        composable("events_home") {
            EventsScreen(
                onSearchClick = { navController.navigate("search") },
                onEventClick = { event ->
                    navController.navigate("event_detail/${event.id}")
                },
                onProfileClick = { navController.navigate("profile") }
            )
        }
        
        composable("search") {
            SearchScreen(
                onBackClick = { navController.popBackStack() },
                onEventClick = { event ->
                    navController.navigate("event_detail/${event.id}")
                }
            )
        }
        
        composable("event_detail/{eventId}") { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString("eventId")
            val event = EventRepository.sampleEvents.find { it.id == eventId }
            
            event?.let {
                EventDetailScreen(
                    event = it,
                    onBackClick = { navController.popBackStack() },
                    onBookEvent = {
                        // TODO: Implement booking logic
                    }
                )
            }
        }
        
        composable("profile") {
            ProfileScreen(
                onBackClick = { navController.popBackStack() },
                onEditProfile = {
                    // TODO: Implement edit profile
                },
                onSettings = {
                    // TODO: Implement settings
                }
            )
        }
    }
}

@Composable
fun EventsScreen(
    onSearchClick: () -> Unit,
    onEventClick: (Event) -> Unit,
    onProfileClick: () -> Unit
) {
    // Define colors from the image
    val lightBlue = Color(0xFF4AA1E5)
    val darkBlue = Color(0xFF1E2A4A)
    val accentBlue = Color(0xFF60B5FF)
    val navyBlue = Color(0xFF2A3A5A)
    
    // Create gradient background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(lightBlue, darkBlue),
                    startY = 0f,
                    endY = 1500f
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Top bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onProfileClick) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Events",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                
                IconButton(onClick = onSearchClick) {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "Search",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Segmented control
            var selectedOption by remember { mutableStateOf(2) } // Monthly selected by default
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0x33FFFFFF))
                    .padding(4.dp)
            ) {
                val options = listOf("Daily", "Weekly", "Monthly")
                options.forEachIndexed { index, text ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(24.dp))
                            .background(
                                if (selectedOption == index) navyBlue
                                else Color.Transparent
                            )
                            .padding(horizontal = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = text,
                            color = Color.White,
                            fontWeight = if (selectedOption == index) FontWeight.Medium else FontWeight.Normal
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Statistics text
            Text(
                text = "The statistics of last month",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Statistics value
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0x33FFFFFF))
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "5.148 K",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Event cards
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // First card
                EventCard(
                    event = EventRepository.sampleEvents[0],
                    modifier = Modifier.weight(1f),
                    onEventClick = onEventClick
                )
                
                // Second card
                EventCard(
                    event = EventRepository.sampleEvents[1],
                    modifier = Modifier.weight(1f),
                    onEventClick = onEventClick
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Events list title
            Text(
                text = "View the events list",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Events list
            EventRepository.sampleEvents.drop(2).take(3).forEach { event ->
                EventListItem(
                    event = event,
                    onClick = { onEventClick(event) }
                )
            }
        }
    }
}

@Composable
fun EventCard(
    event: Event,
    modifier: Modifier = Modifier,
    onEventClick: (Event) -> Unit
) {
    val navyBlue = Color(0xFF2A3A5A)
    val accentBlue = Color(0xFF60B5FF)
    
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = navyBlue),
        onClick = { onEventClick(event) }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Star icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(accentBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Title
            Text(
                text = event.title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            
            // Date
            Text(
                text = event.formattedDate,
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
            
            // Category
            Text(
                text = event.location,
                color = accentBlue,
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Rating stars
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(event.rating.toInt()) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFD700),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Value
            Text(
                text = event.attendeesText,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // View button
            Button(
                onClick = { onEventClick(event) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                colors = ButtonDefaults.buttonColors(containerColor = accentBlue),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "View",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun EventListItem(
    event: Event,
    onClick: () -> Unit
) {
    val accentBlue = Color(0xFF60B5FF)
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Star icon
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(accentBlue),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Star,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        // Title
        Text(
            text = event.title,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )
        
        // Arrow icon
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowRight,
                contentDescription = "View details",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventsScreenPreview() {
    ScreenDesignTheme {
        EventsApp()
    }
}