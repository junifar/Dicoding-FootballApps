package com.rubahapi.footballapps.home.fragment.team

import android.support.v4.app.Fragment

interface TeamPresenterView<T : Fragment> {
    fun onAttach(view: T)
    fun onDetach()
}