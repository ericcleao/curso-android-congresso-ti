package io.github.wellingtoncosta.cursoandroidcongressodeti.resources.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.wellingtoncosta.cursoandroidcongressodeti.resources.database.dao.ContatoFavoritoDao
import io.github.wellingtoncosta.cursoandroidcongressodeti.resources.database.entity.ContatoFavoritoEntity

@Database(
    version = 1,
    entities = [ContatoFavoritoEntity::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contatoFavoritoDao(): ContatoFavoritoDao

    companion object {
        const val DATABASE_NAME = "curso-android-congresso-ti-db"

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if(instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }

            return instance!!
        }
    }

}