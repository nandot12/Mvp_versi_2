package com.udacoding.udacodingecommerce.util

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by imastudio on 2/9/16.
 */
class KeranjangManager//konstruktor
    ( var c: Context) {
    internal var pref: SharedPreferences
    internal var editor: SharedPreferences.Editor

    internal var PRIVATE_MODE = 0

    //0 agar cuma bsa dibaca hp itu sendiri
    internal var PREF_NAME = "kerjanjang"
    //mendapatkan token
    val token: String?
        get() = pref.getString(KEY_TOKEN, "")
    //cek login
    val isKeranjang: Boolean
        get() = pref.getBoolean(KEY_LOGIN, false)
    var idOrder: String?
        get() = pref.getString("idOrder", "")
        set(nama) {
            editor.putString("idOrder", nama)
            editor.commit()
        }
    var total: String?
        get() = pref.getString("total", "")
        set(email) {
            editor.putString("total", email)
            editor.commit()
        }
    var phone: String?
        get() = pref.getString("phone", "")
        set(phone) {
            editor.putString("phone", phone)
            editor.commit()
        }
    val idUser: String?
        get() = pref.getString("id_user", "")

    init {
        pref = c.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    //membuat session login
    fun cerateKeranjang(token: String) {
        editor.putString(KEY_TOKEN, token)
        editor.putBoolean(KEY_LOGIN, true)
        editor.commit()
        //commit digunakan untuk menyimpan perubahan
    }

    //logout user
    fun logout() {
        editor.clear()
        editor.commit()
    }

    fun setIduser(id_user: String) {
        editor.putString("id_user", id_user)
        editor.commit()
    }

    companion object {
        private val KEY_TOKEN = "tokenLogin"
        private val KEY_LOGIN = "isLogin"
    }

}
