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
import com.rubahapi.footballapps.R
import com.rubahapi.footballapps.home.fragment.favorite.favoriteteam.FavoriteTeamsFragment
import com.rubahapi.footballapps.home.fragment.favorite.favoritematch.FavoriteMatchesFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.viewPager

class FavoriteFragment: Fragment(), AnkoComponent<Context> {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    lateinit var layoutTab: TabLayout
    lateinit var appBarLayout: AppBarLayout
    lateinit var viewPager: ViewPager

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

        mSectionsPagerAdapter = SectionsPagerAdapter(childFragmentManager)

        layoutTab.addTab(tabMatches)
        layoutTab.addTab(tabTeams)

        viewPager.adapter = mSectionsPagerAdapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(layoutTab))
        layoutTab.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPager))
    }

    private fun setupUI(ui: AnkoContext<Context>) = with(ui){
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
                0-> FavoriteMatchesFragment.newInstance()
                else -> FavoriteTeamsFragment.newInstance()
            }
        }

        override fun getCount(): Int {
            return 2
        }
    }
}