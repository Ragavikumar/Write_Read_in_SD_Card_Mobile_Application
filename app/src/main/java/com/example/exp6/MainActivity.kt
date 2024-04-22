package com.example.exp6

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var Input: EditText
    private lateinit var dis: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Input = findViewById(R.id.inputtxt)
        dis = findViewById(R.id.readtxt)

        val btnSave: Button = findViewById(R.id.buttonSave)
        val btnLoad: Button = findViewById(R.id.buttonread)

        btnSave.setOnClickListener {
            saveTextToFile(Input.text.toString())
        }

        btnLoad.setOnClickListener {
            dis.text = loadTextFromFile()
        }
    }

    private fun saveTextToFile(text: String) {
        val file = File(filesDir, "saved_text.txt")
        try {
            val fos = FileOutputStream(file)
            fos.write(text.toByteArray())
            fos.close()
            Input.text.clear()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun loadTextFromFile(): String {
        val file = File(filesDir, "saved_text.txt")
        return try {
            val fis = FileInputStream(file)
            val isr = fis.reader()
            val text = isr.readText()
            fis.close()
            text
        } catch (e: IOException) {
            e.printStackTrace()
            "Error loading text"
        }
    }
}
