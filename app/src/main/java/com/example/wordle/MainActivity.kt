package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.wordle.FourLetterWordList



class MainActivity : AppCompatActivity() {

    private val guesses = mutableListOf<String>()
    private val results = mutableListOf<String>()
    private val words = mutableListOf<String>()
    val wordToGuess = FourLetterWordList.getRandomFourLetterWord()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
     fun handleSubmit(view: View?) {


        val targetWordTextView: TextView = findViewById(R.id.targetWordTextView)


        /** Now I have to catch the user's input and compare it to the random word. */
        val guessWordTextView: TextView = findViewById(R.id.guessEditText)
         val feedbackTextView: TextView = findViewById(R.id.feedbackTextView)
        val guessWord = guessWordTextView.text.toString().uppercase()

        val submitButton: Button = findViewById(R.id.submitGuessButton)

        if (guessWord.length != 4) {
            Log.d("GuessWord", "Please enter a 4 letter word")
        }
        else {
            val result = checkGuess(guessWord)
            feedbackTextView.text = result
            results.add(result)
            guesses.add(guessWord)
            guessWordTextView.text = ""
            submitButton.text ="Keep Guessing"


            if(guesses.size == 3){
                showResults()
                submitButton.isEnabled = false
                submitButton.text ="No more guesses!"
            }
            Log.d("GuessWord", result)
        }
    }


    fun showResults() {
        val resultTextView: TextView = findViewById(R.id.resultTextView)
        val stringBuilder = StringBuilder()
        for (i in guesses.indices) {
            stringBuilder.append("#${i + 1} Guess:  ${guesses[i]}  Target Word: ${words[i]}\n\n")
        }
        resultTextView.text = stringBuilder.toString()
        Toast.makeText(this, "Game Over: No more guesses!", Toast.LENGTH_SHORT).show()
    }

    private fun checkGuess(guess: String) : String {

        Log.d("WordToGuess", wordToGuess)
        words.add(wordToGuess)
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}