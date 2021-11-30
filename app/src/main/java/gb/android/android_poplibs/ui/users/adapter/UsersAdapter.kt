package gb.android.android_poplibs.ui.users.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gb.android.android_poplibs.App
import gb.android.android_poplibs.databinding.ItemUserBinding
import gb.android.android_poplibs.screens.AppScreens
import gb.android.android_poplibs.ui.users.UserItemView
import gb.android.android_poplibs.ui.users.UsersPresenter

class UsersAdapter(
    private val presenter: UsersPresenter.UsersListPresenter
) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                Log.d("BLAH", "itemView.setOnClickListener $pos")
                presenter.itemClickListener
            }
        }
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }

    //==========================================================================
    // USER VIEW HOLDER

    inner class UserViewHolder(
        private val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root), UserItemView {

        override var pos: Int = -1

        override fun setLogin(login: String) {
            binding.tvLogin.text = login
        }

    }

}


