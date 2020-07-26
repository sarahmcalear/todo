package com.my.todo

interface TodoService {
    fun createTodo(todo: Todo): Todo
    fun getTodo(id: String): Todo
}

class DefaultTodoService(
        private val repository: TodoRepository
) : TodoService {

    override fun createTodo(todo: Todo): Todo {
        return repository.save(todo)
    }

    override fun getTodo(id: String): Todo {
        return repository.get(id)
    }
}