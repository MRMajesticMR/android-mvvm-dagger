package ru.arkasha.app_mvvm_dagger.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import ru.arkasha.app_mvvm_dagger.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdgeMode()
    }

    private fun enableEdgeToEdgeMode() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

}