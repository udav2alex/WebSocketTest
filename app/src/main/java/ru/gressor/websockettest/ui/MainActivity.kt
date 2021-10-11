package ru.gressor.websockettest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.gressor.websockettest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var viewBinding: ActivityMainBinding? = null
    private fun <T> views(block: ActivityMainBinding.() -> T): T? = viewBinding?.block()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)

        views {
            setContentView(this.root)

            if (supportFragmentManager.fragments.isEmpty()) {
                supportFragmentManager.beginTransaction()
                    .add(fragmentContainer.id, StartFragment())
                    .commit()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}