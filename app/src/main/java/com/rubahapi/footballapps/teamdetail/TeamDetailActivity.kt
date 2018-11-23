package com.rubahapi.footballapps.teamdetail

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.AppBarLayout.ScrollingViewBehavior
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import com.rubahapi.footballapps.R
import com.rubahapi.footballapps.models.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.colorAttr
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.dip
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.textView

class TeamDetailActivity: AppCompatActivity(){

    lateinit var item:Team
    lateinit var collapsingToolbarLayout: CollapsingToolbarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        setSupportActionBar(toolbar)

        item = intent.getParcelableExtra("item")

        Picasso.get().load(item.teamBadge).fit().into(imgThumb)
        str_team.text = item.teamName
        int_formed_year.text = item.intFormedYear.toString()
        str_stadium.text = item.strStadium

//        setupUI()
//        setConfiguration()
    }

    private fun setConfiguration(){

    }

    private fun setupUI(){
        coordinatorLayout {
            lparams {
                width = matchParent
                height = matchParent
                behavior = ScrollingViewBehavior()
            }
            fitsSystemWindows = true

            themedAppBarLayout(R.style.AppTheme_AppBarOverlay) {
                fitsSystemWindows = true
                lparams {
                    width = matchParent
                    height = dip(180)
                }

                collapsingToolbarLayout = collapsingToolbarLayout {
                    fitsSystemWindows = true
                    lparams {
                        width = matchParent
                        height = matchParent
                    }
                    contentScrim = ColorDrawable(colorAttr(R.attr.colorPrimary))
                  textView {
                        text = "coba"
                    }
                }
            }
            textView {
                text = "coba"
            }
        }
//        scrollView {
//            lparams{
//                width = matchParent
//                height = matchParent
//            }
//
//            verticalLayout {
//                lparams{
//                    width = matchParent
//                    height = wrapContent
//                }
//
//                textView {
//                    text = "coba"
//                }
//            }
//        }
    }
}