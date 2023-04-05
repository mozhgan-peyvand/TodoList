package com.example.crudtodolist.di

import android.app.Application
import androidx.room.Room
import com.example.crudtodolist.data.TodoDatabase
import com.example.crudtodolist.data.TodoRepository
import com.example.crudtodolist.data.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*there are different modules we could create like in a bigger project
you usually have more than just one module these module not only
define the dependencies we have on our project but also their
lifetime so there are some dependencies we might not need
 throughout the whole lifetime of our application so they
 they dont need to be so called singletons here we do that but if
you maybe have an app that has multiple activities there might
be some dependencies you only need for a single activity then
you can put them in a single module which only keeps them alive for
the lifetime of that specific activity but since we dont have here
just make sure that this is an object not a class
we want to annotate this with module to tell dagger hey
we are going to have a module here and another annotation
will be installedin so this now defines how long our dependencies
we define here are going to live since as i said this is the app
module which contains only singletons so just application just
dependencies that live as long as our application does we install
this in the so called singleton component  and then in here we
define with function how the dependencies should be created
so lets start by defining how dagger can create our room database*/

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

/*    provides because this function will
    provide a dependency and singleton so the actual
    dependency is a singleton there will only be a single
    instance that exists
    it will need the application context to be created so
    what we need?
    we need to pass it here
    and for the application dagger hill actually knows
    how to provide this so it will do all that behind the
    scenes  */
    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabase): TodoRepository {
        return TodoRepositoryImpl(db.dao)
    }
}