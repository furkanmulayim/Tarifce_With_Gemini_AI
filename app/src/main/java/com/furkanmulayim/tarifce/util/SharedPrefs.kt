package com.furkanmulayim.tarifce.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedPrefs {

    companion object {
        const val preferencesTime = "preferences_time"

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
            sp = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPrefs()
        }

    }

    //kullanıcının indirdiği zamanı kaydedip almak için tutuyoruz
    fun saveTime(time: Long) {
        CoroutineScope(Dispatchers.Default).launch {
            sp?.edit(commit = true) {
                putLong(preferencesTime, time)
            }
        }
    }

    fun getTime() = sp?.getLong(preferencesTime, 0)
}