package com.example.simplecalculator.utils

object MathUtils {
    fun add(value1: Double, value2: Double): Double {
        return value1 + value2
    }

    fun subtract(value1: Double, value2: Double): Double {
        return value1 - value2
    }

    fun multiply(value1: Double, value2: Double): Double {
        return value1 * value2
    }

    fun divide(value1: Double, value2: Double): Double {
        if (value2 == 0.0) {
            throw IllegalArgumentException("Cannot divide by zero")
        }
        return value1 / value2
    }
}