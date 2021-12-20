package gb.android.android_poplibs.ui.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import gb.android.android_poplibs.App
import gb.android.android_poplibs.cache.RoomImageCache
import gb.android.android_poplibs.databinding.FragmentUserDetailsBinding
import gb.android.android_poplibs.db.AppDatabase
import gb.android.android_poplibs.model.GithubRepoModel
import gb.android.android_poplibs.model.GithubUserModel
import gb.android.android_poplibs.ui.base.BackButtonListener
import gb.android.android_poplibs.ui.imageloading.GlideImageLoader
import gb.android.android_poplibs.ui.imageloading.ImageLoader
import gb.android.android_poplibs.ui.userdetails.adapter.RepoListAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDetailsFragment(
    private val imageLoader: ImageLoader<ImageView>,
) : MvpAppCompatFragment(), UserDetailsView, BackButtonListener {

    companion object {

        private const val KEY_USER_MODEL = "KEY_USER_MODEL"

        fun newInstance(githubUserModel: GithubUserModel): UserDetailsFragment {
            return UserDetailsFragment(GlideImageLoader(RoomImageCache(AppDatabase.instance, App.instance))).apply {
                arguments = bundleOf(KEY_USER_MODEL to githubUserModel)
            }
        }
    }


    private var _binding: FragmentUserDetailsBinding? = null
    private val binding: FragmentUserDetailsBinding
        get() = _binding!!

    private val githubUserModel: GithubUserModel by lazy {
        requireArguments().getParcelable<GithubUserModel>(KEY_USER_MODEL) as GithubUserModel
    }

    private val presenter by moxyPresenter {
        App.instance.appComponent.userDetailsPresenterFactory().presenter(githubUserModel)
    }

    private val adapter by lazy {
        RepoListAdapter { presenter.onRepoClicked(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvUserRepoList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUserRepoList.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }

    override fun showLoading() {
        binding.loadingView.isVisible = true
    }

    override fun hideLoading() {
        binding.loadingView.isVisible = false
    }

    override fun updateUser(githubUserModel: GithubUserModel) {
        imageLoader.loadInto(githubUserModel.avatarUrl, binding.ivAvatar)
        binding.tvUser.text = githubUserModel.login
    }

    override fun updateRepoList(repos: List<GithubRepoModel>) {
        adapter.submitList(repos)
    }
}