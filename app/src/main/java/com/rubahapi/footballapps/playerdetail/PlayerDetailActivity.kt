package com.rubahapi.footballapps.playerdetail

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.rubahapi.footballapps.models.Player
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class PlayerDetailActivity: AppCompatActivity(){

    lateinit var imageViewPlayer:ImageView
    lateinit var textWeight:TextView
    lateinit var textHeight:TextView
    lateinit var textDescription:TextView
    lateinit var textPlayerPosition:TextView

    lateinit var item:Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
        setupAction()
    }

    private fun setupAction(){
        item = intent.getParcelableExtra("item")

        Picasso.get().load(item.strThumb).fit().into(imageViewPlayer)
        textWeight.text = item.strWeight
        textHeight.text = item.strHeight
        textDescription.text = item.strDescriptionEN
        textPlayerPosition.text = item.strPosition
    }

    private fun setupUI(){
        scrollView {
            lparams{
                width = matchParent
                height = matchParent
            }
            verticalLayout {
                lparams {
                    width = matchParent
                    height = wrapContent
                }

                imageViewPlayer = imageView {

                }.lparams {
                    width = matchParent
                    height = dip(280)
                }

                linearLayout {
                    lparams{
                        width = matchParent
                        height = wrapContent
                    }
                    gravity = Gravity.CENTER
                    orientation = LinearLayout.HORIZONTAL
                    verticalLayout {
                        lparams {
                            height = matchParent
                            width = matchParent
                            weight = 1F
                        }
                        backgroundColor = Color.LTGRAY
                        textView {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textSize = 20F
                            text = "Weight (Kg)"
                        }
                        textWeight = textView {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textColor = Color.BLUE
                            textSize = 24f
                            text = "65.76"
                        }
                    }
                    verticalLayout {
                        lparams{
                            height = matchParent
                            width = matchParent
                            weight = 1F
                            gravity = Gravity.CENTER_HORIZONTAL
                        }
                        backgroundColor = Color.LTGRAY
                        textView {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textSize = 20F
                            text = "Height (m)"
                        }
                        textHeight = textView {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textColor = Color.BLUE
                            textSize = 24f
                            text = "1.68"
                        }
                    }
                }

                tableRow {
                    lparams(
                        width = matchParent,
                        height = dip(1)
                    )
                    backgroundColor = Color.GRAY
                }

                textPlayerPosition = textView {
                    text = "Striker"
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    typeface = Typeface.DEFAULT_BOLD
                    textColor = Color.BLUE
                    textSize = 24f
                }.lparams {
                    width = matchParent
                    height = wrapContent
                }

                tableRow {
                    lparams(
                        width = matchParent,
                        height = dip(1)
                    )
                    backgroundColor = Color.GRAY
                }

                textDescription = textView {
                }.lparams {
                    width = matchParent
                    height = wrapContent
                }
            }
        }

    }
}