package ru.androiddev.notes.presentation.viewmodels

import androidx.annotation.StringRes

sealed class Result {
    class Success(@StringRes val message: Int) : Result()
    class Error(@StringRes val error: Int) : Result()
}