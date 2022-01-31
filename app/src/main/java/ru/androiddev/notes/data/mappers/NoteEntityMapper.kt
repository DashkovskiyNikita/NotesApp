package ru.androiddev.notes.data.mappers

import ru.androiddev.notes.data.database.NoteEntity
import ru.androiddev.notes.domain.models.NoteModel

class NoteEntityMapper {
    fun toEntityMapper(noteModel: NoteModel): NoteEntity {
        return NoteEntity(
            id = noteModel.id,
            title = noteModel.title,
            text = noteModel.text,
            isCompleted = noteModel.isCompleted
        )
    }

    fun toModelMapper(noteEntity: NoteEntity): NoteModel {
        return NoteModel(
            id = noteEntity.id,
            title = noteEntity.title,
            text = noteEntity.text,
            isCompleted = noteEntity.isCompleted
        )
    }
}