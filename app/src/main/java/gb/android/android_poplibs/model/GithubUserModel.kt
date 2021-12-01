package gb.android.android_poplibs.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class GithubUserModel(
    val login: String
) : Parcelable
