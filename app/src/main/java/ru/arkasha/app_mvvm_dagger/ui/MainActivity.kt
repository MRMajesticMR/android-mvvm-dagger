package ru.arkasha.app_mvvm_dagger.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.arkasha.app_mvvm_dagger.R
import ru.arkasha.app_mvvm_dagger.ui.facts_list.FactsListScreen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}