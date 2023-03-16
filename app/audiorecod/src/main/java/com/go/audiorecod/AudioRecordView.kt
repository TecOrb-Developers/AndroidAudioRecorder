package com.go.audiorecod

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import java.io.IOException
import java.util.*

class AudioRecordView(context: Context, private val callbacks: CallBack) : Dialog(context) {

    private var mMediaRecorder: MediaRecorder? = null
    private lateinit var mMediaPlayer: MediaPlayer
    private var audioSave = ""

    private fun showDialog() {
        mMediaRecorder = MediaRecorder()
        mMediaPlayer = MediaPlayer()
        setContentView(R.layout.recording_dialog)
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)

        val clRecordView = findViewById<ConstraintLayout>(R.id.clRecordView)
        val playRecording = findViewById<ConstraintLayout>(R.id.playRecording)
        val btnStop = findViewById<Button>(R.id.btnStop)
        val pauseRecording = findViewById<ConstraintLayout>(R.id.pauseRecording)
        val audioDelete = findViewById<ConstraintLayout>(R.id.audioDelete)
        val imgClose = findViewById<ImageView>(R.id.imgClose)
        val btnOk = findViewById<Button>(R.id.btnOk)

        imgClose.setOnClickListener {
            dismiss()
        }

        btnOk.setOnClickListener {
            callbacks.audioData(audioSave)
            dismiss()
        }

        val mediaPlayer: MediaPlayer = MediaPlayer.create(context, audioSave.toUri())
        if (mediaPlayer != null) {
            mediaPlayer.setOnCompletionListener {
                audioDelete.visibility = View.VISIBLE
                pauseRecording.visibility = View.GONE
                Log.d("@@audio", "initListener: ")
            }

        }

        clRecordView.setOnClickListener {
            if (PermissionUtils.checkPermission(context)) {
                val uuid = Date().time.toString()
                audioSave = context.externalCacheDir?.absolutePath + "/" + uuid + ".3gp"
                recordReady()
                try {
/*                    lifecycleScope.async {
                        mMediaRecorder?.prepare()
                    }*/
                    mMediaRecorder?.prepare()
                    mMediaRecorder!!.start()
                } catch (e: IllegalStateException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                clRecordView?.visibility = View.GONE
                btnStop?.visibility = View.VISIBLE
                Toast.makeText(context, "Recording Started", Toast.LENGTH_SHORT).show()
            } else {
                PermissionUtils.requestPermission(Activity())
            }
        }

        playRecording.setOnClickListener {
            mMediaPlayer = MediaPlayer()
            clRecordView?.visibility = View.GONE
            pauseRecording?.visibility = View.VISIBLE
            playRecording?.visibility = View.GONE
            try {
                if (audioSave.isNotEmpty()) {
                    mMediaPlayer.setDataSource(audioSave)
/*                    lifecycleScope.launch {
                        mMediaPlayer.prepare()
                    }*/
                    mMediaPlayer.prepare()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            // play recording here
            mMediaPlayer.start()
            Toast.makeText(context, "Recording Playing", Toast.LENGTH_SHORT).show()
        }

        btnStop.setOnClickListener {
            mMediaRecorder?.stop()
            btnStop?.visibility = View.GONE
            btnOk?.visibility = View.VISIBLE
            playRecording?.visibility = View.VISIBLE
            clRecordView?.visibility = View.GONE
            pauseRecording?.visibility = View.GONE
            audioDelete?.visibility = View.VISIBLE

            Toast.makeText(context, "Recording Completed", Toast.LENGTH_SHORT).show()
        }

        pauseRecording.setOnClickListener {
            mMediaPlayer = MediaPlayer()
            btnStop?.visibility = View.GONE
            clRecordView?.visibility = View.GONE
            pauseRecording?.visibility = View.GONE
            playRecording?.visibility = View.VISIBLE

            mMediaPlayer.stop()
            mMediaPlayer.release()
        }

        playRecording.visibility = View.GONE
        btnStop.visibility = View.GONE
        pauseRecording.visibility = View.GONE

        audioDelete.setOnClickListener {
            if (audioSave.isNotEmpty()) {
                audioSave = ""
                playRecording.visibility = View.GONE
                pauseRecording.visibility = View.GONE
                clRecordView.visibility = View.VISIBLE
                btnOk?.visibility = View.GONE
                audioDelete.visibility = View.GONE
                Toast.makeText(context, "Recording deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Recording not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun recordReady() {
        mMediaRecorder = MediaRecorder()
        mMediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mMediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mMediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mMediaRecorder?.setOutputFile(audioSave)
    }


    init {
        showDialog()
    }

}