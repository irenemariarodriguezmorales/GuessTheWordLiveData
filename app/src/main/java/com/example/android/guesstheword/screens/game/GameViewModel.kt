package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class GameViewModel : ViewModel() {

    // Convertimos la variable a mutable
    private val _word = MutableLiveData<String>()

    // Convertimos la variable a mutable y LiveData
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    val word: LiveData<String>
        get() = _word


    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish


    // Lista de palabras
    private lateinit var wordList: MutableList<String>

    //Lista de palabras
    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    //Cambiamos el init
    init {
        _word.value = ""
        _score.value = 0
        Log.i("GameViewModel", "GameViewModel created!")
        resetList()
        nextWord()
    }

    //Cuando el viewModel es destruido
    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }

    //Método para actualizar las referencias de los objetos LiveData
    fun onSkip() {
        _score.value = (_score.value)?.minus(1)
        nextWord()
    }
    fun onCorrect() {
        _score.value = (_score.value)?.plus(1)
        nextWord()
    }

    //Pasamos a la siguente palabra de la lista
    private fun nextWord() {
        if (wordList.isEmpty()) {
            onGameFinish()

        } else {
            //Select and remove a _word from the list
            _word.value = wordList.removeAt(0)
        }
    }

    //Métodos para el juego
    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

}
