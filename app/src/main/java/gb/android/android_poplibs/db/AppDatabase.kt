package gb.android.android_poplibs.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gb.android.android_poplibs.App
import gb.android.android_poplibs.db.dao.ImageDao
import gb.android.android_poplibs.db.dao.RepoDao
import gb.android.android_poplibs.db.dao.UserDao
import gb.android.android_poplibs.db.model.RoomGithubRepo
import gb.android.android_poplibs.db.model.RoomGithubUser
import gb.android.android_poplibs.db.model.RoomImage


@Database(
    entities = [
        RoomGithubUser::class,
        RoomGithubRepo::class,
        RoomImage::class,
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract val userDao: UserDao

    abstract val repositoryDao: RepoDao

    abstract val imageDao: ImageDao

    companion object {
        private const val DB_NAME = "database.db"

        val instance by lazy {
            Room.databaseBuilder(App.instance, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}