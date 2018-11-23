package com.rubahapi.footballapps.teamdetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rubahapi.footballapps.models.Team
import org.jetbrains.anko.*

class TeamDetailActivity: AppCompatActivity(){

    lateinit var item:Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        item = intent.getParcelableExtra("item")

        setupUI()
    }

    private fun setupUI(){
        scrollView {
            lparams{
                width = matchParent
                height = matchParent
            }

            verticalLayout {
                lparams{
                    width = matchParent
                    height = wrapContent
                }

                textView {
                    text = "coba"
                }
            }
        }
    }
}