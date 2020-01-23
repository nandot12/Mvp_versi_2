package com.udacoding.udacodingecommerce.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.udacoding.udacodingecommerce.ui.main.MainActivity
import com.udacoding.udacodingecommerce.R
import com.udacoding.udacodingecommerce.ui.login.LoginActivity
import com.udacoding.udacodingecommerce.util.SessionManager
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var sesi = SessionManager(this)


        Handler().postDelayed(Runnable {

            if(sesi.isLogin) startActivity<MainActivity>()
            else startActivity<LoginActivity>()
            finish()

        },4000)


    }
}
