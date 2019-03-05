package y_and_y.com.cout_down_life

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.DialogFragment
import android.text.format.DateFormat
import java.util.*

class DatePick : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity as SetDate)
        val selectDate = sharedPreferences.getString("selectDate", "")
        val dateList = if (selectDate != "") selectDate!!.split("/") else null

        val c = Calendar.getInstance()
        val year = if (dateList != null) dateList[0].toInt() else c.get(Calendar.YEAR)
        val month = if (dateList != null) dateList[1].toInt() - 1 else c.get(Calendar.MONTH)
        val day = if (dateList != null) dateList[2].toInt() else c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(activity!!,
                activity as SetDate, year, month, day)

        val maxDate = GregorianCalendar()
        val today = getToday()
        maxDate.set(today[0].toInt(), today[1].toInt() - 1, today[2].toInt())

        datePickerDialog.datePicker.maxDate = maxDate.timeInMillis

        return datePickerDialog
    }

    override fun onDateSet(view: android.widget.DatePicker, year: Int,
                           monthOfYear: Int, dayOfMonth: Int) {
    }

    private fun getToday() = DateFormat.format("yyyy/MM/dd", Date()).toString().split("/")

}