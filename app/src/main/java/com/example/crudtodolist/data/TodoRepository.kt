package com.example.crudtodolist.data

import kotlinx.coroutines.flow.Flow

//that s usually a good idea to depend on abstractions not on concretions
//so interface would be an abstraction because that way you are just flexible with your architecture
//reason for use interface for repository
//1-if we want to change we just change implementaion
//2-test get easy because we work with fake repository for test
//3- for test we should not access across with actual api and database
interface TodoRepository {
    suspend fun insertTodo (todo: Todo)

    suspend fun deleteTodo(todo: Todo)

    suspend fun getTodoById(id:Int): Todo?

    fun getTodos(): Flow<List<Todo>>
}