package com.example.crudtodolist.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    //we define here all table we have in rdb
    entities = [Todo::class],
    version = 1
)
abstract class TodoDatabase: RoomDatabase() {
    abstract val dao: TodoDao
}