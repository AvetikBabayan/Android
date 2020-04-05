package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
enum class Mode {
    None, Add, Minus, Multiply
}

class MainActivity : AppCompatActivity() {

    var lastButtonWasMode =false
    var currentMode = Mode.None
    var labelString = ""
    var savedNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupCalculator()

    }
    fun setupCalculator() {
        val allButtons= arrayOf(number0,number1,number2,number3,number4,number5,number6,number7,number8,number9)
        for (i in allButtons.indices) {
            allButtons[i].setOnClickListener { didPressedNumber(i) }
        }
        iconPlus.setOnClickListener { changeMode(Mode.Add) }
        iconMinus.setOnClickListener { changeMode(Mode.Minus) }
        iconMultiply.setOnClickListener { changeMode(Mode.Multiply) }
        iconEqual.setOnClickListener {didPressedEquals() }
        iconClean.setOnClickListener { didPressedClear() }

    }
    fun didPressedEquals ( ) {
        if (lastButtonWasMode) {
            return
        }
        val intValue = labelString.toInt()

        when (currentMode) {
            Mode.Add -> savedNum += intValue
            Mode.Minus -> savedNum -=intValue
            Mode.Multiply->savedNum*=intValue
            Mode.None->return
        }
        currentMode=Mode.None
        labelString ="$savedNum"
        updateText()
        lastButtonWasMode= true
    }

    fun didPressedClear() {
        lastButtonWasMode =false
        currentMode = Mode.None
        labelString = ""
        savedNum = 0
        mainText1.text = "0"
    }

    fun updateText() {

        if (labelString.length>8) {
            didPressedClear()
            mainText1.text ="BIGGY"
            return
        }

        val intValue = labelString.toInt()
            labelString = intValue.toString()

            if(currentMode == Mode.None) {
                savedNum= intValue
            }
            mainText1.text = labelString

    }
    fun changeMode(mode:Mode) {

        if (savedNum ==0 ) {
            return
        }
        currentMode = mode
        lastButtonWasMode = true

    }
    fun didPressedNumber(num:Int) {
        val strValue = num.toString()
        if(lastButtonWasMode) {
            lastButtonWasMode =false
            labelString = "0"
        }

        labelString = "$labelString$strValue"
        updateText()
    }
}


