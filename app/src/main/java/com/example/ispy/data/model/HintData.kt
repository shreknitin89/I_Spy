package com.example.ispy.data.model

data class HintData(
    val hint: String,
    val createdUser: User,
    val latitude: Double,
    val longitude: Double,
    val ratings: List<Rating>,
    val hintWins: Int
)

data class Rating(
    val userRating: Double,
    val user: User
)

data class User(
    val name: String,
    val userWins: Int
)
