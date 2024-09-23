package com.example.TugasPertemuan6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.TugasPertemuan6.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val nama = intent.getStringExtra("nama")
        val jam = intent.getStringExtra("jam")
        val tanggal = intent.getStringExtra("tanggal")
        val tujuan = intent.getStringExtra("tujuan")
        val harga = intent.getStringExtra("harga")

        // Tampilkan data di TextView menggunakan binding
        binding.Nama.text = nama
        binding.Jam.text = jam
        binding.Tanggal.text = tanggal
        binding.Tujuan.text = tujuan
        binding.Harga.text = harga
    }
}