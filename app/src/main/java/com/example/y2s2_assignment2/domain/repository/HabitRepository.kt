package com.example.y2s2_assignment2.domain.repository

import android.content.Context
import com.example.y2s2_assignment2.data.prefs.HabitStore
import com.example.y2s2_assignment2.domain.model.Habit
import com.example.y2s2_assignment2.domain.model.ProgressState
import com.example.y2s2_assignment2.util.DateProvider

class HabitRepository(ctx: Context) {
    private val store = HabitStore.get(ctx)

    fun loadHabits(): MutableList<Habit> = store.getHabits()

    fun addHabit(title: String) {
        val list = store.getHabits()
        list.add(Habit(title = title))
        store.saveHabits(list)
    }

    fun deleteHabit(id: String) {
        val updated = store.getHabits().filterNot { it.id == id }
        store.saveHabits(updated)

        val today = DateProvider.today()
        val done = store.getCompletedIds(today)
        if (done.remove(id)) {
            store.setCompletedIds(today, done)
        }
    }

    fun isDoneToday(id: String): Boolean {
        val done = store.getCompletedIds(DateProvider.today())
        return done.contains(id)
    }

    fun toggleDoneToday(id: String) {
        val today = DateProvider.today()
        val done = store.getCompletedIds(today)
        if (done.contains(id)) done.remove(id) else done.add(id)
        store.setCompletedIds(today, done)
    }

    fun progressToday(): ProgressState {
        val total = store.getHabits().size
        val done = store.getCompletedIds(DateProvider.today()).size
        return ProgressState(done, total)
    }
}
