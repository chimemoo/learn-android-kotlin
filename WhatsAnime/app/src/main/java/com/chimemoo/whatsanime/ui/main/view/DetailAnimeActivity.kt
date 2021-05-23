package com.chimemoo.whatsanime.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.core.app.NavUtils
import com.chimemoo.whatsanime.R
import kotlinx.android.synthetic.main.activity_detail_anime.*

class DetailAnimeActivity : AppCompatActivity() {

    private val EXTRA_ANIME_ID ="EXTRA_ANIME_ID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_anime)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val anilistId = intent.getIntExtra(EXTRA_ANIME_ID, 0)
        web_view.settings.javaScriptEnabled = true
        web_view.settings.domStorageEnabled = true
        web_view.webChromeClient = WebChromeClient()
        web_view.webViewClient = WebViewClient()
        web_view.loadUrl("https://anilist.co/anime/${anilistId}")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}