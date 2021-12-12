package gb.android.android_poplibs.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import gb.android.android_poplibs.App
import gb.android.android_poplibs.databinding.FragmentUsersBinding
import gb.android.android_poplibs.domain.GithubUsersRepositoryImpl
import gb.android.android_poplibs.model.GithubUserModel
import gb.android.android_poplibs.remote.ApiHolder
import gb.android.android_poplibs.ui.base.BackButtonListener
import gb.android.android_poplibs.ui.imageloading.GlideImageLoader
import gb.android.android_poplibs.ui.users.adapter.UsersAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var _binding: FragmentUsersBinding? = null
    private val binding: FragmentUsersBinding
        get() = _binding!!

    private val presenter by moxyPresenter {
        UsersPresenter(App.instance.router, GithubUsersRepositoryImpl(ApiHolder.retrofitService))
    }

    private val adapter by lazy {
        UsersAdapter(presenter::onUserClicked, GlideImageLoader())
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.usersRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.usersRecycler.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun updateList(users: List<GithubUserModel>) {
        adapter.submitList(users)
    }

    override fun showLoading() {
        binding.loadingView.isVisible = true
        binding.usersRecycler.isVisible = false
    }

    override fun hideLoading() {
        binding.loadingView.isVisible = false
        binding.usersRecycler.isVisible = true
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }
}