package gb.android.android_poplibs.ui.users.adapter

import androidx.recyclerview.widget.DiffUtil
import gb.android.android_poplibs.model.GithubUserModel


object GithubUserItemCallback : DiffUtil.ItemCallback<GithubUserModel>() {

    override fun areItemsTheSame(oldItem: GithubUserModel, newItem: GithubUserModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GithubUserModel, newItem: GithubUserModel): Boolean {
        return oldItem == newItem
    }

}