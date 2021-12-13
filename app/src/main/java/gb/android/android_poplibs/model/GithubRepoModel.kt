package gb.android.android_poplibs.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubRepoModel(
    @Expose @SerializedName("id") val id: String,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("owner") val owner: GithubRepoOwner,
    @Expose @SerializedName("forks_count") val forksCount: Int,
) : Parcelable

@Parcelize
data class GithubRepoOwner(
    @Expose @SerializedName("id") val id: String,
): Parcelable