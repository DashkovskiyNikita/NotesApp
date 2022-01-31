package ru.androiddev.notes.data.repositories

import kotlinx.coroutines.flow.Flow
import ru.androiddev.notes.domain.models.NoteModel
import ru.androiddev.notes.domain.repositories.NotesRepository

class NotesRepositoryImpl(
    private val dataSource: DataSource
) : NotesRepository {
    override suspend fun insertNote(noteModel: NoteModel) {
        dataSource.saveNote(noteModel)
    }

    override suspend fun fetchAllNotes(): Flow<List<NoteModel>> {
        return dataSource.fetchAllNotes()
    }

    override suspend fun searchNotes(title: String): Flow<List<NoteModel>> {
        return dataSource.searchNotes(title)
    }

    override suspend fun fetchNote(id: Int): NoteModel {
        return dataSource.fetchNote(id)
    }

    override suspend fun updateNote(noteModel: NoteModel) {
        dataSource.updateNote(noteModel)
    }

    override suspend fun deleteNote(noteModel: NoteModel) {
        return dataSource.deleteNote(noteModel)
    }

}