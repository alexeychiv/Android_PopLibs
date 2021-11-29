package gb.android.android_poplibs

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import gb.android.android_poplibs.databinding.ActivityUsersBinding
import gb.android.android_poplibs.domain.GithubUsersRepository
import gb.android.android_poplibs.ui.users.UsersPresenter
import gb.android.android_poplibs.ui.users.UsersView
import gb.android.android_poplibs.ui.users.adapter.UsersAdapter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class UsersActivity : MvpAppCompatActivity(), UsersView {

    private var _binding: ActivityUsersBinding? = null
    private val binding: ActivityUsersBinding
        get() = _binding!!

    private val adapter by lazy {
        UsersAdapter(presenter.usersListPresenter)
    }

    private val presenter by moxyPresenter { UsersPresenter(GithubUsersRepository()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.usersRecycler.layoutManager = LinearLayoutManager(this)
        binding.usersRecycler.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }
}