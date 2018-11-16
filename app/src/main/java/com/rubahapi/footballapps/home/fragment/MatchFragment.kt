package com.rubahapi.footballapps.home.fragment

import android.content.Context
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

class MatchFragment: Fragment(), AnkoComponent<Context>{

    lateinit var layoutTab: TabLayout
    lateinit var appBarLayout: AppBarLayout

    override fun createView(ui: AnkoContext<Context>): View {
//        val tab = layoutTab.newTab()
//        tab.text = "Test"
//        layoutTab.addTab(tab)
        return setupUI(ui)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val tab = layoutTab.newTab()
        tab.text = "Test"

        val tab2 = layoutTab.newTab()
        tab2.text = "Test2"

        val tab3 = layoutTab.newTab()
        tab3.text = "Test3"

        layoutTab.addTab(tab)
        layoutTab.addTab(tab2)
        layoutTab.addTab(tab3)
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
                    backgroundResource = R.color.colorPrimary
                    lparams(
                        width = matchParent,
                        height = android.R.attr.actionBarSize
                    )
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
//                text = "Match test"
//            }
        }
    }
}