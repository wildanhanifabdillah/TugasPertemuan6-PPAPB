package com.example.TugasPertemuan6

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.TugasPertemuan6.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tujuan: Array<String>
    private lateinit var harga: Array<String>

    private var selectedDate = ""
    private var selectedTime = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tujuan = resources.getStringArray(R.array.tujuan)
        harga = resources.getStringArray(R.array.harga)
        with(binding) {
            // Set adapter untuk Spinner tujuan
            val adapterTujuan = ArrayAdapter(
                this@MainActivity,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, tujuan
            )
            spinnerTujuan.adapter = adapterTujuan
            ButtonTanggalKeberangkatan.setOnClickListener {
                showDatePickerDialog()
            }
            buttonJamKeberangkatan.setOnClickListener {
                showTimePickerDialog()
            }
            buttonPesan.setOnClickListener {
                val username = username.text.toString()
                val tujuanDipilih = spinnerTujuan.selectedItemPosition
                val hargaDipilih = harga[tujuanDipilih]

                if (selectedDate.isEmpty() || selectedTime.isEmpty()) {
                    Toast.makeText(this@MainActivity, "Pilih tanggal dan waktu!", Toast.LENGTH_SHORT).show()
                } else {
                    showConfirmationDialog(username, selectedTime, selectedDate, tujuan[tujuanDipilih], hargaDipilih)
                }
            }


        }
    }
    private fun showConfirmationDialog(nama: String, jam: String, tanggal: String, tujuan: String, harga: String) {
        // Membuat instance dari DialogPemesanan dan menampilkan dialog
        val dialog = DialogPemesanan(this, nama, jam, tanggal, tujuan, harga)
        dialog.show(supportFragmentManager, "DialogPemesanan")
    }
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            selectedDate = "$selectedDay-${selectedMonth + 1}-$selectedYear"
            binding.ButtonTanggalKeberangkatan.text = selectedDate
        }, year, month, day)

        datePickerDialog.show()
    }


    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            binding.buttonJamKeberangkatan.text = selectedTime
        }, hour, minute, true)

        timePickerDialog.show()
    }
}
