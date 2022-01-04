package com.dtu.nemsport.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.*
import androidx.navigation.Navigation
import com.dtu.nemsport.R
import com.dtu.nemsport.models.FakeDB

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [profilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */




class profilFragment : Fragment() {

    var displayMessage: String? = ""


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var skiftIndstillinger: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profil, container, false)
        val textView: TextView = view.findViewById(R.id.navn)

        val args = this.arguments
        val inputData = args?.get("data")
        textView.text = inputData.toString()

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        skiftIndstillinger = view.findViewById(R.id.skiftIndstillinger)

        // Navigate to EditProfile fragment
        skiftIndstillinger.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.profilToRedigereProfil)
            Toast.makeText(context, "Kommet til edit profile", Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment profilFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            profilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}