package y_and_y.com.cout_down_life

import android.app.DatePickerDialog
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_set_date.*
import org.jetbrains.anko.startActivity

class SetDate : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_date)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val selectDate = sharedPreferences.getString("selectDate", "")!!

        dateView.text = if (selectDate != "") selectDate else getString(R.string.no_date)

        dateView.setOnClickListener {
            showDatePickerDialog()
        }
    }

    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val month = monthOfYear + 1
        val selectDate = "$year/$month/$dayOfMonth"
        dateView.text = selectDate

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this).edit()
        sharedPreferences.apply {
            putString("selectDate", selectDate)
            putBoolean("first_boot", false)
            sharedPreferences.apply()
        }

        startActivity<RemainingTimeActivity>()
        finish()
    }

    private fun showDatePickerDialog() {
        DatePick().show(supportFragmentManager, "datePicker")
    }


}


