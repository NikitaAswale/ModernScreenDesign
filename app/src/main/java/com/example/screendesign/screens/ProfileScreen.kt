package com.example.screendesign.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onBackClick: () -> Unit,
    onEditProfile: () -> Unit,
    onSettings: () -> Unit
) {
    val lightBlue = Color(0xFF4AA1E5)
    val darkBlue = Color(0xFF1E2A4A)
    val accentBlue = Color(0xFF60B5FF)
    val navyBlue = Color(0xFF2A3A5A)
    
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                // Top bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    
                    Text(
                        text = "Profile",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                    
                    IconButton(onClick = onSettings) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
            
            item {
                // Profile header
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = navyBlue),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Profile picture
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .background(accentBlue),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Profile",
                                tint = Color.White,
                                modifier = Modifier.size(60.dp)
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Text(
                            text = "John Doe",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        
                        Text(
                            text = "john.doe@email.com",
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 14.sp
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Button(
                            onClick = onEditProfile,
                            colors = ButtonDefaults.buttonColors(containerColor = accentBlue),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text("Edit Profile")
                        }
                    }
                }
            }
            
            item {
                // Statistics
                Text(
                    text = "Your Statistics",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StatCard(
                        title = "Events Attended",
                        value = "24",
                        icon = Icons.Filled.Event,
                        modifier = Modifier.weight(1f)
                    )
                    
                    StatCard(
                        title = "Bookmarks",
                        value = "12",
                        icon = Icons.Filled.Bookmark,
                        modifier = Modifier.weight(1f)
                    )
                    
                    StatCard(
                        title = "Reviews",
                        value = "8",
                        icon = Icons.Default.Star,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            
            item {
                // Quick actions
                Text(
                    text = "Quick Actions",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = navyBlue),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column {
                        ProfileMenuItem(
                            icon = Icons.Outlined.History,
                            title = "Event History",
                            subtitle = "View your past events",
                            onClick = { /* TODO */ }
                        )
                        
                        Divider(color = Color.White.copy(alpha = 0.1f))
                        
                        ProfileMenuItem(
                            icon = Icons.Filled.Bookmark,
                            title = "Saved Events",
                            subtitle = "Your bookmarked events",
                            onClick = { /* TODO */ }
                        )
                        
                        Divider(color = Color.White.copy(alpha = 0.1f))
                        
                        ProfileMenuItem(
                            icon = Icons.Default.Notifications,
                            title = "Notifications",
                            subtitle = "Manage your notifications",
                            onClick = { /* TODO */ }
                        )
                        
                        Divider(color = Color.White.copy(alpha = 0.1f))
                        
                        ProfileMenuItem(
                            icon = Icons.Outlined.CreditCard,
                            title = "Payment Methods",
                            subtitle = "Manage payment options",
                            onClick = { /* TODO */ }
                        )
                    }
                }
            }
            
            item {
                // Account settings
                Text(
                    text = "Account",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = navyBlue),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column {
                        ProfileMenuItem(
                            icon = Icons.Outlined.Security,
                            title = "Privacy & Security",
                            subtitle = "Manage your security settings",
                            onClick = { /* TODO */ }
                        )
                        
                        Divider(color = Color.White.copy(alpha = 0.1f))
                        
                        ProfileMenuItem(
                            icon = Icons.Outlined.Help,
                            title = "Help & Support",
                            subtitle = "Get help and contact support",
                            onClick = { /* TODO */ }
                        )
                        
                        Divider(color = Color.White.copy(alpha = 0.1f))
                        
                        ProfileMenuItem(
                            icon = Icons.Default.Info,
                            title = "About",
                            subtitle = "App version and info",
                            onClick = { /* TODO */ }
                        )
                        
                        Divider(color = Color.White.copy(alpha = 0.1f))
                        
                        ProfileMenuItem(
                            icon = Icons.Default.ExitToApp,
                            title = "Sign Out",
                            subtitle = "Sign out of your account",
                            onClick = { /* TODO */ },
                            isDestructive = true
                        )
                    }
                }
            }
            
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    val navyBlue = Color(0xFF2A3A5A)
    val accentBlue = Color(0xFF60B5FF)
    
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = navyBlue),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = accentBlue,
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = value,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = title,
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ProfileMenuItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    isDestructive: Boolean = false
) {
    val accentBlue = Color(0xFF60B5FF)
    val iconColor = if (isDestructive) Color(0xFFFF5722) else accentBlue
    val titleColor = if (isDestructive) Color(0xFFFF5722) else Color.White
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = titleColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            
            Text(
                text = subtitle,
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 12.sp
            )
        }
        
        Icon(
            imageVector = Icons.Rounded.KeyboardArrowRight,
            contentDescription = "Navigate",
            tint = Color.White.copy(alpha = 0.5f),
            modifier = Modifier.size(20.dp)
        )
    }
} 