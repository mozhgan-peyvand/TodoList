package com.example.crudtodolist.ui.add_edit_todo

sealed class AddEditTodoEvent{
    /*
    * what kind of event we could possibly send:
    * 1- they could change the text content of our title text
    * 2- they could change the text content of our description text
    * 3- we may have unsafe to do click
    * */
    data class OnTitleChange(val title: String): AddEditTodoEvent()
    data class OnDescriptionChange(val description: String): AddEditTodoEvent()
    object OnSaveTodoClick: AddEditTodoEvent()
}
