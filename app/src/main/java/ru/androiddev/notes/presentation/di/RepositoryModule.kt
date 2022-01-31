package ru.androiddev.notes.presentation.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.androiddev.notes.data.database.NotesDatabase
import ru.androiddev.notes.data.mappers.NoteEntityMapper
import ru.androiddev.notes.data.repositories.DataSource
import ru.androiddev.notes.data.repositories.DataSourceImpl
import ru.androiddev.notes.data.repositories.NotesRepositoryImpl
import ru.androiddev.notes.domain.repositories.NotesRepository

val repositoryModule = module {
    single { NotesDatabase.getInstance(androidContext()).notesDao() }
    single { NoteEntityMapper() }
    single<DataSource> { DataSourceImpl(notesDao = get(), mapper = get()) }
    single<NotesRepository> { NotesRepositoryImpl(dataSource = get()) }
}

