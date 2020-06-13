package com.example.roomdbdemo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *Created by Ankit Bajaj on 13-06-2020.
 */
@Entity(tableName = "note_table")
data class Note(
    @ColumnInfo(name = "title") private var title: String? = null,
    @ColumnInfo(name = "description") private var desc: String? = null,
    @ColumnInfo(name = "prority") private var prority: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    private var id: Int = 0
}