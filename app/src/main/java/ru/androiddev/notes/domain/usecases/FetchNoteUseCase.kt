package ru.androiddev.notes.domain.usecases

import ru.androiddev.notes.domain.repositories.NotesRepository

class FetchNoteUseCase(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(id: Int) = notesRepository.fetchNote(id)
}