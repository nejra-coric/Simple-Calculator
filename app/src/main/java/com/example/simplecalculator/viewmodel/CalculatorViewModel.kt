package com.example.simplecalculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.simplecalculator.utils.MathUtils

class CalculatorViewModel: ViewModel() {

    private val _inputValue1 = MutableLiveData<String>()
    private val _inputValue2 = MutableLiveData<String>()
    private val _operator = MutableLiveData<String>()
    private val _result = MutableLiveData<String>()

    val inputValue1: LiveData<String>
        get() = _inputValue1

    val inputValue2: LiveData<String>
        get() = _inputValue2

    val operator: LiveData<String>
        get() = _operator

    val result: LiveData<String>
        get() = _result

    fun onClearButtonClick() {
        _inputValue1.value = ""
        _inputValue2.value = ""
        _operator.value = ""
        _result.value = ""
    }

    fun onDigitButtonClick(digit: Int) {
        // Check if the operator is set
        if (_operator.value.isNullOrEmpty()) {
            // If the operator is not set, update inputValue1
            val newValue = (_inputValue1.value.orEmpty() + digit.toString()).trimStart('0')
            _inputValue1.value = newValue
        } else {
            // If the operator is set, update inputValue2
            val newValue = (_inputValue2.value.orEmpty() + digit.toString()).trimStart('0')
            _inputValue2.value = newValue
        }
    }

    fun onOperatorButtonClick(operator: String) {
        if (!inputValue2.value.isNullOrEmpty()) {
            onEqualButtonClick()
        }
        _operator.value = operator
    }

    fun onEqualButtonClick() {
        val value1 = _inputValue1.value?.toDoubleOrNull() ?: return
        val value2 = _inputValue2.value?.toDoubleOrNull() ?: return

        val result = when (_operator.value) {
            "+" -> performAddition(value1,value2)
            "-" -> performSubtraction(value1, value2)
            "×" -> performMultiplication(value1,value2)
            "÷" -> performDivision(value1, value2)
            else -> Double.NaN
        }

        _result.value = result.toString()
    }

    private fun performAddition(value1: Double, value2: Double): Double {
        return MathUtils.add(value1, value2)
    }

    private fun performSubtraction(value1: Double, value2: Double): Double {
        return MathUtils.subtract(value1, value2)
    }

    private fun performMultiplication(value1: Double, value2: Double): Double {
        return MathUtils.multiply(value1, value2)
    }

    private fun performDivision(value1: Double, value2: Double): Double {
        return MathUtils.divide(value1, value2)
    }
}