package ru.androiddev.notes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {

        private const val DB_NAME = "notes_db"

        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getInstance(context: Context): NotesDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: createDatabase(context).also { INSTANCE = it }
            }
        }

        private fun createDatabase(context: Context): NotesDatabase {
            return Room.databaseBuilder(
                context,
                NotesDatabase::class.java,
                DB_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}