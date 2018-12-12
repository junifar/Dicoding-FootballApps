package com.rubahapi.footballapps.home.fragment.match.pastmatch

import android.support.v4.app.Fragment

interface PastMatchPresenterView<T : Fragment> {
    fun onAttach(view: T)
    fun onDetach()
}