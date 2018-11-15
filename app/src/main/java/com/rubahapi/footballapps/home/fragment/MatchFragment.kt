package com.rubahapi.footballapps.home.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.support.v4.ctx

class MatchFragment: Fragment(), AnkoComponent<Context>{

    override fun createView(ui: AnkoContext<Context>): View {
        return setupUI(ui)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    private fun setupUI(ui:AnkoContext<Context>) = with(ui){
        coordinatorLayout {
            lparams(
                width = matchParent,
                height = matchParent
            )

            fitsSystemWindows = true

            appBarLayout {
                lparams(
                    width = matchParent,
                    height = wrapContent
                )
                topPadding = dip(8)
                toolbar {
                    lparams(
                        width = matchParent,
                        height = android.R.attr.actionBarSize
                    )
                }.lparams{
                    weight = 1f
                }
            }

            textView {
                text = "Match test"
            }
        }
    }
}