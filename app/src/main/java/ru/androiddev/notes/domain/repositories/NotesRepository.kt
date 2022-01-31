package ru.androiddev.notes.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.androiddev.notes.domain.models.NoteModel

interface NotesRepository {
    suspend fun insertNote(noteModel: NoteModel)
    suspend fun fetchAllNotes(): Flow<List<NoteModel>>
    suspend fun searchNotes(title: String): Flow<List<NoteModel>>
    suspend fun fetchNote(id : Int) : NoteModel
    suspend fun updateNote(noteModel: NoteModel)
    suspend fun deleteNote(noteModel: NoteModel)
}