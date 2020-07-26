package com.my.todo

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todos")
class TodosController(private val service: TodoService) {

    @GetMapping("/{id}")
    fun getTodo( @PathVariable id: String): ResponseEntity<Todo> {
        val todo = service.getTodo(id)
        return ResponseEntity(todo, HttpStatus.OK)
    }

    @PostMapping
    fun createTodo(@RequestBody request: Todo): ResponseEntity<Todo> {
        val createdTodo = service.createTodo(request)
        return ResponseEntity(createdTodo, HttpStatus.CREATED)
    }
}