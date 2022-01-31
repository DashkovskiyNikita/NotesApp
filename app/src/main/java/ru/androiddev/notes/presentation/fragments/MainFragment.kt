package ru.androiddev.notes.presentation.fragments


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.androiddev.notes.R
import ru.androiddev.notes.databinding.MainFragmentBinding
import ru.androiddev.notes.presentation.adapter.NotesAdapter
import ru.androiddev.notes.presentation.viewmodels.NotesViewModel

class MainFragment : Fragment(R.layout.main_fragment) {

    private lateinit var binding: MainFragmentBinding
    private val notesViewModel: NotesViewModel by viewModel()
    private lateinit var notesAdapter: NotesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)
        initUI()
        initObservers()
        notesViewModel.fetchNotes()
    }

    private fun initObservers() {
        notesViewModel.notes.observe(viewLifecycleOwner, { notes ->
            notesAdapter.setData(notes)
        })
        notesViewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            val indicator = binding.progressIndicator
            indicator.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
    }

    private fun initUI() {
        setButtonClickListener()
        //setOnQueryTextListener()
        initRecyclerView()
    }

    private fun setButtonClickListener() {
        binding.addNoteButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_noteFragment22)
        }
    }

    private fun setOnQueryTextListener() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                notesAdapter.filterDataList(newText)
                return true
            }
        })
    }

    private fun initRecyclerView() {
        val notesLayoutManager = LinearLayoutManager(requireContext())
        notesAdapter = provideAdapter()
        binding.noteRecyclerView.apply {
            adapter = notesAdapter
            layoutManager = notesLayoutManager
        }
    }

    private fun provideAdapter(): NotesAdapter {
        return NotesAdapter(
            deleteCallback = notesViewModel::deleteNote,
            isCompletedCallback = notesViewModel::updateNote,
            cardViewCallback = { id ->
                val action = MainFragmentDirections.actionMainFragmentToUpdateNoteFragment(id = id)
                findNavController().navigate(action)
            }
        )
    }
}