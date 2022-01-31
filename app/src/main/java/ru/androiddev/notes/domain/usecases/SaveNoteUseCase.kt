package ru.androiddev.notes.domain.usecases

import ru.androiddev.notes.domain.models.NoteModel
import ru.androiddev.notes.domain.repositories.NotesRepository

class SaveNoteUseCase(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(noteModel: NoteModel) = notesRepository.insertNote(noteModel)
}