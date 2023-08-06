package com.apoplawski.codesamples.articles.permissions

import android.app.Activity
import android.app.Application
import android.os.Bundle

class CurrentActivityProvider(private val application: Application) {

    private var _currentActivity: Activity? = null

    val currentActivity get() = _currentActivity

    fun initialize() {
        println("2137 - activity provider init")
        application.registerActivityLifecycleCallbacks(
            object : Application.ActivityLifecycleCallbacks {
                override fun onActivityResumed(activity: Activity) {
                    println("2137 - resumed, $activity")
                    _currentActivity = activity
                }
                override fun onActivityPaused(activity: Activity) {
                    println("2137 - paused, $activity")
                    _currentActivity = null
                }
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    println("2137 - resumed, $activity")
                    _currentActivity = activity
                }
                override fun onActivityStarted(activity: Activity) {}
                override fun onActivityStopped(activity: Activity) {}
                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
                override fun onActivityDestroyed(activity: Activity) {}
            }
        )
    }
}
