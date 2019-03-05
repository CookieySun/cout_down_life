package y_and_y.com.cout_down_life

import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_remaining_time.*
import org.jetbrains.anko.startActivity
import java.text.NumberFormat
import java.util.*


class RemainingTimeActivity : AppCompatActivity() {
    val handler = Handler()
    private lateinit var r: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remaining_time)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val selectDate = sharedPreferences.getString("selectDate", "")!!
        val nfNum = NumberFormat.getNumberInstance()

        val pastSec = getPastSec(selectDate, Date())

        val lifeSpanSec = resources.getInteger(R.integer.second).toLong() *
                resources.getInteger(R.integer.minutes).toLong() *
                resources.getInteger(R.integer.hours).toLong() *
                resources.getInteger(R.integer.days).toLong() *
                resources.getInteger(R.integer.years).toLong()

        val remainingSec = lifeSpanSec - pastSec
        countDown.text = nfNum.format(remainingSec).toString()

        //毎秒実行
        doEveryTime(remainingSec)

        returnButton.setOnClickListener {
            startActivity<SetDate>()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        handler.removeCallbacks(r)
    }

    private fun getPastSec(today: String, selectDate: Date): Long {
        val todayArr = today.split("/")

        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, todayArr[0].toInt())
            set(Calendar.MONTH, todayArr[1].toInt())
            set(Calendar.DATE, todayArr[2].toInt())
            set(Calendar.HOUR, 12)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }

        val todayTime = calendar.time.time
        val selectDateTime = selectDate.time

        return (-(todayTime - selectDateTime) / 1000)
    }


    private fun doEveryTime(leaveTime: Long) {
        var nowPastSec = leaveTime
        val nfNum = NumberFormat.getNumberInstance()
        r = object : Runnable {
            override fun run() {
                nowPastSec -= 1
                countDown.text = nfNum.format(nowPastSec).toString()
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(r)
    }
}
