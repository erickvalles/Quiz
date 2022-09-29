package guerrero.erick.quiz

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


private const val TAG = "QuizViewModel"
public const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
class QuizViewModel(private val savedStateHandle:SavedStateHandle): ViewModel() {
    private val bancoPreguntas = listOf(
        Pregunta(R.string.pregunta_chocolate,false),
        Pregunta(R.string.pregunta_33,false),
        Pregunta(R.string.pregunta_bajas, false),
        Pregunta(R.string.pregunta_proyecto, true)
    )
    private var indice:Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY,value)

    val currentQuestionAnswer:Boolean
        get() = bancoPreguntas[indice].respuesta
    val currentQuestionText:Int
        get() = bancoPreguntas[indice].textoPregunta

    fun siguientePregunta(){

        indice = (indice+1)%bancoPreguntas.size
    }
}