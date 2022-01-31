package ru.androiddev.notes.presentation.di

import org.koin.dsl.module
import ru.androiddev.notes.domain.usecases.*

val useCasesModule = module {
    single { DeleteNoteUseCase(notesRepository = get()) }
    single { FetchAllNotesUseCase(notesRepository = get()) }
    single { SaveNoteUseCase(notesRepository = get()) }
    single { SearchNotesUseCase(notesRepository = get()) }
    single { FetchNoteUseCase(notesRepository = get()) }
    single { UpdateNoteUseCase(notesRepository = get()) }
}