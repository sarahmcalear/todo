package com.my.todo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.jdbc.core.JdbcTemplate

@JdbcTest
class DefaultTodoRepositoryTest {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    private lateinit var todoRepository: TodoRepository

    @BeforeEach
    fun setup() {
        todoRepository = DefaultTodoRepository(jdbcTemplate = jdbcTemplate)
    }

    @Test
    @Throws(Exception::class)
    fun `create`() {

        val todo = Todo(title = "title", description = "description")

        todoRepository.save(todo)
    }

    @Test
    fun get() {
        val todo = Todo(title = "title", description = "description")

        val savedTodo = todoRepository.save(todo)

        val returnedTodo = todoRepository.get(savedTodo.id.toString())

        assertEquals(todo.title, returnedTodo.title)
        assertEquals(todo.description, returnedTodo.description)
    }
}
