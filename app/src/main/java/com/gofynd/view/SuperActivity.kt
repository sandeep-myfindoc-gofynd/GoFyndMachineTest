package com.gofynd.view

import android.R
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

open class SuperActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun updateToolbar(toolbartext: String?) {
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = toolbartext
        }
    }

    fun updateToolbarWithoutBackButton(toolbartext: String?) {
        if (supportActionBar != null) {
            supportActionBar!!.title = toolbartext
        }
    }

    fun hideToolbar() {
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
    }
}