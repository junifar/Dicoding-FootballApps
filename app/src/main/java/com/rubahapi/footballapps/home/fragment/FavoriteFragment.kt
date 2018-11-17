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
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.support.v4.ctx

class FavoriteFragment: Fragment(), AnkoComponent<Context>{

    lateinit var layoutTab: TabLayout
    lateinit var appBarLayout: AppBarLayout

    override fun createView(ui: AnkoContext<Context>): View {
        return setupUI(ui)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val tabMatches = layoutTab.newTab()
        tabMatches.text = "Matches"

        val tabTeams = layoutTab.newTab()
        tabTeams.text = "Teams"

        layoutTab.addTab(tabMatches)
        layoutTab.addTab(tabTeams)
    }

    private fun setupUI(ui:AnkoContext<Context>) = with(ui){
        coordinatorLayout {
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

                layoutTab = tabLayout {
                    lparams(
                        width = matchParent,
                        height = wrapContent
                    )
                }
            }

//            textView {
//                text = "Favorite test"
//            }
        }
    }
}