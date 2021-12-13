package gb.android.android_poplibs.ui.userdetails.adapter

import androidx.recyclerview.widget.DiffUtil
import gb.android.android_poplibs.model.GithubRepoModel
import gb.android.android_poplibs.model.GithubUserModel


object GithubRepoItemCallback : DiffUtil.ItemCallback<GithubRepoModel>() {

    override fun areItemsTheSame(oldItem: GithubRepoModel, newItem: GithubRepoModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GithubRepoModel, newItem: GithubRepoModel): Boolean {
        return oldItem == newItem
    }

}