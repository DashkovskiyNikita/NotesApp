package ru.androiddev.notes.domain.usecases

import ru.androiddev.notes.domain.repositories.NotesRepository

class SearchNotesUseCase(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(title : String) = notesRepository.searchNotes(title)
}