package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast


/**
 * This activity allows the user to roll a dice and see the result on the screen
 * */
class MainActivity : AppCompatActivity() {

    /** global variable for keeping track of the number of sides of the dice */
    private var numSides = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Creates rollButton variable which references the button's ID
        val rollButton: Button = findViewById(R.id.button)
        // Creates an onclick listener and executes rollDice when clicked
        rollButton.setOnClickListener { rollDice() }

        /** Sets the number of sides TextView to the default(2) */
        var sides: TextView = findViewById(R.id.numSides)
        sides.text = numSides.toString()

        /** Sets onclick listeners to the plus and minus buttons */
        val minusSides: Button = findViewById(R.id.minusButton)
        val plusSides: Button = findViewById(R.id.plusButton)
        minusSides.setOnClickListener { deleteSides() }
        plusSides.setOnClickListener { addSides() }

        /** do a dice roll when the app starts */
        rollDice()

    }

    /** Subtracts from the number of sides TextView in increments of 1 */
    private fun deleteSides() {
        // sets sides to the number of sides TextView
        var sides: TextView = findViewById(R.id.numSides)

        // Subtracts sides only if there are more than two sides, otherwise makes toast explaining that you cannot subtract anymore
        if (numSides > 2) {
            numSides -= 1
        } else {
            Toast.makeText(this, "Dice must have more than one side", Toast.LENGTH_SHORT).show()
        }
        // Updates the numSides TextView to the new amount of sides
        sides.text = numSides.toString()
    }

    /** Adds to the number of sides TextView in increments of 1 */
     private fun addSides() {
        var sides: TextView = findViewById(R.id.numSides)
        numSides += 1
        sides.text = numSides.toString()
    }

    /**
     * Roll the dice and update the screen with the result
     * */
    private fun rollDice() {

        // Create a Dice object with numSides sides and rolls it
        val dice = Dice(numSides)
        val diceRoll = dice.roll()

        // Creates reference to the dice ImageView
        val diceImage: ImageView = findViewById(R.id.diceImage)
        /**
         * This is how you set the dice image to a drawable resource:
         * ~~~~~~~~diceImage.setImageResource(R.drawable.dice_1)~~~~~~~~
         */
        /** Replaces the ImageView with dice images depending on diceRoll */
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        /** Update the ImageView with the correct drawable resource ID */
        diceImage.setImageResource(drawableResource)

        /** updates the image content description to whatever the dice roll is */
        diceImage.contentDescription = diceRoll.toString()

        // Toast.makeText(this, "Your $numSides sided dice has been rolled...", Toast.LENGTH_SHORT).show()

        // Update the screen with the result of the dice roll
        val resultTextView: TextView = findViewById(R.id.textView)
        // Converts the diceRoll into a string so that it can be used in the text view
        resultTextView.text = diceRoll.toString()
    }
}

/** Takes numSides as parameter for dice objects */
class Dice(private val numSides: Int) {
    // Creates a random number based on the numSides
    fun roll(): Int {
        return (1..numSides).random()
    }
}