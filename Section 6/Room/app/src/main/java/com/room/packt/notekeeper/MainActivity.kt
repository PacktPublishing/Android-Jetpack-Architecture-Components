package com.room.packt.notekeeper

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity(), NoteListAdapter.OnDeleteClickListener {

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val intent = Intent(this, NewNoteActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        }

        val noteListAdapter = NoteListAdapter(this, this)
        recyclerview.adapter = noteListAdapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        noteViewModel.allNotes.observe(this, Observer { notes ->
            notes?.let {
                noteListAdapter.setNotes(notes)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            // Code to insert note
            val noteId = UUID.randomUUID().toString()
            val note = Note(noteId, data!!.getStringExtra(NewNoteActivity.NOTE_ADDED))
            noteViewModel.insert(note)

            Toast.makeText(
                applicationContext,
                R.string.saved,
                Toast.LENGTH_LONG
            ).show()
        } else if (requestCode == UPDATE_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            // Code to update the note
            val note = Note(
                data!!.getStringExtra(EditNoteActivity.NOTE_ID),
                data.getStringExtra(EditNoteActivity.UPDATED_NOTE)
            )
            noteViewModel.update(note)

            Toast.makeText(
                applicationContext,
                R.string.updated,
                Toast.LENGTH_LONG
            ).show()

        } else {
            Toast.makeText(
                applicationContext,
                R.string.not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onDeleteClickListener(myNote: Note) {
        // Code for delete
        noteViewModel.delete(myNote)
    }

    companion object {
        private val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
        val UPDATE_NOTE_ACTIVITY_REQUEST_CODE = 2
    }
}
