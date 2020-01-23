package com.udacoding.udacodingecommerce.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.udacoding.udacodingecommerce.ui.main.history.HistoryFragment
import com.udacoding.udacodingecommerce.ui.main.home.HomeFragment
import com.udacoding.udacodingecommerce.ui.main.profile.ProfilFragment

class PagerFragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {

        var fragment = Fragment()

        when(position){
            0 -> fragment =  HomeFragment()
            1 -> fragment =  HistoryFragment()
            2 -> fragment =  ProfilFragment()
        }

        return fragment
    }



    override fun getCount()= 3


    override fun getPageTitle(position: Int): CharSequence? {

        var name = ""
        when(position){

            0 -> name = "Home"
            1 -> name = "History"
            2 -> name = "Profil"
        }
        return name
    }

}