package com.room.packt.notekeeper

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Note::class], version = 1)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {

        @Volatile
        private var noteRoomInstance: NoteRoomDatabase? = null

        internal fun getDatabase(context: Context): NoteRoomDatabase? {
            if (noteRoomInstance == null) {
                synchronized(NoteRoomDatabase::class.java) {
                    if (noteRoomInstance == null) {
                        noteRoomInstance = Room.databaseBuilder(
                            context.applicationContext,
                            NoteRoomDatabase::class.java, "note_db")
                            .build()
                    }
                }
            }
            return noteRoomInstance
        }
    }
}