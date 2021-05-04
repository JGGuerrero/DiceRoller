package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


/**
 * This activity allows the user to roll a dice and see the result on the screen
 * */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Creates rollButton variable which references the button's ID
        val rollButton: Button = findViewById(R.id.button)

        // Creates an onclick listener and executes rollDice when clicked
        rollButton.setOnClickListener { rollDice() }
    }

    /**
     * Roll the dice and update the screen with the result
     * */
    private fun rollDice() {

        val numSides: Int

        // Create a Dice object with 6 sides and roll it
        val dice = Dice(6)
        val diceRoll = dice.roll()

        // Update the screen with the result of the dice roll
        val resultTextView: TextView = findViewById(R.id.textView)
        // Converts the diceRoll into a string so that it can be used in the text view
        resultTextView.text = diceRoll.toString()
    }
}


class Dice(private val numSides: Int) {
    // Creates a random number based on the numSides
    fun roll(): Int {
        return (1..numSides).random()
    }
}