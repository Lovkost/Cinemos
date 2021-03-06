package com.example.cinemos.ui.main.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cinemos.R
import com.example.cinemos.ui.main.app.App.Companion.getHistoryDao
import com.example.cinemos.ui.main.model.FactDTO
import com.example.cinemos.ui.main.repository.LocalRepository
import com.example.cinemos.ui.main.repository.LocalRepositoryImpl
import com.example.cinemos.ui.main.viewModel.MainViewModel

class NotesFragment : Fragment() {
    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao())
    private lateinit var movieBundle: FactDTO
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.saveButton)
        val editTextNote = view.findViewById<EditText>(R.id.yourNote)
        getNotes(editTextNote)
        button.setOnClickListener {
            saveNotes(editTextNote.text.toString())
            Toast.makeText(context, "Сохранили", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveNotes(text: String) {
        MainViewModel().saveCityToDB(text)
    }

    private fun getNotes(editTextNote: EditText) {
        editTextNote.setText(historyRepository.getAllHistory().toString())
    }

    companion object {
        fun newInstance() = NotesFragment()
    }
}

