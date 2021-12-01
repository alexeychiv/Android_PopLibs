package gb.android.android_poplibs.ui.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import gb.android.android_poplibs.App
import gb.android.android_poplibs.databinding.FragmentUsersBinding
import gb.android.android_poplibs.domain.GithubUsersRepository
import gb.android.android_poplibs.ui.base.BackButtonListener
import gb.android.android_poplibs.ui.users.adapter.UsersAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var _binding: FragmentUsersBinding? = null
    private val binding: FragmentUsersBinding
        get() = _binding!!

    private val presenter by moxyPresenter {
        UsersPresenter(App.instance.router, GithubUsersRepository())
    }

    private val adapter by lazy {
        Log.d(
            "BLAH",
            "private val adapter by lazy --> ${presenter.usersListPresenter.users.toString()}"
        )
        UsersAdapter(presenter.usersListPresenter)
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

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }
}