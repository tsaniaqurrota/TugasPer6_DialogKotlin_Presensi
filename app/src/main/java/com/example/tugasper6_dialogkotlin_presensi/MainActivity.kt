package com.example.tugasper6_dialogkotlin_presensi

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import com.example.tugasper6_dialogkotlin_presensi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()  {

    private lateinit var binding: ActivityMainBinding

    private val kehadiran = arrayOf(
        "Hadir Tepat Waktu",
        "Sakit",
        "Terlambat",
        "Izin")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        with(binding) {
            val adapterKehadiran = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, kehadiran)
            adapterKehadiran.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)

            spinnerKehadiran.adapter= adapterKehadiran

            spinnerKehadiran.onItemSelectedListener=
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                        val selectedPresensi = kehadiran[position]

                        if (selectedPresensi != "Hadir Tepat Waktu") {
                            txtKeterangan.visibility = View.VISIBLE
                        } else {
                            txtKeterangan.visibility = View.GONE
                        }

                        Log.d("KEHADIRAN",selectedPresensi)
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }

                }

            datePicker.init(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth
            )
            {_,year, monthOfYear, dayOfMonth->
                val selectedDate = "$dayOfMonth/${monthOfYear +1}/$year"
                Log.d("SELECTED DATE",selectedDate)
            }

            timePicker.setOnTimeChangedListener{view, hourOfDay, minute ->
                var selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                Log.d("SELECTED TIME",selectedTime)
            }
        }
    }



}