package com.example.y2s2_assignment2.domain.model

import java.util.UUID

data class Habit(
    val id: String = UUID.randomUUID().toString(),
    var title: String
)
