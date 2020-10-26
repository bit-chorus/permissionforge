package com.example.permissionforge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import java.io.File


class MainActivity : AppCompatActivity() {

    // data

    var editTextDict = mutableMapOf<Int,EditText>()

    private fun addUIDictEntry(key:Int){
        editTextDict[key] = findViewById<TextInputLayout>(key).editText!!
    }

    private fun inflateUIDict(){
        addUIDictEntry(R.id.field请假人)
        addUIDictEntry(R.id.field辅导员)
        addUIDictEntry(R.id.field时长)
        addUIDictEntry(R.id.field地点)
        addUIDictEntry(R.id.field原因)
    }

    // init and de-init

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideTitleBar()
        setContentView(R.layout.activity_main)
        inflateUIDict()
        load()
    }

    override fun onDestroy() {
        super.onDestroy()
        save()
    }

    // config loading and saving

    private fun load() {
        val file = File(filesDir, "config.txt")
        if (!file.exists()) {
            return
        }
        val lines = file.readLines()
        var i = 0
        for (entry in editTextDict){
            entry.value.setText(lines[ i])
            i += 1
        }
    }

    private fun save() {
        val file = File(filesDir, "config.txt")
        if (!file.exists()) {
            file.createNewFile()
        }
        file.printWriter().use { out ->
            var i = 0
            for (entry in editTextDict){
                out.println(entry.value.text.toString())
                i += 1
            }
        }
    }


    // ui triggered events
    fun onGeneratePressed(view: View){
        val intent = Intent(this, SimulationActivity::class.java).apply {
            for( entry in editTextDict) {
                putExtra(entry.key.toString(),entry.value.text.toString())
            }
        }
        startActivity(intent)
    }
}