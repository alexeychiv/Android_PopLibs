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
import gb.android.android_poplibs.databinding.FragmentUserDetailsBinding
import gb.android.android_poplibs.db.AppDatabase
import gb.android.android_poplibs.domain.GithubRepoRepositoryImpl
import gb.android.android_poplibs.model.GithubRepoModel
import gb.android.android_poplibs.model.GithubUserModel
import gb.android.android_poplibs.remote.ApiHolder
import gb.android.android_poplibs.remote.connectivity.NetworkStatus
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
            return UserDetailsFragment(GlideImageLoader()).apply {
                arguments = bundleOf(KEY_USER_MODEL to githubUserModel)
            }
        }
    }


    private var _binding: FragmentUserDetailsBinding? = null
    private val binding: FragmentUserDetailsBinding
        get() = _binding!!


    private val presenter by moxyPresenter {
        UserDetailsPresenter(
            router = App.instance.router,
            githubUserModel = requireArguments().getParcelable<GithubUserModel>(KEY_USER_MODEL)!!,
            githubRepoRepository = GithubRepoRepositoryImpl(
                networkStatus = NetworkStatus(requireContext()),
                retrofitService = ApiHolder.retrofitService,
                db = AppDatabase.instance,
            )
        )
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