package com.ytplayer.eaceves

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalArgumentException
import java.net.URL
import kotlin.properties.Delegates

class FeedEntry {
    var name: String = ""
    var artist: String = ""
    var releaseDate: String = ""
    var summary: String = ""
    var imageUrl: String = ""

    override fun toString(): String {
        return """
            name = $name
            artist = $artist
            releaseDate = $releaseDate
            summary = $summary
            imageUrl = $imageUrl
        """.trimIndent()
    }
}

class GameFeed : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_feed)

        val btnMenu=findViewById<Button>(R.id.btn_menu)

        btnMenu.setOnClickListener(this)

        val recyclerView: RecyclerView = findViewById(R.id.xmlRecyclerView)

        Log.d(ContentValues.TAG, "onCreate")

        val downloadData = DownloadData(this, recyclerView)
        downloadData.execute("https://mynintendonews.com/feed/")
        Log.d(ContentValues.TAG, "onCreate DONE")
    }

    companion object {
        private class DownloadData (context: Context, recyclerView: RecyclerView): AsyncTask<String, Void, String>() {
            private val TAG = "DownloadData"

            var localContext: Context by Delegates.notNull()
            var localRecyclerView: RecyclerView by Delegates.notNull()

            init{
                localContext = context
                localRecyclerView = recyclerView
            }

            override fun doInBackground(vararg url: String?): String {
                Log.d(TAG, "doInBackground")
                val rssFeed = downloadXML(url[0])
                if (rssFeed.isEmpty()) {
                    Log.e(TAG, "doInBackground: failed")
                }
                Log.d(TAG, rssFeed)
                return rssFeed
            }

            override fun onPostExecute(result: String) {
                super.onPostExecute(result)
                Log.d(TAG, "onPostExecute")
                val parsedApplication = ParseApplication()
                parsedApplication.parse(result)

                val adapter:ApplicationsAdapter=ApplicationsAdapter(localContext, parsedApplication.applications)
                localRecyclerView.adapter=adapter
                localRecyclerView.layoutManager= LinearLayoutManager(localContext)
            }

            private fun downloadXML(urlPath: String?):String{
                return URL(urlPath).readText()
            }
        }
    }

    override fun onClick(view: View) {
        val intent =when(view.id){
            R.id.btn_menu-> Intent(this, Menu::class.java)
            else->throw IllegalArgumentException("Invalid Button")
        }
        startActivity(intent)
    }
}