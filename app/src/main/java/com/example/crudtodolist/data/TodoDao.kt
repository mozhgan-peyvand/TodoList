package com.example.crudtodolist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

//for access with db and know room what we want to do
@Dao
interface TodoDao {

    //on conflict is use for what was your strategy when insert an
    //id that exist currently in database
    //and we use of replace that mean when we have that id we should
    //update it and replace with new data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo (todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getTodoById(id:Int): Todo?


    //we use of flow becuse it is cool and when list change
    //we have new data and real time
    @Query("SELECT * FROM todo")
    fun getTodos(): Flow<List<Todo>>
}