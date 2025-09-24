package com.example.y2s2_assignment2.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.y2s2_assignment2.data.prefs.HydrationPrefs
import com.example.y2s2_assignment2.notifications.NotificationHelper

class HydrationWorker(
    ctx: Context,
    params: WorkerParameters
) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        // Show only if user has it enabled
        val prefs = HydrationPrefs(applicationContext)
        if (prefs.isEnabled()) {
            NotificationHelper.showHydration(applicationContext)
        }
        return Result.success()
    }
}
