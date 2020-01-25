package com.digitalindo.simplenavigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_amount.*

/**
 * A simple [Fragment] subclass.
 */
class AmountFragment : Fragment() {

    private var nav : NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        nav = Navigation.findNavController(view)

        cancel.setOnClickListener {

            activity?.onBackPressed()
        }

        next.setOnClickListener {


            var bundle = Bundle()
            bundle.putString("amout",number.text.toString())

            nav?.navigate(R.id.action_amountFragment_to_resultFragment,bundle)
        }


    }


}
