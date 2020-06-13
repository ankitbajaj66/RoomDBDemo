package com.example.roomdbdemo

import android.os.AsyncTask
import androidx.lifecycle.LiveData

/**
 *Created by Ankit Bajaj on 13-06-2020.
 */
class NoteRepo(private val noteDao: NoteDao) {

    fun getAllTheNotes() = noteDao.getAllNotes()

    fun insert(note: Note) {
        InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun update(note: Note) {
        UpdateNoteAsyncTask(noteDao).execute(note)
    }

    fun delete(note: Note) {
        DeleteNoteAsyncTask(noteDao).execute(note)
    }

    fun deleteAllNotes() {
        DeleteAllNoteAsyncTask(noteDao).execute()
    }

    companion object {

        // Insert the note
        private class InsertNoteAsyncTask(private val noteDao: NoteDao) :
            AsyncTask<Note, Unit, Unit>() {
            override fun doInBackground(vararg notes: Note?) {
                notes[0]?.let {
                    noteDao.insert(it)
                }
            }
        }

        // Update the note
        private class UpdateNoteAsyncTask(private val noteDao: NoteDao) :
            AsyncTask<Note, Unit, Unit>() {
            override fun doInBackground(vararg notes: Note?) {
                notes[0]?.let {
                    noteDao.update(it)
                }
            }
        }

        // Delete one note
        private class DeleteNoteAsyncTask(private val noteDao: NoteDao) :
            AsyncTask<Note, Unit, Unit>() {
            override fun doInBackground(vararg notes: Note?) {
                notes[0]?.let {
                    noteDao.delete(it)
                }
            }
        }

        // Delete all the notes
        private class DeleteAllNoteAsyncTask(private val noteDao: NoteDao) :
            AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg p0: Unit?) {
                noteDao.deleteAllNotes()
            }

        }
    }

}