package com.example.composesample

import java.util.*

data class Todo(
    val id: UUID = UUID.randomUUID(),
    val data: String
)