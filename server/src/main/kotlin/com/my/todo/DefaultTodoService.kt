package com.my.todo

interface TodoService {
    fun createTodo(todo: Todo): Todo
}

class DefaultTodoService(
        private val repository: TodoRepository
) : TodoService {

    override fun createTodo(todo: Todo): Todo {
        return repository.save(todo)
    }
}