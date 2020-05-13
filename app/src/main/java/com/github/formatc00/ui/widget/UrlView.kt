package com.github.formatc00.ui.widget

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.formatc00.R

const val HTTP_PREFIX = "http://"

const val HTTPS_PREFIX = "https://"

class UrlView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    
    private val resourceLabel: TextView
    
    private val urlLabel: TextView
    
    private var url: CharSequence? = null
    
    init {
        LayoutInflater.from(context).inflate(R.layout.view_url, this)
        
        resourceLabel = findViewById(R.id.resourceLabel)
        urlLabel = findViewById(R.id.urlLabel)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.UrlView)
        val resource = typedArray.getString(R.styleable.UrlView_resource)
        
        setResource(resource)
        setUrl(typedArray.getString(R.styleable.UrlView_url))
        typedArray.recycle()
        setOnClickListener { openPage(url, context) }
    }
    
    private fun openPage(url: CharSequence?, context: Context) {
        if (url == null || (!url.startsWith(HTTP_PREFIX) && !url.startsWith(HTTPS_PREFIX))) {
            Toast.makeText(context, R.string.url_open_invalid_url, Toast.LENGTH_SHORT).show()
            return
        }
        
        try {
            val webPage = Uri.parse(url.toString())
            val myIntent = Intent(Intent.ACTION_VIEW, webPage)
            context.startActivity(myIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, R.string.url_open_no_app, Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
    
    fun setResource(resource: CharSequence?) {
        resourceLabel.text = resource
    }
    
    fun setUrl(url: CharSequence?) {
        urlLabel.text = url
        this.url = url
    }
}