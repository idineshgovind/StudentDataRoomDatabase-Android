package com.example.a7_simpleroomapp


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import com.example.a7_simpleroomapp.databinding.FragmentInputBinding
import kotlinx.coroutines.*


class InputFragment : Fragment() {
    private lateinit var appDb: AppDatabase

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentInputBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_input,
                container,
                false
            )
        appDb = AppDatabase.getDatabase(this.requireContext())

        binding.readButton.setOnClickListener {

            val zsid = binding.zsidText.text.toString()
            val name = binding.nameText.text.toString()
            val gender = binding.genText.text.toString()
            val school = binding.sclText.text.toString()

            if (zsid.isNotEmpty() && name.isNotEmpty() && gender.isNotEmpty() && school.isNotEmpty()) {
                val student = Student(
                    null, zsid, name, gender, school
                )
                GlobalScope.launch(Dispatchers.IO) {
                    appDb.StudentDao().insert(student)
                }


                binding.zsidText.text.clear()
                binding.nameText.text.clear()
                binding.genText.text.clear()
                binding.sclText.text.clear()
                Toast.makeText(context, "Successfully Added!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Enter all the values to continue!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.viewButton.setOnClickListener {
            val idzs = binding.zsidRead.text.toString()
            if (idzs.isNotEmpty()) {
                lifecycle.coroutineScope.launch(Dispatchers.IO) {
                    val student: Student = appDb.StudentDao().findByZsid(idzs)

                    val zid = student.zsid.toString()
                    val nam = student.name.toString()
                    val gend = student.gender.toString()
                    val schl = student.school.toString()
                    withContext(Dispatchers.Main) {
                        view?.findNavController()
                            ?.navigate(
                                InputFragmentDirections.actionInputFragmentToDataFragment(
                                    zsid = zid,
                                    name = nam,
                                    gender = gend,
                                    school = schl
                                )
                            )
                    }
                }
            } else {
                Toast.makeText(
                    context,
                    "Please enter any values to continue!",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.i("onStartTag", "OnStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.i("onResumeTag", "OnResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.i("onPauseTag", "OnPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.i("onStopTag", "OnStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("onDestroyTag", "OnDestroy Called")
    }
}

