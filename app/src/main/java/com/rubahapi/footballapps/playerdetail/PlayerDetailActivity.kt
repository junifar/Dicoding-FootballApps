package com.rubahapi.footballapps.playerdetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.wrapContent

class PlayerDetailActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
    }

    private fun setupUI(){
        verticalLayout {
            lparams {
                width = matchParent
                height = wrapContent
            }

            textView {
                text = "Coba"
            }
        }
    }
}