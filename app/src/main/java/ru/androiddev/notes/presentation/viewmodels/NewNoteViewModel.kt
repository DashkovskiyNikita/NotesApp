package ru.androiddev.notes.presentation.viewmodels

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.androiddev.notes.R
import ru.androiddev.notes.domain.models.NoteModel
import ru.androiddev.notes.domain.usecases.SaveNoteUseCase

class NewNoteViewModel(
    private val saveNoteUseCase: SaveNoteUseCase,
) : ViewModel() {

    private val _result = MutableLiveData<Result>()
    val result: LiveData<Result> = _result


    fun saveNote(title: String, text: String) {
        viewModelScope.launch {
            when {
                TextUtils.isEmpty(title) -> {
                    _result.value = Result.Error(R.string.empty_title_error)
                }
                TextUtils.isEmpty(text) -> {
                    _result.value = Result.Error(R.string.empty_text_error)
                }
                else -> {
                    val note = NoteModel(title = title, text = text)
                    saveNoteUseCase(note)
                    _result.value = Result.Success(R.string.success_added)
                }
            }
        }
    }
}