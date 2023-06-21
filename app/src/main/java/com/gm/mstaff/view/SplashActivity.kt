package com.gm.mstaff.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import com.gm.mstaff.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slash)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        lifecycleScope.launch {
            delay(1700L)
            navigateToNextScreen()
        }
    }
    private fun navigateToNextScreen() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }
}