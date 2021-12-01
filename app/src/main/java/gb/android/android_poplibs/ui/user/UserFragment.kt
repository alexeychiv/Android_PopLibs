package gb.android.android_poplibs.ui.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import gb.android.android_poplibs.App
import gb.android.android_poplibs.databinding.FragmentUserBinding
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
        Log.d("BLAH", "onCreateView --> savedInstanceState = ${savedInstanceState.toString()}")

        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("BLAH", "onViewCreated --> savedInstanceState = ${savedInstanceState.toString()}")

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        Log.d("BLAH", "onViewStateRestored --> savedInstanceState = ${savedInstanceState.toString()}")


        if (savedInstanceState != null) {
            binding.tvUser.text = savedInstanceState.get("login") as String
        }
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