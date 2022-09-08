package guerrero.erick.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import guerrero.erick.quiz.databinding.ActivityMainBinding

private const val TAG="MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val quizViewModel:QuizViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "Tengo un viewModel: ${quizViewModel}")

        binding.trueButton.setOnClickListener { view: View ->

            checkAnswer(true,view)
        }

        binding.falseButton.setOnClickListener { view:View->
            checkAnswer(false,view)
        }

        binding.nextButton.setOnClickListener { view:View ->
            quizViewModel.siguientePregunta()
            updateQuestion()
        }
        updateQuestion()
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"En el onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"En el onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"En el onDestroy")
    }

    private fun updateQuestion(){
        val preguntaTextResId = quizViewModel.currentQuestionText
        binding.questionText.setText(preguntaTextResId)
    }

    private fun checkAnswer(userAnswer:Boolean, view:View){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = if (userAnswer == correctAnswer){
            R.string.correct_toast
        }else{
            R.string.incorrect_toast
        }
        val colorBackground = if(userAnswer == correctAnswer){
            R.color.verde
        }else{
            R.color.rojo
        }
        val mySnack = Snackbar.make(view,messageResId,Snackbar.LENGTH_LONG)
        mySnack.setBackgroundTint(getColor(colorBackground))
        mySnack.show()
    }
}