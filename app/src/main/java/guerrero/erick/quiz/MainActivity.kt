package guerrero.erick.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import guerrero.erick.quiz.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val bancoPreguntas = listOf(
        Pregunta(R.string.pregunta_chocolate,false),
        Pregunta(R.string.pregunta_33,false),
        Pregunta(R.string.pregunta_bajas, false),
        Pregunta(R.string.pregunta_proyecto, true)
    )
    private var indice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.trueButton.setOnClickListener { view: View ->

            checkAnswer(true,view)
        }

        binding.falseButton.setOnClickListener { view:View->
            checkAnswer(false,view)
        }

        binding.nextButton.setOnClickListener { view:View ->
            indice = (indice + 1 ) % bancoPreguntas.size
            updateQuestion()
        }
        updateQuestion()
    }

    private fun updateQuestion(){
        val preguntaTextResId = bancoPreguntas[indice].textoPregunta
        binding.questionText.setText(preguntaTextResId)
    }

    private fun checkAnswer(userAnswer:Boolean, view:View){
        val correctAnswer = bancoPreguntas[indice].respuesta
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