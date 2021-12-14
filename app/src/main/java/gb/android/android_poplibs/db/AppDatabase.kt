package gb.android.android_poplibs.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gb.android.android_poplibs.App
import gb.android.android_poplibs.db.dao.RepoDao
import gb.android.android_poplibs.db.dao.UserDao
import gb.android.android_poplibs.db.model.RoomGithubRepo
import gb.android.android_poplibs.db.model.RoomGithubUser


@Database(
    entities = [
        RoomGithubUser::class,
        RoomGithubRepo::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract val userDao: UserDao

    abstract val repositoryDao: RepoDao

    companion object {
        private const val DB_NAME = "database.db"

        val instance by lazy {
            Room.databaseBuilder(App.instance, AppDatabase::class.java, DB_NAME)
                .build()
        }
    }
}