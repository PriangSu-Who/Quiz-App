package com.example.quizapp
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.databinding.ActivityStartQuizBinding

class   StartQuiz : AppCompatActivity() {
    lateinit var binding: ActivityStartQuizBinding

    val quizList = listOf(
        Quiz(
            question = "What is the default visibility modifier for Kotlin classes and their members?",
            option1 = "public",
            option2 = "private",
            option3 = "protected",
            option4 = "internal",
            answer = "public"
        ),
        Quiz(
            question = "Which of the following is used to define a data class in Kotlin?",
            option1 = "class",
            option2 = "data class",
            option3 = "abstract class",
            option4 = "enum class",
            answer = "data class"
        ),
        Quiz(
            question = "How do you declare a variable in Kotlin that cannot be reassigned?",
            option1 = "var",
            option2 = "val",
            option3 = "const",
            option4 = "let",
            answer = "val"
        ),
        Quiz(
            question = "Which Kotlin feature allows you to avoid null pointer exceptions?",
            option1 = "Optionals",
            option2 = "Null Safety",
            option3 = "Try-Catch",
            option4 = "Type Checking",
            answer = "Null Safety"
        )
    )

    var index = 0
    var updateQuestion = 1
    var finished = false

    var skip = -1
    var correct = 0
    var wrong = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityStartQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intentQuestion ()
        binding.nextButton.setOnClickListener{
            showNextQuestion()
        }
    }
    private fun intentQuestion() {

        val quiz = quizList[index]

        binding.apply {
            questionView.text = quiz.question
            radioOption1.text = quiz.option1
            radioOption2.text = quiz.option2
            radioOption3.text = quiz.option3
            radioOption4.text = quiz.option4
        }
    }
    private fun showNextQuestion (){
        checkAnswer()

        binding.apply {
            if (updateQuestion < quizList.size) {
                updateQuestion ++
                intentQuestion()

            } else if (index <= quizList.size -1) {
                index ++
            } else {
                finished = true
            }
            radioGroup.clearCheck()
        }

    }
    private fun checkAnswer() {

        binding.apply {

            if (radioGroup.checkedRadioButtonId == -1) {
                skip ++
            } else {
                 val checkButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
                 val checkAnswer = checkButton.text.toString()

                if (checkAnswer == quizList[index].answer){
                    correct ++
                    showAlert("Correct Answer")
                } else {
                    wrong ++
                    showAlert("Wrong Answer")
                }
            }
            if (index <= quizList.size -1) {
                index ++
            } else {
                showAlert("Quiz Complete")
            }

        }

    }
    fun showAlert(message: String) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle(message)

        builder.setPositiveButton("OK", object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {

                if (message == "Quiz Complete") {
                    val intent = Intent(this@StartQuiz, ResultActivity :: class.java)

                    intent.putExtra("skip", skip)
                    intent.putExtra("correct", correct)
                    intent.putExtra("wrong", wrong)

                    startActivity(intent)
                }
            }

        })

        val alertDialog = builder.create()
        alertDialog.show()

    }

}