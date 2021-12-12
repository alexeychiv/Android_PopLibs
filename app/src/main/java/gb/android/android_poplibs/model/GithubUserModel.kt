package gb.android.android_poplibs.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class GithubUserModel(
    @Expose @SerializedName("login") val login: String,
    @Expose @SerializedName("avatar_url") val avatarUrl: String,
) : Parcelable
