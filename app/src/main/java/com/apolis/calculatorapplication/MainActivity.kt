package com.apolis.calculatorapplication

import android.nfc.cardemulation.HostApduService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edit_text_number.isEnabled = false
    }

    private var decidePoint: Boolean = true //true if the point is valid to add
    private var newOperand: Boolean = true //true if we are gonna start a new operand
    private var finalNumber: Any? = 0

    fun numberEvent(view: View){
        try {
            if (newOperand) {
                edit_text_number.setText("")
                decidePoint = true
            }
            newOperand = false
            val buttonClicked = view as Button
            var editTextString: String = edit_text_number.text.toString()

            if(editTextString.length<6){
                when (buttonClicked.id) {
                    //numbers
                    button_zero.id -> {if (editTextString != "0") editTextString += 0}
                    button_one.id -> {if (editTextString == "0") editTextString = "1" else editTextString += 1}
                    button_two.id -> {if (editTextString == "0") editTextString = "2" else editTextString += 2}
                    button_three.id -> {if (editTextString == "0") editTextString = "3" else editTextString += 3}
                    button_four.id -> {if (editTextString == "0") editTextString = "4" else editTextString += 4}
                    button_five.id -> {if (editTextString == "0") editTextString = "5" else editTextString += 5}
                    button_six.id -> {if (editTextString == "0") editTextString = "6" else editTextString += 6}
                    button_seven.id -> {if (editTextString == "0") editTextString = "7" else editTextString += 7}
                    button_eight.id -> {if (editTextString == "0") editTextString = "8" else editTextString += 8}
                    button_nine.id -> {if (editTextString == "0") editTextString = "9" else editTextString += 9}

                    //point
                    button_point.id -> {
                        if (decidePoint) {
                            if (editTextString == "")
                                editTextString = "0."
                            else
                                editTextString += "."
                            decidePoint = false
                        }
                    }
                }
            }
            edit_text_number.setText(editTextString)
            finalNumber = editTextString.toDouble()

        }catch (exception: Exception){
            Toast.makeText(this,exception.message,Toast.LENGTH_SHORT).show()
        }
    }


    fun signChange(view: View){
        val numChangeSign: Double = edit_text_number.text.toString().toDouble() * -1
        edit_text_number.setText(numChangeSign.toString())
        finalNumber = numChangeSign
    }


    var operator = ""
    var firstNumber = "0"


    fun operatorEvent(view: View){
        val buttonClicked = view as Button
        when(buttonClicked.id){
            button_add.id -> {operator = "+"}
            button_minus.id -> {operator = "-"}
            button_multiple.id -> {operator = "*"}
            button_divide.id -> {operator = "/"}
        }
        firstNumber = edit_text_number.text.toString()
        newOperand = true
        decidePoint = true
    }


    fun buttonEqual(view: View){
        try{
            val secondNumber = edit_text_number.text.toString()
            when(operator){
                "+" -> {finalNumber = firstNumber.toDouble() + secondNumber.toDouble() }
                "-" -> {finalNumber = firstNumber.toDouble() - secondNumber.toDouble()}
                "*" -> {finalNumber = firstNumber.toDouble() * secondNumber.toDouble()}
                "/" -> {finalNumber = firstNumber.toDouble() / secondNumber.toDouble()}
            }
            if(finalNumber.toString().length > 6)
                finalNumber = finalNumber.toString().substring(0,5).toDouble()

            edit_text_number.setText(finalNumber.toString())

            operator = ""
            decidePoint = true
            newOperand = true
        }
        catch (exception: Exception){
            Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
        }
    }


    fun buttonPercent(view: View){
        var numPercent: Double = edit_text_number.text.toString().toDouble() / 100
        finalNumber = numPercent
        edit_text_number.setText(finalNumber.toString())
        decidePoint = true
        newOperand = true
    }


    fun buttonClear(view: View){
        edit_text_number.setText("0")
        decidePoint = true
        newOperand = true
    }

}