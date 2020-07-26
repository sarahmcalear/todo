package com.my.todo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/todos")
class TodosController(private val service: TodoService) {

    @PostMapping
    fun createTodo(@RequestBody request: Todo): ResponseEntity<Todo> {
        val createdTodo = service.createTodo(request)
        return ResponseEntity(createdTodo, HttpStatus.CREATED)
    }
}