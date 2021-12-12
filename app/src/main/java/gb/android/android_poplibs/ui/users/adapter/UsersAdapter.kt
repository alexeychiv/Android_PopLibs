package gb.android.android_poplibs.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gb.android.android_poplibs.databinding.ItemUserBinding
import gb.android.android_poplibs.model.GithubUserModel
import gb.android.android_poplibs.ui.imageloading.ImageLoader


class UsersAdapter(
    private val itemClickListener: (GithubUserModel) -> Unit,
    private val imageLoader: ImageLoader<ImageView>,
) : ListAdapter<GithubUserModel, UsersAdapter.UserViewHolder>(GithubUserItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.setLogin(currentList[position])
    }


    //==========================================================================
    // USER VIEW HOLDER

    inner class UserViewHolder(
        private val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setLogin(githubUserModel: GithubUserModel) {
            binding.tvLogin.text = githubUserModel.login

            imageLoader.loadInto(githubUserModel.avatarUrl, binding.ivAvatar)

            binding.root.setOnClickListener {
                itemClickListener(githubUserModel)
            }
        }

    }

}

