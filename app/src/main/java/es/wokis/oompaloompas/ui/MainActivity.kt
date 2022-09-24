package es.wokis.oompaloompas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import dagger.hilt.android.AndroidEntryPoint
import es.wokis.oompaloompas.R
import es.wokis.oompaloompas.databinding.ActivityMainBinding
import es.wokis.oompaloompas.utils.setVisible

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var executeMenuClick: () -> Boolean = {
        // Default no code execution.
        false
    }

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
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

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    fun setOnClickMenuListener(onClickMenu: () -> Boolean) {
        executeMenuClick = onClickMenu
    }

    fun setLoading(loading: Boolean, loadingText: String) {
        binding?.mainContainerLoading?.loadingContainerMain.setVisible(loading)
        binding?.mainContainerLoading?.loadingLabelLoadingText?.text = loadingText
    }
}