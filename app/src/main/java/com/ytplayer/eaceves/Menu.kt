package com.ytplayer.eaceves

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.youtube.player.YouTubeStandalonePlayer
import java.lang.IllegalArgumentException

class Menu : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnSingle=findViewById<Button>(R.id.btn_single)
        val btnPlaylist=findViewById<Button>(R.id.btn_Playlist)
        val btnList=findViewById<Button>(R.id.btn_lista)

        btnSingle.setOnClickListener(this)
        btnPlaylist.setOnClickListener(this)
        btnList.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val intent= when(view.id){
            R.id.btn_single->YouTubeStandalonePlayer.createVideoIntent(
                this, getString(R.string.GOOGLE_API_KEY), YOUTUBE_ID_KEY)
            R.id.btn_Playlist->YouTubeStandalonePlayer.createPlaylistIntent(
                this, getString(R.string.GOOGLE_API_KEY), PLAYLIST_ID_KEY)
            R.id.btn_lista-> Intent(this, GameFeed::class.java)
            else-> throw IllegalArgumentException("Invalid Button")
        }
        startActivity(intent)
    }
}