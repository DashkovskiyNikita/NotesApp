package ru.androiddev.notes.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.androiddev.notes.data.mappers.NoteEntityMapper
import ru.androiddev.notes.data.database.NotesDao
import ru.androiddev.notes.domain.models.NoteModel

class DataSourceImpl(
    private val notesDao: NotesDao,
    private val mapper: NoteEntityMapper
) : DataSource {
    override suspend fun saveNote(noteModel: NoteModel) {
        val entity = mapper.toEntityMapper(noteModel)
        notesDao.saveNote(entity)
    }

    override suspend fun fetchAllNotes(): Flow<List<NoteModel>> {
        return notesDao.fetchAllNotes().map { list ->
            list.map { note ->
                mapper.toModelMapper(note)
            }
        }
    }

    override suspend fun searchNotes(title: String): Flow<List<NoteModel>> {
        return notesDao.searchNotes(title).map { list ->
            list.map { note ->
                mapper.toModelMapper(note)
            }
        }
    }

    override suspend fun fetchNote(id: Int): NoteModel {
        val entity = notesDao.fetchNoteById(id)
        return mapper.toModelMapper(entity)
    }

    override suspend fun updateNote(noteModel: NoteModel) {
        val entity = mapper.toEntityMapper(noteModel)
        notesDao.updateNote(entity)
    }

    override suspend fun deleteNote(noteModel: NoteModel) {
        val entity = mapper.toEntityMapper(noteModel)
        notesDao.deleteNote(entity)
    }
}