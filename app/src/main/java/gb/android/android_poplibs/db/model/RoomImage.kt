package gb.android.android_poplibs.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomImage(
    @PrimaryKey
    val url: String,
    val localPath: String
)
