package ru.gressor.websockettest.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.gressor.websockettest.domain.MainInteractor

class MainViewModelFactory(
    private val interactor: MainInteractor,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(interactor) as T
            else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
        }
}