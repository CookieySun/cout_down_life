package kktyu.com.cout_down_life

import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_remaining_time.*
import org.jetbrains.anko.startActivity
import java.text.NumberFormat

class RemainingTimeActivity : AppCompatActivity() {
    val handler = Handler()
    private lateinit var r: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remaining_time)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val times = Times(sharedPreferences.getString("selectDate", "")!!.split("/"))

        //毎秒実行
        doEveryTime(times)

        returnButton.setOnClickListener {
            startActivity<SetDate>()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        handler.removeCallbacks(r)
    }

    private fun doEveryTime(times:Times) {
        val nfNum = NumberFormat.getNumberInstance()
        r = object : Runnable {
            override fun run() {
                countDown.text = nfNum.format(times.remainingSec()).toString()
                handler.postDelayed(this, 250)
            }
        }
        handler.post(r)
    }
}
