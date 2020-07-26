package com.my.todo

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.*


interface TodoRepository {
    fun save(todo: Todo): Todo
    fun get(id: String): Todo
}

@Repository
class DefaultTodoRepository(
        private val jdbcTemplate: JdbcTemplate
) : TodoRepository {
    override fun save(todo: Todo): Todo {
        val id = UUID.randomUUID()
        val insertQuery = """
            INSERT INTO todo (id, title, description, timestamp) 
            VALUES (?, ?, ?, ?)
            """.trimIndent()

        jdbcTemplate.update(
                insertQuery,
                id,
                todo.title,
                todo.description,
                Date()
        )

        return Todo(id = id, title = todo.title, description = todo.description)
    }

    override fun get(id: String): Todo {
        val selectQuery = """
            SELECT id, title, description
            FROM todo
            WHERE id = '${id}'"""
                .trimIndent()

        val todo = jdbcTemplate.queryForObject(
                selectQuery,
                rowMapper()
        )

        if (todo != null) {
            return todo
        } else {
            throw Exception()
        }
    }

    private fun rowMapper(): RowMapper<Todo> {
        return RowMapper { result: ResultSet, rowNum: Int ->
            Todo(
                    UUID.fromString(result.getString("id")),
                    result.getString("title"),
                    result.getString("description")
            )
        }
    }
}