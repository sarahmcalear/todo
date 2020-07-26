package com.my.todo

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.util.*


interface TodoRepository {
    fun save(todo: Todo): Todo
}

@Repository
class DefaultTodoRepository(
        private val jdbcTemplate: JdbcTemplate
) : TodoRepository {
    override fun save(todo: Todo): Todo {
        val id = UUID.randomUUID()
        val insertQuery = "INSERT INTO todo (id, title, description, timestamp) VALUES (?, ?, ?, ?)"
        jdbcTemplate.update(
                insertQuery,
                id,
                todo.title,
                todo.description,
                Date()
        )

        return Todo(id = id, title = todo.title, description = todo.description)
    }
}