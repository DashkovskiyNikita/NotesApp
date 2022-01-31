package ru.androiddev.notes.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.androiddev.notes.databinding.NoteItemBinding
import ru.androiddev.notes.domain.models.NoteModel

class NotesAdapter(
    private var data: List<NoteModel> = emptyList(),
    private val deleteCallback: (NoteModel) -> Unit,
    private val isCompletedCallback: (NoteModel) -> Unit,
    private val cardViewCallback: (Int) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val binding = holder.binding
        data[position].apply {
            binding.noteTitleTv.text = title
            binding.noteTextTv.text = text
            binding.checkbox.isChecked = isCompleted
            binding.deleteNoteButton.setOnClickListener { deleteCallback(this) }
            binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
                this.isCompleted = isChecked
                isCompletedCallback(this)
            }
            binding.cardView.setOnClickListener { cardViewCallback(this.id) }
        }

    }

    override fun getItemCount() = data.size

    fun setData(newData: List<NoteModel>) {
        val notesDiffUtils = NotesDiffUtils(data, newData)
        val result = DiffUtil.calculateDiff(notesDiffUtils)
        data = newData
        result.dispatchUpdatesTo(this)
    }

    fun filterDataList(value: String?) {
        value ?: return
        val result = data.filter { note -> value.lowercase() in note.title.lowercase() }
        setData(result)
    }
}