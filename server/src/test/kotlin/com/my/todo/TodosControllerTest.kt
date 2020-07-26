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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

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

    class MockTodoService: TodoService {
        override fun createTodo(todo: Todo): Todo {
            return todo;
        }
    }
}

@Configuration
class TestConfig {
    @Bean
    @Primary
    fun getMockTodoService() = TodosControllerTest.MockTodoService()
}
