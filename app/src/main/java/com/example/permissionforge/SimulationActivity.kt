package com.example.permissionforge

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextClock
import android.widget.TextView
import java.util.*

class SimulationActivity : AppCompatActivity() {

    // init and de-init

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideTitleBar()
        setContentView(R.layout.activity_simulation)
        initUI()
    }

    private fun initUI(){

        // init clock

        val clock = findViewById<TextClock>(R.id.txtclk)
        clock.setTextColor(Color.WHITE)
        clock.format24Hour = "当前时间:yyyy-MM-dd HH:mm:ss"
        clock.format12Hour = null
        // init text

        val dur = intent.getStringExtra(R.id.field时长.toString())?.toIntOrNull() ?: 3
        val startTime = Calendar.getInstance()
        val endTime = Calendar.getInstance()
        endTime.add(Calendar.HOUR,dur)

        val startTimeStr =  "${startTime.get(Calendar.MONTH)+1}-${startTime.get(Calendar.DAY_OF_MONTH)} ${startTime.get(Calendar.HOUR_OF_DAY)}:00"
        val endTimeStr = "${endTime.get(Calendar.MONTH)+1}-${endTime.get(Calendar.DAY_OF_MONTH)} ${endTime.get(Calendar.HOUR_OF_DAY)}:00"

        val tel = "18${randd()}${randd()}${randd()}${randd()}${randd()}${randd()}${randd()}${randd()}${randd()}"

        val reason = intent.getStringExtra(R.id.field原因.toString())!!
        val location = intent.getStringExtra(R.id.field地点.toString())!!
        val name = intent.getStringExtra(R.id.field请假人.toString())!!
        val teacherName = intent.getStringExtra(R.id.field辅导员.toString())!!

        val text = t_fld("请假类型：")+t_val("事假　　　　　") +t_fld("需要离校：")+t_val("离校")+nl+
                t_fld("销假规则：")+t_emp("离校请假需要销假，非离校请假无需销假")+nl+
                t_fld("实际休假时间：") + t_val("$startTimeStr ~ $endTimeStr （共 $dur 小时）")
        findTextView(R.id.txt_top).text = Html.fromHtml(text)


        val text2 = t_fld("开始时间：　 ")+t_val(startTimeStr)+nl+
                t_fld("结束时间：　 ")+t_val(endTimeStr)+nl+
                t_fld("审批流程：　 ")+t_val("共1步 ") + t_lnk("查看>")+nl+
                t_fld("紧急联系人： ")+t_val(tel)+nl+
                t_fld("请假原因：　 ")+t_val(reason)+nl+
                t_fld("发起位置：　 ")+t_lnk(location)+nl+
                t_fld("抄送人：　　 ")+t_val("无")
        findTextView(R.id.txt_mid).text =  Html.fromHtml(text2)
        findTextView(R.id.txt_initiate).text = Html.fromHtml(t_fld("$name - 发起申请"))
        findTextView(R.id.txt_passed).text = Html.fromHtml(t_fld("一级：[辅导员]$teacherName - 审批通过"))
    }

    //region 代码粪坑
    fun findTextView(id:Int) : TextView{
        return  findViewById<TextView>(id)
    }

    val COLOR_FIELD = "#787878"
      val COLOR_VALUE = "#000000"
      val COLOR_LINK = "#97cbfa"
      val COLOR_EMPHASIS = "#fbcc42"
      val COLOR_OK = "#04d351"
      val COLOR_WHITE = "#ffffff"

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
        return (0..9).random()
    }

    private val nl = "<br/>"
    //endregion
}