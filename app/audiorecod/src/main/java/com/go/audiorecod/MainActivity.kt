package com.go.audiorecod

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.go.audiorecod.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mMediaRecorder: MediaRecorder? = null
    private lateinit var mMediaPlayer: MediaPlayer
    private val requestPermissionCode = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mMediaRecorder = MediaRecorder()
        mMediaPlayer = MediaPlayer()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, Results: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, Results)
        when (requestCode) {
            requestPermissionCode -> if (Results.isNotEmpty()) {
                val storagePermission = Results[0] == PackageManager.PERMISSION_GRANTED
                val recordPermission = Results[1] == PackageManager.PERMISSION_GRANTED
                if (storagePermission && recordPermission) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}