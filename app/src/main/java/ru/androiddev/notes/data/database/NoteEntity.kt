package ru.androiddev.notes.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = NoteEntity.TABLE_NAME)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "isCompleted")
    val isCompleted: Boolean
) {
    companion object {
        const val TABLE_NAME = "note_table"
    }
}
