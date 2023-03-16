package com.go.audiorecod

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaRecorder
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


object PermissionUtils {

    private const val requestPermissionCode = 123

    fun checkPermission(context: Context): Boolean {
        val first = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val first1 = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO)
        return first == PackageManager.PERMISSION_GRANTED && first1 == PackageManager.PERMISSION_GRANTED
    }

    //give below permission for audio capture
    fun requestPermission(activity: Activity) {
       return ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO), requestPermissionCode)
    }

}