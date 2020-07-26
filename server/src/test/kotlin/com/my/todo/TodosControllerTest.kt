package com.my.todo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@WebMvcTest
@ContextConfiguration(classes = [TodosController::class, TestConfig::class])
class TodosControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mockTodoService: MockTodoService

    @Test
    fun `create Todo`() {
        val title = "Title"
        val description = "Description"

        val request = """
            {
                "title": "$title",
                "description": "$description"
            }
        """.trimIndent()

        val expectedResponse = """
            {
                "title": "$title",
                "description": "$description"
            }
        """.trimIndent()

        mockMvc.perform(
                post("/api/todos")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated)
                .andExpect(content().json(expectedResponse))
    }

    @Test
    fun getTodo() {
        val id = UUID.randomUUID()
        val title = "Title"
        val description = "Description"

        val expectedResponse = """
            {
                "id": "$id",
                "title": "$title",
                "description": "$description"
            }
        """.trimIndent()

        val expectedTodo = Todo(id = id, title = title, description = description)

        mockTodoService.setResponse(expectedTodo)

        mockMvc.perform(
                get("/api/todos/${id}")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk)
                .andExpect(content().json(expectedResponse))
    }

    class MockTodoService: TodoService {
        private lateinit var responseToBeReturned: Todo

        override fun createTodo(todo: Todo): Todo {
            return todo;
        }

        override fun getTodo(id: String): Todo {
            return responseToBeReturned
        }

        fun setResponse(todo: Todo) {
            responseToBeReturned = todo
        }
    }
}

@Configuration
class TestConfig {
    @Bean
    @Primary
    fun getMockTodoService() = TodosControllerTest.MockTodoService()
}
