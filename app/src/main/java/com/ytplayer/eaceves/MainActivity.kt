package com.ytplayer.eaceves

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSinglePlay=findViewById<Button>(R.id.btn_playVideo)
        val btnMenu=findViewById<Button>(R.id.btn_Menu)

        btnSinglePlay.setOnClickListener(this)
        btnMenu.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val intent =when(view.id){
            R.id.btn_playVideo->Intent(this, YouTubePlayerActivity::class.java)
            R.id.btn_Menu->Intent(this, Menu::class.java)
            else->throw IllegalArgumentException("Invalid Button")
        }
        startActivity(intent)
    }


}