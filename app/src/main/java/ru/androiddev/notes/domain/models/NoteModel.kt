package ru.androiddev.notes.domain.models

data class NoteModel(
    var id: Int = 0,
    var title: String,
    var text: String,
    var isCompleted: Boolean = false
)
