package com.example.roomdbdemo

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

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
            ).fallbackToDestructiveMigration().addCallback(roomDatabaseCallback).build().also {
                instance = it
            }
        }

        // Database callback
        private val roomDatabaseCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                InsertNoteAsyncTask(instance!!.noteDao()).execute()
            }
        }

        // Inner class to insert data into table on creation
        private class InsertNoteAsyncTask(private val noteDao: NoteDao) :
            AsyncTask<Unit, Unit, Unit>() {

            override fun doInBackground(vararg p0: Unit?) {
                noteDao.insert(Note("Java", "Java is not supported in Android Studio", 2))
                noteDao.insert(Note("Java", "Java is not supported in Android Studio", 1))
            }

        }
    }
}