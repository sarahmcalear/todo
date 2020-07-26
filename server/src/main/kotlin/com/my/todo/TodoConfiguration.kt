package com.my.todo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.client.RestTemplate
import javax.sql.DataSource

@Configuration
class TodoConfiguration {
    @Bean
    fun getTodoService(
            repository: DefaultTodoRepository
    ) = DefaultTodoService(
            repository = repository
    )

    @Bean
    fun getRestTemplate() = RestTemplate()

    @Bean
    fun jdbcTemplate(dataSource: DataSource): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }
}