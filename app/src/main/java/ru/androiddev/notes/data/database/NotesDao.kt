package ru.androiddev.notes.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM note_table")
    fun fetchAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note_table WHERE title LIKE '%'|| :title || '%'")
    fun searchNotes(title: String): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note_table WHERE id = :id")
    suspend fun fetchNoteById(id: Int): NoteEntity

    @Update
    suspend fun updateNote(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)
}