package com.rubahapi.footballapps.home.fragment.match.nextmatch

import android.support.v4.app.Fragment

interface NextMatchPresenterView<T : Fragment> {
    fun onAttach(view: T)
    fun onDetach()
}