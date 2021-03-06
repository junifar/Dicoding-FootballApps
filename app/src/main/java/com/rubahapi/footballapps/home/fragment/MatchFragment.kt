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
import com.rubahapi.footballapps.home.fragment.match.nextmatch.NextMatchFragment
import com.rubahapi.footballapps.home.fragment.match.pastmatch.PastMatchFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.support.v4.viewPager

class MatchFragment: Fragment(), AnkoComponent<Context>{

    private lateinit var layoutTab: TabLayout
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var viewPager: ViewPager

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    lateinit var nextMatchFragment: NextMatchFragment
    lateinit var pastMatchFragment: PastMatchFragment

    override fun createView(ui: AnkoContext<Context>): View {
        return setupUI(ui)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
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

    fun filterList(textFilter:String){
        nextMatchFragment.filterList(textFilter)
        pastMatchFragment.filterList(textFilter)
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

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when(position){
                0-> {
                    nextMatchFragment = NextMatchFragment()
                    val args = Bundle()
                    nextMatchFragment.arguments = args
                    nextMatchFragment
                }
                else -> {
                    pastMatchFragment = PastMatchFragment()
                    val args = Bundle()
                    pastMatchFragment.arguments = args
                    pastMatchFragment
                }
            }
        }

        override fun getCount(): Int {
            return 2
        }
    }
}