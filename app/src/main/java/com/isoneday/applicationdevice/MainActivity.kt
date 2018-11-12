package com.isoneday.applicationdevice

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainActivity : AppCompatActivity() {
  //deklarasi context agar semua activity ngeload context yang sama
    private var context: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//inisialisasi context
        context = this
        //function perpindahan halaman

        //aksi ketika tombol button diklik
        btnalarm.onClick { moveClass(AlarmActivity::class.java) }
        btnaudiomanager.onClick { moveClass(AudioManager::class.java) }
        btnbrowser.onClick { moveClass(AudioManager::class.java) }
        btnphone.onClick { moveClass(CallPhoneActivity::class.java) }
        btncamera.onClick { moveClass(CameraActivity::class.java) }
        btnemail.onClick { moveClass(EmailActivity::class.java) }
        btnsms.onClick { moveClass(SmsActivity::class.java) }
        btntts.onClick { moveClass(TTSActivity::class.java) }
        btnvideo.onClick { moveClass(VideoActivity::class.java) }
    }
    fun moveClass(kelastujuan: Class<*>) {
        startActivity(Intent(context, kelastujuan)) }

}
