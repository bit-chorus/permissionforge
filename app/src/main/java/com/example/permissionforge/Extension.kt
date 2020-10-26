package com.example.permissionforge

import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.hideTitleBar() {
     this.supportActionBar?.hide();
}