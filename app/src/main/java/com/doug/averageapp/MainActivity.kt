package com.doug.averageapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.doug.averagelibrary.Average
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpSubmitButton()
        setUpGetAverageButton()
    }

    private fun setUpSubmitButton() {
        submitButton.setOnClickListener {
            val numberString = numberEditText.text.toString()
            // check if number is valid
            if (numberString.isNotEmpty() && numberString.isDigitsOnly()) {
                // call the library to add the number
                Average.addNumber(numberString.toInt())
                // clear the input
                numberEditText.text = null
                // show a confirmation saying the number was added
                Toast.makeText(this, getString(R.string.number_added_message), Toast.LENGTH_LONG)
                    .show()
            } else {
                // show error when number is not valid
                Toast.makeText(this, getString(R.string.invalid_number_message), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun setUpGetAverageButton() {
        getAverageButton.setOnClickListener {
            // call the library to get the average
            val average = Average.getAverage()
            // print the average on the screen
            averageTextView.text = getString(R.string.average_message, average)
        }
    }
}
