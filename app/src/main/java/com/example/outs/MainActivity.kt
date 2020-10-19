package com.example.outs

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileWriter



class MainActivity : AppCompatActivity() {

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        this.getSupportActionBar()?.hide();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadConfig()
    }
    private fun saveConfig(){
        val file = File(filesDir, "config.txt")
        if(!file.exists()){
            file.createNewFile()
        }
        file.printWriter().use { out ->
            out.println(edt_stu_name.text.toString())
            out.println(edt_teacher_name.text.toString())
            out.println(edt_duration.text.toString())
            out.println(edt_location.text.toString())
            out.println(edt_reason.text.toString())
        }
    }

    private fun loadConfig(){
        val file = File(filesDir, "config.txt")
        if(!file.exists()){
            return
        }
        val lines = file.readLines()
        if(lines.size == 5){
            edt_stu_name.setText(lines[0])
            edt_teacher_name.setText(lines[1])
            edt_duration.setText(lines[2])
            edt_location.setText(lines[3])
            edt_reason.setText(lines[4])
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        saveConfig()
    }

    fun onBtnPressed(view: View){
        val intent = Intent(this, SimulationActivity::class.java).apply {
            putExtra("duration",edt_duration.text.toString())
            putExtra("reason",edt_reason.text.toString())
            putExtra("location",edt_location.text.toString())
            putExtra("name",edt_stu_name.text.toString())
            putExtra("teacherName",edt_teacher_name.text.toString())

        }
        startActivity(intent)
    }
}