package com.example.simplecalculator.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.simplecalculator.R
import com.example.simplecalculator.databinding.ActivityMainBinding
import com.example.simplecalculator.viewmodel.CalculatorViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var calculatorViewModel: CalculatorViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        calculatorViewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)
        calculatorViewModel.inputValue1.observe(this, Observer { value ->
            binding.textResult.text = value
        })

        calculatorViewModel.inputValue2.observe(this, Observer { value ->
            binding.textResult.text = value
        })

        calculatorViewModel.operator.observe(this, Observer { value ->
            binding.textResult.text = value
        })

        calculatorViewModel.result.observe(this, Observer { value ->
            binding.textResult.text = value
        })
        setUpButtonClickListeners()
    }

    private fun setUpButtonClickListeners() {
        val buttons = arrayOf(
            binding.button0, binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6, binding.button7,
            binding.button8, binding.button9, binding.buttonDivide, binding.buttonDot,
            binding.buttonEqual, binding.buttonMinus, binding.buttonPlus, binding.buttonMultiply
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                handleButtonClick(button.text.toString())
            }
        }
    }

    private fun handleButtonClick(buttonText: String) {
        with(binding) {
            when {
                buttonText.length == 1 && Character.isDigit(buttonText[0]) -> {
                    val digit = buttonText.toInt()
                    calculatorViewModel.onDigitButtonClick(digit)
                }
                buttonText == "=" -> {
                    calculatorViewModel.onEqualButtonClick()
                }
                else -> {
                    calculatorViewModel.onOperatorButtonClick(buttonText)
                }
            }
        }
    }
}
