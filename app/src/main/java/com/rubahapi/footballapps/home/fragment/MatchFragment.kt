package com.rubahapi.footballapps.home.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rubahapi.footballapps.MainActivity
import com.rubahapi.footballapps.R
import com.rubahapi.footballapps.home.fragment.match.NextMatchFragment
import com.rubahapi.footballapps.home.fragment.match.PastMatchFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.support.v4.viewPager

class MatchFragment: Fragment(), AnkoComponent<Context>{

    lateinit var layoutTab: TabLayout
    lateinit var appBarLayout: AppBarLayout
    lateinit var viewPager: ViewPager

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

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

        val tabNext = layoutTab.newTab()
        tabNext.text = "Next"

        val tabPast = layoutTab.newTab()
        tabPast.text = "Past"

        layoutTab.addTab(tabNext)
        layoutTab.addTab(tabPast)

        mSectionsPagerAdapter = SectionsPagerAdapter(childFragmentManager)

        viewPager.adapter = mSectionsPagerAdapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(layoutTab))
        layoutTab.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPager))

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

            viewPager = viewPager {
                id = R.id.match_fragment_container
                lparams {
                    width = matchParent
                    height = matchParent
                }
            }.lparams{
                behavior = AppBarLayout.ScrollingViewBehavior()
            }
        }
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when(position){
                0-> NextMatchFragment.newInstance()
                else -> PastMatchFragment.newInstance()
            }
        }

        override fun getCount(): Int {
            return 2
        }
    }
}