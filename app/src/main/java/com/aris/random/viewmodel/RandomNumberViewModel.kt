package com.aris.random.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RandomNumberViewModel : ViewModel() {

    private val numberList = listOf(1, 2, 3, 4, 5, 6)

    // number 1
    private val _stateFlowNumber1 = MutableStateFlow(0)
    val stateFlowNumber1 = _stateFlowNumber1.asStateFlow()

    // number 2
    private val _stateFlowNumber2 = MutableStateFlow(0)
    val stateFlowNumber2 = _stateFlowNumber2.asStateFlow()

    private val _sharedFlowNumber = MutableSharedFlow<String>()
    val sharedFlowNumber = _sharedFlowNumber.asSharedFlow()

    fun getRandomNumber() {
        viewModelScope.launch {
            val number1 = numberList.random()
            _stateFlowNumber1.emit(number1)

            val number2 = numberList.random()
            _stateFlowNumber2.emit(number2)

            _sharedFlowNumber.emit("$number1   $number2")

        }
    }
}


