package ru.androiddev.notes.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.androiddev.notes.R
import ru.androiddev.notes.databinding.UpdateNoteFragmentBinding
import ru.androiddev.notes.presentation.viewmodels.EditNoteViewModel
import ru.androiddev.notes.presentation.viewmodels.Result

class UpdateNoteFragment : Fragment(R.layout.update_note_fragment) {

    private lateinit var binding: UpdateNoteFragmentBinding
    private val editNoteViewModel: EditNoteViewModel by viewModel()
    private val args: UpdateNoteFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = UpdateNoteFragmentBinding.bind(view)
        initUI()
        initObservers()
        val id = args.id
        editNoteViewModel.fetchNote(id)
    }

    private fun initObservers() {
        editNoteViewModel.result.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Success -> showToast(result.message)
                is Result.Error -> showToast(result.error)
            }
        })
        editNoteViewModel.note.observe(viewLifecycleOwner, { note ->
            binding.titleEditText.setText(note.title)
            binding.noteContentEditText.setText(note.text)
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
            editNoteViewModel.updateNote(title, text)
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