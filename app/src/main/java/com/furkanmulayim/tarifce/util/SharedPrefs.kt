package com.furkanmulayim.tarifce.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

class SharedPrefs {

    companion object {
        private val preferencesTime = "preferences_time"
        private val preferencesGptTime = "gpt_time"
        private val prefImage = "preferences_image"

        private var sp: SharedPreferences? = null

        @Volatile
        private var instance: SharedPrefs? = null
        private val lock = Any()

        operator fun invoke(context: Context): SharedPrefs = instance ?: synchronized(lock) {

            instance ?: makeSharedPrefs(context).also {
                instance = it
            }
        }

        private fun makeSharedPrefs(context: Context): SharedPrefs {
            sp = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPrefs()
        }

    }

    //kullanıcının indirdiği zamanı kaydedip almak için tutuyoruz
    fun saveTime(time: Long) {
        sp?.edit(commit = true) {
            putLong(preferencesTime, time)
        }
    }
    fun getTime() = sp?.getLong(preferencesTime, 0)


    //kullanıcının indirdiği zamanı kaydedip almak için tutuyoruz
    fun saveGptTime(time: Long) {
        sp?.edit(commit = true) {
            putLong(preferencesGptTime, time)
        }
    }
    fun getGptTime() = sp?.getLong(preferencesGptTime, 0)


    //kullanıcının çektiği fotoğrafı bir sonraki fotograf çekene kadar tutuyoruz
    fun saveImageLocation(uriString: String) {
        sp?.edit(commit = true) {
            putString(prefImage, uriString)
        }
    }
    fun getImageUriInShared() = sp?.getString(prefImage,"")
}