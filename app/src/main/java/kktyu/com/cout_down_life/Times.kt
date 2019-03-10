package kktyu.com.cout_down_life

import java.util.*

class Times(birthdayArr: List<String>) {
    val birthday = Calendar.getInstance().apply {
        set(Calendar.YEAR, birthdayArr[0].toInt())
        set(Calendar.MONTH, birthdayArr[1].toInt())
        set(Calendar.DATE, birthdayArr[2].toInt())
        set(Calendar.HOUR, 12)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
    }

    private val lifeSpanSec = 60L * 60L * 24L * 365L * 90L

    fun remainingSec() = lifeSpanSec - getPastSec()

    private fun getPastSec(): Long {
        val todayTime = birthday.time.time
        val selectDateTime = Date().time

        return (-(todayTime - selectDateTime) / 1000)
    }
}