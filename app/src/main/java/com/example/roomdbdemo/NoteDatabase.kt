package com.example.roomdbdemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 *Created by Ankit Bajaj on 13-06-2020.
 */
@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase private constructor(context: Context) : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    // fallbackToDestructiveMigration - it comes into picture when we are incrementing the version of db then it will delete old database and create new one
    companion object {
        @Volatile
        private var instance: NoteDatabase? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "note_database"
            ).fallbackToDestructiveMigration().build().also {
                instance = it
            }
        }
    }
}