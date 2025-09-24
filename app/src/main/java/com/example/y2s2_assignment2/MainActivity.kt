package com.example.y2s2_assignment2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.y2s2_assignment2.ui.habits.list.HabitListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HabitListFragment())
                .commit()
        }
    }
}
