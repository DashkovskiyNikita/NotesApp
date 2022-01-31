package ru.androiddev.notes.presentation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.androiddev.notes.presentation.di.appModule
import ru.androiddev.notes.presentation.di.repositoryModule
import ru.androiddev.notes.presentation.di.useCasesModule

class NotesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@NotesApp)
            modules(repositoryModule, useCasesModule, appModule)
        }
    }
}