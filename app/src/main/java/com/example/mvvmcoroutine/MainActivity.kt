package com.example.mvvmcoroutine

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmcoroutine.databinding.ActivityMainBinding
import com.example.mvvmcoroutine.practical.getCardViewModel
import com.example.mvvmcoroutine.viewmodel.GetCardViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: GetCardViewModel
    val token =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGUiOjExMTExMTExMTEsImlhdCI6MTc0NTU3MTA0NywiZXhwIjoxNzQ4MTYzMDQ3fQ.h7XF-48KUSDFior1hDn19lYmtyq0y6T3zrQ4pvdTh08"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
        observal()
    }

    fun initview() {
        viewModel = ViewModelProvider(this)[GetCardViewModel::class.java]
        viewModel.getCard(token)
    }

    fun observal() {
        viewModel.cardList.observe(this) { response ->
            when (response) {
                is Result.Loading -> {

                    binding.progress.visibility = View.VISIBLE
                }

                is Result.Success -> {
                    binding.progress.visibility = View.GONE
                    val names = response.data.data?.mapNotNull { item ->
                        item?.userId?.firstName
                    }?.joinToString("\n")

                    binding.name.text = names.orEmpty()

                    Log.d("observe", "observe: ${response.data.data}.")
                    binding.progress.visibility = View.GONE
                }

                is Result.Error -> {

                    Toast.makeText(this, "${response.message}", Toast.LENGTH_SHORT).show()
                    binding.progress.visibility = View.GONE
                }
            }

        }
        practice()
    }


    fun practice() {
        var view = getCardViewModel()

        view.getCard("token")
        view.cardResponse.observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }

                is Result.Success -> {
                    binding.progress.visibility = View.GONE
                    Log.e("practice", "practice: ${result.data.data}")
                }

                is Result.Error -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(this, "${result.message}", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}