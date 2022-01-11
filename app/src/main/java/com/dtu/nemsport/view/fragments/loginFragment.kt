package com.dtu.nemsport.view.fragments

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.dtu.nemsport.R
import com.dtu.nemsport.view.MainPage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [loginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class loginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    // views and text inputs from the front page
    private lateinit var input_username: EditText
    private lateinit var input_password: EditText
    private lateinit var out_feedback: TextView
    private var foundUser: Boolean = false
    private lateinit var auth: FirebaseAuth

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
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonForgotPassword: Button = view.findViewById(R.id.buttonForgotPassword)
        val buttonLogin: Button = view.findViewById(R.id.buttonLogin)

        buttonForgotPassword.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.loginToGlemtKode)

        }
        // 00 - set the views/inputs to our local vars
        input_username = view.findViewById(R.id.loginWriteEmail)
        input_password = view.findViewById(R.id.loginWritePassword)
        out_feedback = view.findViewById(R.id.textView_feedback)
        auth = Firebase.auth

        // TODO: edit this function
        buttonLogin.setOnClickListener {
            requireActivity().run {

                var user_inputs = false

                // 01 - validation
                if(input_username.text.toString().isNullOrBlank() ||
                    input_password.text.toString().isNullOrBlank())
                        out_feedback.text = "you need fill out the fields"
                else
                    user_inputs = true

                if(user_inputs)
                {
                    // 02 - check if the value is in the database
                    auth.signInWithEmailAndPassword(input_username.text.toString(), input_password.text.toString())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                val user = auth.currentUser
                                Log.d(TAG, "signInWithEmail:success - " + user.toString())
                                // updateUI(user)
                                Log.i("auth user info:" , user.toString())
                                startActivity(Intent(this, MainPage::class.java))
                                finish()

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.exception)
                                Toast.makeText(
                                    context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                                // updateUI(null)

                                out_feedback.text = "you have the wrong email or password"
                            }
                        }

                }

            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment loginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            loginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}