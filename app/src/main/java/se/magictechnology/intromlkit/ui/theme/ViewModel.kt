package se.magictechnology.intromlkit.ui.theme

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions


class FancyViewMode(private val context: Context) :ViewModel(){
    private val _resultList = MutableLiveData<List<String>>()

    val result: LiveData<List<String>> = _resultList

    fun updateMocktailCocktail(newData: List<String>) {
        _resultList.value = newData
    }
    fun runTextRecognition(id : Int ) {
        Log.i("MLKITDEBUG","START")

        var bitmap = BitmapFactory.decodeResource(context.resources,id)

        val image = InputImage.fromBitmap(bitmap, 0)
        var textRecognizerOptions = TextRecognizerOptions.Builder().build()
        val recognizer = TextRecognition.getClient(textRecognizerOptions)
        //mTextButton.setEnabled(false)
        recognizer.process(image)
            .addOnSuccessListener { texts ->
                //mTextButton.setEnabled(true)
                Log.i("MLKITDEBUG","SUCSSES")

                processTextRecognitionResult(texts)
            }
            .addOnFailureListener { e -> // Task failed with an exception
                //mTextButton.setEnabled(true)

                e.printStackTrace()
                Log.i("MLKITDEBUG","FAILED")
            }
    }

    fun processTextRecognitionResult(texts: Text){
        val blocks: List<Text.TextBlock> = texts.getTextBlocks()
        val resultList = mutableListOf<String>()
        if (blocks.size == 0) {
            //showToast("No text found")
            Log.i("MLKITDEBUG","NoTEXT")

            return
        }
        //mGraphicOverlay.clear()
        for (i in blocks.indices) {
            val lines: List<Text.Line> = blocks[i].getLines()
            for (j in lines.indices) {
                val elements: List<Text.Element> = lines[j].getElements()
                for (k in elements.indices) {
                    val elementText = elements[k].text
                    resultList.add(elementText)

                    Log.i("MLKITDEBUG",elements[k].text + " " + elements[k].confidence.toString())

                }
            }
        }

        _resultList.value = resultList
    }

}