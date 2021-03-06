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
import com.rubahapi.footballapps.R
import com.rubahapi.footballapps.home.fragment.favorite.favoritematch.FavoriteMatchesFragment
import com.rubahapi.footballapps.home.fragment.favorite.favoriteteam.FavoriteTeamsFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.viewPager
import android.support.v7.app.AppCompatActivity
import android.view.*


class FavoriteFragment: Fragment(), AnkoComponent<Context> {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    private lateinit var layoutTab: TabLayout
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var viewPager: ViewPager

    lateinit var favoriteTeamsFragment:FavoriteTeamsFragment
    lateinit var favoriteMatchFragment:FavoriteMatchesFragment

    override fun createView(ui: AnkoContext<Context>): View {
        return setupUI(ui)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar!!.show()
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
                    visibility = View.GONE

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

    fun filterList(textFilter:String){
        favoriteMatchFragment.filterList(textFilter)
        favoriteTeamsFragment.filterList(textFilter)
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when(position){
                0-> {
                    favoriteMatchFragment = FavoriteMatchesFragment()
                    val args = Bundle()
                    favoriteMatchFragment.arguments = args
                    favoriteMatchFragment
                }
                else -> {
                    favoriteTeamsFragment = FavoriteTeamsFragment()
                    val args = Bundle()
                    favoriteTeamsFragment.arguments = args
                    favoriteTeamsFragment
                }
            }
        }

        override fun getCount(): Int {
            return 2
        }
    }
}