package com.example.screendesign.data

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val date: LocalDateTime,
    val location: String,
    val category: String,
    val rating: Float,
    val attendees: Int,
    val price: Double,
    val imageUrl: String? = null,
    val isBookmarked: Boolean = false,
    val organizer: String,
    val maxCapacity: Int,
    val tags: List<String> = emptyList()
) {
    val formattedDate: String
        get() = date.format(DateTimeFormatter.ofPattern("dd MMM"))
    
    val formattedDateTime: String
        get() = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm"))
    
    val attendeesText: String
        get() = if (attendees >= 1000) {
            "${String.format("%.1f", attendees / 1000.0)} K"
        } else {
            attendees.toString()
        }
}

data class EventCategory(
    val id: String,
    val name: String,
    val color: Long,
    val iconName: String
)

// Sample data
object EventRepository {
    val sampleEvents = listOf(
        Event(
            id = "1",
            title = "The best event",
            description = "An amazing event that showcases the best in entertainment and networking. Join us for an unforgettable experience with industry leaders.",
            date = LocalDateTime.of(2024, 3, 8, 19, 0),
            location = "STAR events hall",
            category = "Entertainment",
            rating = 5.0f,
            attendees = 2890,
            price = 75.0,
            organizer = "Star Entertainment Co.",
            maxCapacity = 3000,
            tags = listOf("networking", "entertainment", "premium")
        ),
        Event(
            id = "2",
            title = "Lucky event",
            description = "A luxurious gathering featuring exclusive performances and high-end amenities. Limited seating available.",
            date = LocalDateTime.of(2024, 2, 17, 20, 0),
            location = "Luxury events hall",
            category = "Luxury",
            rating = 5.0f,
            attendees = 7910,
            price = 150.0,
            organizer = "Luxury Events Ltd.",
            maxCapacity = 8000,
            tags = listOf("luxury", "exclusive", "vip")
        ),
        Event(
            id = "3",
            title = "Tech Innovation Summit",
            description = "Discover the latest in technology and innovation. Network with tech leaders and startups.",
            date = LocalDateTime.of(2024, 4, 15, 9, 0),
            location = "Tech Center Convention Hall",
            category = "Technology",
            rating = 4.8f,
            attendees = 1250,
            price = 99.0,
            organizer = "Tech Innovators Inc.",
            maxCapacity = 1500,
            tags = listOf("technology", "innovation", "startups")
        ),
        Event(
            id = "4",
            title = "Art & Design Expo",
            description = "Explore contemporary art and design trends. Meet artists and designers from around the world.",
            date = LocalDateTime.of(2024, 5, 22, 10, 0),
            location = "Modern Art Gallery",
            category = "Art",
            rating = 4.6f,
            attendees = 890,
            price = 45.0,
            organizer = "Creative Arts Foundation",
            maxCapacity = 1000,
            tags = listOf("art", "design", "gallery", "contemporary")
        ),
        Event(
            id = "5",
            title = "Music Festival 2024",
            description = "Three days of amazing music from top artists. Food, drinks, and entertainment for all ages.",
            date = LocalDateTime.of(2024, 6, 10, 16, 0),
            location = "Central Park Amphitheater",
            category = "Music",
            rating = 4.9f,
            attendees = 15000,
            price = 120.0,
            organizer = "Music Events Global",
            maxCapacity = 20000,
            tags = listOf("music", "festival", "outdoor", "family")
        )
    )
    
    val categories = listOf(
        EventCategory("1", "All", 0xFF6650a4, "category"),
        EventCategory("2", "Entertainment", 0xFF60B5FF, "movie"),
        EventCategory("3", "Technology", 0xFF4CAF50, "computer"),
        EventCategory("4", "Music", 0xFFFF9800, "music_note"),
        EventCategory("5", "Art", 0xFFE91E63, "palette"),
        EventCategory("6", "Luxury", 0xFFFFD700, "diamond")
    )
} 