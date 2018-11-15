package com.rubahapi.footballapps.home.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx

class FavoriteFragment: Fragment(), AnkoComponent<Context>{
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

            textView {
                text = "Favorite test"
            }
        }
    }
}