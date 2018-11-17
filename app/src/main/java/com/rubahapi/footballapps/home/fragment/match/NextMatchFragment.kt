package com.rubahapi.footballapps.home.fragment.match

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

class NextMatchFragment : Fragment(), AnkoComponent<Context> {
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
            textView { text = "Next Match" }
        }
    }

    companion object {
        fun newInstance(): NextMatchFragment{
            val fragment = NextMatchFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}
