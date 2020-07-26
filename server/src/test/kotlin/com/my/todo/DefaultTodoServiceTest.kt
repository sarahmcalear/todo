package com.my.todo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.*

class DefaultTodoServiceTest {

    lateinit var todoService: DefaultTodoService
    lateinit var repository: TodoRepository

    @BeforeEach
    fun setup() {
        repository = mock(TodoRepository::class.java)
        todoService = DefaultTodoService(repository)
    }

    @Test
    fun `create Todo`() {
        val todoToBeCreated = Todo(
                title = "title",
                description = "description"
        )

        val expectedTodo = Todo(
                id = UUID.randomUUID(),
                title = "title",
                description = "description"
        )

        `when`(repository.save(todoToBeCreated)).thenReturn(expectedTodo)

        val actualTodo = todoService.createTodo(todoToBeCreated)

        assertEquals(expectedTodo, actualTodo)
    }
}