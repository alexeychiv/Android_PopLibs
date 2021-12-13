package gb.android.android_poplibs.ui.userdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gb.android.android_poplibs.databinding.ItemRepoBinding
import gb.android.android_poplibs.model.GithubRepoModel

class RepoListAdapter(
    private val itemClickListener: (GithubRepoModel) -> Unit,
) : ListAdapter<GithubRepoModel, RepoListAdapter.RepoViewHolder>(GithubRepoItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.setRepo(currentList[position])
    }


    //==========================================================================
    // USER VIEW HOLDER

    inner class RepoViewHolder(
        private val binding: ItemRepoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setRepo(githubRepoModel: GithubRepoModel) {
            binding.tvRepoName.text = githubRepoModel.name

            binding.root.setOnClickListener {
                itemClickListener(githubRepoModel)
            }
        }

    }

}