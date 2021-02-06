package com.gofynd.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson

class Prefs internal constructor(context: Context){
    var preferences: SharedPreferences
    var editor: SharedPreferences.Editor
    /*constructor(context: Context){
        Log.d("Inside","Secondry constructor")
        Prefs.preferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE)
        Prefs.editor = Prefs.preferences.edit()
    }*/
    // To get object from prefrences
    fun <T> getObject(key: String, a: Class<T>?): T? {
        val gson = preferences.getString(key, null)
        return if (gson == null) {
            null
        } else {
            try {
                GSON.fromJson(gson, a)
            } catch (e: Exception) {
                throw IllegalArgumentException("Object storaged with key "
                        + key + " is instanceof other class")
            }
        }
    }
    fun save(key: String?, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }
    fun save(key: String?, value: String?) {
        editor.putString(key, value).apply()
    }
    fun save(key: String?, value: Int) {
        editor.putInt(key, value).apply()
    }
    fun save(key: String?, value: Float) {
        editor.putFloat(key, value).apply()
    }
    fun save(key: String?, value: Long) {
        editor.putLong(key, value).apply()
    }

    // to save object in prefrence
    fun save(key: String?, `object`: Any?) {
        requireNotNull(`object`) { "object is null" }
        require(!(key == "" || key == null)) { "key is empty or null" }
        editor.putString(key, GSON.toJson(`object`)).apply()
    }
    fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return preferences.getBoolean(key, defValue)
    }
    fun getString(key: String?, defValue: String?): String? {
        return preferences.getString(key, defValue)
    }
    fun getInt(key: String?, defValue: Int): Int {
        return preferences.getInt(key, defValue)
    }
    fun getFloat(key: String?, defValue: Float): Float {
        return preferences.getFloat(key, defValue)
    }
    fun getLong(key: String?, defValue: Long): Long {
        return preferences.getLong(key, defValue)
    }
    fun getStringSet(key: String?, defValue: Set<String?>?): Set<String>? {
        return preferences.getStringSet(key, defValue)
    }

    fun removeAll() {
        editor.clear()
        editor.apply()
    }

    companion object {
        private const val TAG = "Prefs"
        var singleton: Prefs? = null
        private val GSON = Gson()
        fun with(context: Context): Prefs? {
            if (singleton == null) {
                singleton = Prefs(context)
            }
            return singleton
        }
    }

    init {
        preferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE)
        editor = preferences.edit()
    }
}