package com.example.roomdbdemo

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 *Created by Ankit Bajaj on 13-06-2020.
 */
@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    // Room dont have all the notation available for all the different types of operation so there is way to cover this

    // So writing query we are getting ide suggestions as well as doing typo mistake will be compilination error
    @Query("delete from note_table")
    fun deleteAllNotes()

    // Becuase we are returning the livedata object here, it will do the bg task itself.
    @Query("select * from note_table ORDER BY prority DESC")
    fun getAllNotes(): LiveData<List<Note>>

}