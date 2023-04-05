package com.example.crudtodolist.ui.add_edit_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudtodolist.data.Todo
import com.example.crudtodolist.data.TodoRepository
import com.example.crudtodolist.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/*
it cantain a bunch of state variable we use of it to restore a viewmodel state
maybe after process death but on the other hand what s really useful is
that this contains our navigation arguments so on this screen we actually  have this navigation
argument the to do id which we would actually like to load form the database if we clicked on a
to do open its details here on this page so hilt will do all that for us behind the since
*/

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private var repository: TodoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var todo by mutableStateOf<Todo?>(null)
        private set

    /*
    * private set:
    * we can change just in this view model
    * but we can reed it outside
    * */
    var title by mutableStateOf("")
        private set

    /*
    * by:
    * we can easily assign the state without using that value all
    *
    * */
    var description by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        /*
        here we first want to check did we actually open this add edit
        page by clicking on an existing to do or by clicking on adding
        a new to do because if we want to open or if we open this from
        an existing to do then we want to load this to do from the
        database by its id so then we would get the to do id using our
        saved state handle that is an integer the navigation argument is
        called to do id and
        * */
        val todoId = savedStateHandle.get<Int>("todoId")!!
        if (todoId != -1) {
            viewModelScope.launch {
                repository.getTodoById(id = todoId)?.let { todo ->
                    title = todo.title
                    description = todo.description ?: ""
                    this@AddEditTodoViewModel.todo = todo
                }
            }
        }
    }

    fun onEvent(event: AddEditTodoEvent) {
        when (event) {
            is AddEditTodoEvent.OnTitleChange -> {
                title = event.title
            }
            is AddEditTodoEvent.OnDescriptionChange -> {
                description = event.description
            }
            is AddEditTodoEvent.OnSaveTodoClick -> {
                viewModelScope.launch {
                    if (title.isBlank()){
                        sendUiEvent(UiEvent.ShowSnackbar(
                            message = "the title cant be empty"
                        ))
                        return@launch
                    }
                    repository.insertTodo(
                        Todo(
                            title = title,
                            description = description,
                            isDone = todo?.isDone ?: false,
                            id = todo?.id
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
        }
    }
    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}