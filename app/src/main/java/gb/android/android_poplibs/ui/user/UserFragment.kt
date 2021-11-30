package gb.android.android_poplibs.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import gb.android.android_poplibs.App
import gb.android.android_poplibs.databinding.FragmentUserBinding
import gb.android.android_poplibs.model.GithubUserModel
import gb.android.android_poplibs.ui.base.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    private var _binding: FragmentUserBinding? = null
    private val binding: FragmentUserBinding
        get() = _binding!!


    private val presenter by moxyPresenter {
        UserPresenter(App.instance.router)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val githubDataModel : GithubUserModel = savedInstanceState?.get("githubDataModel") as GithubUserModel
        binding.tvUser.text = githubDataModel.login
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }
}