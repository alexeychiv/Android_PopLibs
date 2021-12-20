package gb.android.android_poplibs.ui.repodetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import gb.android.android_poplibs.App
import gb.android.android_poplibs.databinding.FragmentRepoDetailsBinding
import gb.android.android_poplibs.model.GithubRepoModel
import gb.android.android_poplibs.ui.base.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoDetailsFragment : MvpAppCompatFragment(), RepoDetailsView, BackButtonListener {

    companion object {

        private const val KEY_REPO_MODEL = "KEY_REPO_MODEL"

        fun newInstance(githubRepoModel: GithubRepoModel): RepoDetailsFragment {
            return RepoDetailsFragment().apply {
                arguments = bundleOf(KEY_REPO_MODEL to githubRepoModel)
            }
        }
    }


    private var _binding: FragmentRepoDetailsBinding? = null
    private val binding: FragmentRepoDetailsBinding
        get() = _binding!!


    private val presenter by moxyPresenter {
        App.instance.initRepoDetailsSubcomponent()
        App.instance.repoDetailsSubcomponent?.repoDetailsPresenterFactory()
            ?.presenter(
                requireArguments()
                    .getParcelable<GithubRepoModel>(KEY_REPO_MODEL)!!
            )!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }

    override fun updateRepo(githubRepoModel: GithubRepoModel) {
        binding.tvName.text = githubRepoModel.name
        binding.tvForksCounter.text = githubRepoModel.forksCount.toString()
    }

}