package com.ytplayer.eaceves

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.lang.IllegalArgumentException

class Picassin : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picassin)

        val btnMen=findViewById<Button>(R.id.btn_menu2)

        btnMen.setOnClickListener(this)

        val imageView=findViewById<ImageView>(R.id.randomImage)
        Picasso.get().load("https://thispersondoesnotexist.com/image").into(imageView)

    }
    override fun onClick(view: View) {
        val intent= when(view.id){
            R.id.btn_menu2-> Intent(this, Menu::class.java)
            else-> throw IllegalArgumentException("Invalid Button")
        }
        startActivity(intent)
    }
}