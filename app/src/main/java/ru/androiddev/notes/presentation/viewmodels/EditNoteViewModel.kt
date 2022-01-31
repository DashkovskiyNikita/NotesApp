package ru.androiddev.notes.presentation.viewmodels



import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.androiddev.notes.R
import ru.androiddev.notes.domain.models.NoteModel
import ru.androiddev.notes.domain.usecases.FetchNoteUseCase
import ru.androiddev.notes.domain.usecases.UpdateNoteUseCase

class EditNoteViewModel(
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val fetchNoteUseCase: FetchNoteUseCase
) : ViewModel() {

    private val _result = MutableLiveData<Result>()
    val result: LiveData<Result> = _result

    private val _note = MutableLiveData<NoteModel>()
    val note: LiveData<NoteModel> = _note

    fun fetchNote(id: Int) {
        viewModelScope.launch {
            val result = fetchNoteUseCase(id)
            _note.postValue(result)
        }
    }

    fun updateNote(title: String, text: String) {
        viewModelScope.launch {
            when {
                TextUtils.isEmpty(title) -> {
                    _result.value = Result.Error(R.string.empty_title_error)
                }
                TextUtils.isEmpty(text) -> {
                    _result.value = Result.Error(R.string.empty_text_error)
                }
                else -> {
                    _note.value = _note.value?.copy(
                        title = title,
                        text = text
                    )
                    updateNoteUseCase(note.value!!)
                    _result.value = Result.Success(R.string.success_updated)
                }
            }
        }
    }

}