package com.rubahapi.footballapps.home.fragment.favorite

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.textView

class FavoriteMatchesFragment : Fragment(), AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>): View {
        return setupUI(ui)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    private fun setupUI(ui:AnkoContext<Context>) = with(ui){
        coordinatorLayout {
            lparams{
                width = matchParent
                height = matchParent
            }
            textView { text = "Favorite Matches" }
        }
    }

    companion object {
        fun newInstance(): FavoriteMatchesFragment{
            val fragment = FavoriteMatchesFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}
