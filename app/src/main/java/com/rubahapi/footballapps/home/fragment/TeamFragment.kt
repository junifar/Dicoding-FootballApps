package com.rubahapi.footballapps.home.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rubahapi.footballapps.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.support.v4.ctx

class TeamFragment:Fragment(), AnkoComponent<Context> {
    lateinit var appBarLayout: AppBarLayout

    override fun createView(ui: AnkoContext<Context>): View {
        return setupUI(ui)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    private fun setupUI(ui:AnkoContext<Context>) = with(ui){
        linearLayout {
            lparams(
                width = matchParent,
                height = matchParent
            )

            fitsSystemWindows = false

            appBarLayout = themedAppBarLayout(R.style.AppTheme_AppBarOverlay){
                lparams(
                    width = matchParent,
                    height = wrapContent
                )

                themedToolbar(R.style.AppTheme_PopupOverlay) {
                    lparams(
                        width = matchParent,
                        height = android.R.attr.actionBarSize
                    )
                    title = "Football Apps"
                    setTitleTextColor(Color.WHITE)

                }.lparams{
                    weight = 1f
                }
            }

//            textView {
//                text = "Team test"
//            }
        }
    }
}