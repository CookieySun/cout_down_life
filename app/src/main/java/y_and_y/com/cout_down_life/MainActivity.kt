package y_and_y.com.cout_down_life

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val firstFlg = sharedPreferences.getBoolean("first_boot", true)

        if (firstFlg) {
            startActivity<SetDate>()
        } else {
            startActivity<RemainingTimeActivity>()
        }
        finish()
    }
}
