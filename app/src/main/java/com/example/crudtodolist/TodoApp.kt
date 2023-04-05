package com.example.crudtodolist

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//to have an application class
//so that will just give it access to application to also be able to
//use the application context to provide some object and dependencies
//of us
@HiltAndroidApp
class TodoApp :Application()
