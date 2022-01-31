package ru.androiddev.notes.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.androiddev.notes.domain.models.NoteModel
import ru.androiddev.notes.domain.usecases.DeleteNoteUseCase
import ru.androiddev.notes.domain.usecases.FetchAllNotesUseCase
import ru.androiddev.notes.domain.usecases.SaveNoteUseCase

class NotesViewModel(
    private val fetchAllNotesUseCase: FetchAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val saveNoteUseCase: SaveNoteUseCase
) : ViewModel() {
    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _notes = MutableLiveData<List<NoteModel>>()
    val notes: LiveData<List<NoteModel>> = _notes

    fun fetchNotes() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            fetchAllNotesUseCase().collect { list ->
                _notes.postValue(list)
                _isLoading.postValue(false)
            }
        }
    }

    fun deleteNote(noteModel: NoteModel) {
        viewModelScope.launch {
            deleteNoteUseCase(noteModel)
        }
    }

    fun updateNote(noteModel: NoteModel) {
        viewModelScope.launch {
            saveNoteUseCase(noteModel)
        }
    }
}