package ru.androiddev.notes.domain.usecases

import ru.androiddev.notes.domain.repositories.NotesRepository

class FetchAllNotesUseCase(private val notesRepository: NotesRepository) {
    suspend operator fun invoke() = notesRepository.fetchAllNotes()
}