package gb.android.android_poplibs.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import gb.android.android_poplibs.cache.*
import gb.android.android_poplibs.db.AppDatabase
import javax.inject.Singleton

private const val DB_NAME = "database.db"

@Module
class CacheModule {

    @Singleton
    @Provides
    fun db(context: Context): AppDatabase = Room
        .databaseBuilder(context, AppDatabase::class.java, DB_NAME)
        .build()

    @Singleton
    @Provides
    fun usersCache(db: AppDatabase): UsersCache = RoomUsersCache(db)

    @Singleton
    @Provides
    fun repoCache(db: AppDatabase): RepoCache = RoomRepoCache(db)

    @Singleton
    @Provides
    fun imageCache(db: AppDatabase, context: Context): ImageCache = RoomImageCache(db, context)

}