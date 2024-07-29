package com.example.kyliesnewapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {

    // chamadas para o carrossel
    private lateinit var carrossel : ViewPager
    private val handler = Handler(Looper.getMainLooper())
    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpCarrossel()
        setUpScroll()


    }

    // fun criada para o funcionamento do carrossel

    private fun setUpCarrossel() {
        carrossel = findViewById(R.id.carrossel)
        val imageView = listOf(R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4)
        carrossel.adapter = CarrosselAdapter(imageView)

    }

    // fun criada para o funcionamento do scroll

    private fun setUpScroll() {

        val update = Runnable {
            if ( currentPage == (carrossel.adapter?.count?: 1)-1) {
                currentPage = 0
            }else{
                currentPage++
            }
            carrossel.setCurrentItem(currentPage, true)
        }
        handler.postDelayed( object: Runnable {
            override fun run() {
                update.run()
                handler.postDelayed(this, 4000)
            }
        },4000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}