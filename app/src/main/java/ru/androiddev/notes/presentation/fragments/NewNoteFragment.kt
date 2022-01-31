package ru.androiddev.notes.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.androiddev.notes.R
import ru.androiddev.notes.databinding.NoteFragmentBinding
import ru.androiddev.notes.presentation.viewmodels.Result
import ru.androiddev.notes.presentation.viewmodels.NewNoteViewModel

class NewNoteFragment : Fragment(R.layout.note_fragment) {

    private lateinit var binding: NoteFragmentBinding
    private val newNoteViewModel: NewNoteViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = NoteFragmentBinding.bind(view)
        initUI()
        initObservers()
    }

    private fun initObservers() {
        newNoteViewModel.result.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Success -> {
                    showToast(result.message)
                    findNavController().popBackStack()
                }
                is Result.Error -> showToast(result.error)
            }
        })
    }

    private fun initUI() {
        setBackButtonListener()
        setSaveButtonListener()
    }

    private fun setSaveButtonListener() {
        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val text = binding.noteContentEditText.text.toString()
            newNoteViewModel.saveNote(title, text)
        }
    }

    private fun setBackButtonListener() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun showToast(@StringRes message: Int) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}