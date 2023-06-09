package com.example.phoneshop.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.phoneshop.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FragmentMain.newInstance()).commit()
        }
    }
}