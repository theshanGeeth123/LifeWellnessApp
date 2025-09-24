package com.example.y2s2_assignment2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.y2s2_assignment2.ui.habits.list.HabitListFragment
import com.example.y2s2_assignment2.ui.mood.MoodListFragment
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HabitListFragment())
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_habits -> {
                supportActionBar?.title = getString(R.string.habits)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, HabitListFragment())
                    .commit()
                true
            }
            R.id.menu_mood -> {
                supportActionBar?.title = getString(R.string.mood_journal)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, MoodListFragment())
                    .commit()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
