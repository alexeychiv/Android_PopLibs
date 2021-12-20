package gb.android.android_poplibs.cache.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomImage(
    @PrimaryKey
    val url: String,
    val localPath: String
)
