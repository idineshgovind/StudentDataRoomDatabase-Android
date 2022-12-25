package com.example.a7_simpleroomapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.a7_simpleroomapp.databinding.FragmentDataBinding
import kotlinx.coroutines.*

class DataFragment : Fragment() {
    private lateinit var appDb: AppDatabase

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentDataBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_data,
                container,
                false
            )
        appDb = AppDatabase.getDatabase(this.requireContext())
        val arguments = DataFragmentArgs.fromBundle(requireArguments())


        binding.zsidShow.text = arguments.zsid
        binding.genShow.text = arguments.gender
        binding.nameShow.text = arguments.name
        binding.sclShow.text = arguments.school

        binding.deleteButton.setOnClickListener {
            GlobalScope.launch {
                appDb.StudentDao().deleteAll()
            }
            binding.zsidShow.text = "No Content"
            binding.genShow.text = "No Content"
            binding.nameShow.text = "No Content"
            binding.sclShow.text = "No Content"
        }
        return binding.root
    }
}