package kz.mihael3d.rickandmortycharacters.screens.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kz.mihael3d.rickandmortycharacters.R
import android.view.WindowManager

import android.os.Build
import android.view.Window


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

    }
}