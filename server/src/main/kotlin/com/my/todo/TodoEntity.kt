package com.my.todo

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class TodoEntity(
        @Id
        val id: UUID,
        @Column(nullable = false)
        val title: String,
        @Column()
        val description: String
)