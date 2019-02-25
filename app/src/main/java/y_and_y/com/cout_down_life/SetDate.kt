package y_and_y.com.cout_down_life

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_set_date.*
import java.util.*


class SetDate : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_date)

        dateView.setOnClickListener {
            showDatePickerDialog(it)
        }

    }

    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val str = String.format(Locale.US, "%d/%d/%d", year, monthOfYear + 1, dayOfMonth)
        dateView.text = str
    }

    fun showDatePickerDialog(v: View) {
        val newFragment = DatePick()
        newFragment.show(supportFragmentManager, "datePicker")
    }
}


