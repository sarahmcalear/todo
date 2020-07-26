package com.my.todo

import java.util.*

data class Todo(
        val id: UUID? = null,
        val title: String,
        val description: String
)