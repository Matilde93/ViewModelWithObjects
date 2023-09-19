package com.example.viewmodelwithobjects

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.viewmodelwithobjects.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PersonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSave.setOnClickListener {
            val name = binding.editTextName.text.trim().toString()
            if(name.isEmpty()){
                binding.editTextName.error = "No name"
                return@setOnClickListener
            }
            val ageStr = binding.editTextAge.text.trim().toString()
            if(ageStr.isEmpty()){
                binding.editTextName.error = "No age"
                return@setOnClickListener
            }
            val address = binding.editTextAddress.text.trim().toString()
            if(address.isEmpty()){
                binding.editTextName.error = "No address"
                return@setOnClickListener
            }
            viewModel.person.value = Person(name, ageStr.toInt(), address)

            binding.editTextAge.text.clear()
            binding.editTextName.text.clear()
            binding.editTextAddress.text.clear()

        }

        binding.buttonNext.setOnClickListener{
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}