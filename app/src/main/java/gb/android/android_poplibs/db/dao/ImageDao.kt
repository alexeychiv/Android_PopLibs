package gb.android.android_poplibs.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gb.android.android_poplibs.db.model.RoomImage


@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomImage: RoomImage)

    @Query("SELECT localPath FROM RoomImage WHERE url = :url LIMIT 1")
    fun getPath(url: String): String?

}