package com.example.outs
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.os.Bundle
import android.os.SystemClock
import android.text.Html
import android.widget.Chronometer
import android.widget.TextClock
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_simulation.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val COLOR_FIELD = "#787878"
const val COLOR_VALUE = "#000000"
const val COLOR_LINK = "#97cbfa"
const val COLOR_EMPHASIS = "#fbcc42"
const val COLOR_OK = "#04d351"
const val COLOR_WHITE = "#ffffff"

class SimulationActivity : AppCompatActivity() {
    // entry point
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation)
        setTexts()
        setGif()
        setTimer()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    fun setTimer(){
        val clock = findViewById<TextClock>(R.id.txtclk)
        clock.setTextColor(Color.WHITE)
    }


    private fun setGif(){
        val src = ImageDecoder.createSource(resources, R.drawable.on_vacation_bk)
        val drawable = ImageDecoder.decodeDrawable(src)
        img_onVacationBk.setImageDrawable(drawable)
        if (drawable is AnimatedImageDrawable) {
           drawable.start()
        }
    }

    private fun hideSystemUI() {
        this.getSupportActionBar()?.hide();
    }

    private fun coloredText(color: String, str: String) : String{
        return "<font color=$color>$str</font>"
    }

    private fun t_fld(str: String) : String{
        return coloredText(COLOR_FIELD, str)
    }

    private fun t_val(str: String) : String{
        return coloredText(COLOR_VALUE, str)
    }

    private fun t_lnk(str: String) : String{
        return coloredText(COLOR_LINK, str)
    }

    private fun t_emp(str: String) : String{
        return coloredText(COLOR_EMPHASIS, str)
    }

    private fun t_ok(str: String) : String{
        return coloredText(COLOR_OK, str)
    }

    private fun t_white(str: String) : String{
        return coloredText(COLOR_WHITE, str)
    }

    private fun randd() : Int{
        return (0..10).random()
    }

    private val nl = "<br/>"

    private fun setTexts(){
        val mon = LocalDateTime.now().monthValue
        val day = LocalDateTime.now().dayOfMonth
        val hr_st = LocalDateTime.now().hour
        val dur = intent.getStringExtra("duration")?.toIntOrNull() ?: 3
        val hr_ed = LocalDateTime.now().hour + dur
        val time_st = "$mon-$day $hr_st:00"
        val time_ed = "$mon-$day $hr_ed:00"
        val tel = "189${randd()}${randd()}${randd()}${randd()}${randd()}${randd()}${randd()}${randd()}"
        val reason = intent.getStringExtra("reason")!!
        val location = intent.getStringExtra("location")!!
        val name = intent.getStringExtra("name")!!
        val teacherName = intent.getStringExtra("teacherName")!!

        val text = t_fld("请假类型：")+t_val("事假　　　　　") +t_fld("需要离校：")+t_val("离校")+nl+
                    t_fld("销假规则：")+t_emp("离校请假需要销假，非离校请假无需销假")+nl+
                t_fld("实际休假时间：") + t_val("$time_st ~ $time_ed （共 $dur 小时）")
       txt_top.text = Html.fromHtml(text)


        val text2 = t_fld("开始时间：　 ")+t_val(time_st)+nl+
                t_fld("结束时间：　 ")+t_val(time_ed)+nl+
                t_fld("审批流程：　 ")+t_val("共1步 ") + t_lnk("查看>")+nl+
                t_fld("紧急联系人： ")+t_val(tel)+nl+
                t_fld("请假原因：　 ")+t_val(reason)+nl+
                t_fld("发起位置：　 ")+t_lnk(location)+nl+
                t_fld("抄送人：　　 ")+t_val("无")
        txt_mid.text =  Html.fromHtml(text2)
        txt_initiate.text = Html.fromHtml(t_fld("$name - 发起申请"))
        txt_passed.text = Html.fromHtml(t_fld("一级：[辅导员]$teacherName - 审批通过"))

    }
}