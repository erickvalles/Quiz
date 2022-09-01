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
            val mySnack = Snackbar.make(view,R.string.correct_toast,Snackbar.LENGTH_LONG)
            mySnack.setBackgroundTint(resources.getColor(R.color.naranja))
            mySnack.show()
        }

        binding.falseButton.setOnClickListener { view:View->
            Toast.makeText(this,R.string.incorrect_toast,Toast.LENGTH_LONG).show()
        }

        binding.nextButton.setOnClickListener { view:View ->
            indice = (indice + 1 ) % bancoPreguntas.size
            val preguntaTextResId = bancoPreguntas[indice].textoPregunta
            binding.questionText.setText(preguntaTextResId)
        }

        val preguntaTextResId = bancoPreguntas[indice].textoPregunta
        binding.questionText.setText(preguntaTextResId)



    }
}