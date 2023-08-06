package com.apoplawski.codesamples.articles.permissions

import android.Manifest
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class PermissionRequester {

    private var requestLauncher: ActivityResultLauncher<String>? = null

    fun initialize(activity: ComponentActivity) {
        requestLauncher = activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            // Handle user response
        }
    }

    fun tryRequest() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return

        requestLauncher?.launch(Manifest.permission.POST_NOTIFICATIONS)
    }
}