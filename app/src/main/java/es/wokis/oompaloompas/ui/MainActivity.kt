package es.wokis.oompaloompas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import dagger.hilt.android.AndroidEntryPoint
import es.wokis.oompaloompas.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var executeMenuClick: () -> Boolean = {
        // Default no code execution.
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.main_menu__filter -> executeMenuClick()
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun setOnClickMenuListener(onClickMenu: () -> Boolean) {
        executeMenuClick = onClickMenu
    }
}