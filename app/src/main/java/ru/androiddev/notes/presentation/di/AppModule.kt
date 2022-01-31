package ru.androiddev.notes.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.androiddev.notes.presentation.viewmodels.EditNoteViewModel
import ru.androiddev.notes.presentation.viewmodels.NewNoteViewModel
import ru.androiddev.notes.presentation.viewmodels.NotesViewModel

val appModule = module {
    viewModel {
        NotesViewModel(
            fetchAllNotesUseCase = get(),
            deleteNoteUseCase = get(),
            saveNoteUseCase = get()
        )
    }
    viewModel {
        NewNoteViewModel(saveNoteUseCase = get())
    }
    viewModel {
        EditNoteViewModel(
            updateNoteUseCase = get(),
            fetchNoteUseCase = get()
        )
    }
}