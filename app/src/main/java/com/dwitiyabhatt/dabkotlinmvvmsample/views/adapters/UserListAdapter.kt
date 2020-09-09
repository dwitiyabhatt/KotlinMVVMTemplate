package com.dwitiyabhatt.dabkotlinmvvmsample.views.adapters

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dwitiyabhatt.dabkotlinmvvmsample.R
import com.dwitiyabhatt.dabkotlinmvvmsample.models.pojo.user_listing_responses.UserDetails
import kotlinx.android.synthetic.main.row_user.view.*


class UserListAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserDetails>() {

        override fun areItemsTheSame(oldItem: UserDetails, newItem: UserDetails): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: UserDetails, newItem: UserDetails): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return UserDetailsVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_user,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserDetailsVH -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<UserDetails>) {
        differ.submitList(list)
    }

    class UserDetailsVH constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: UserDetails) = with(itemView) {

            itemView.tvName.setText(item.firstName+" "+item.lastName)
            itemView.tvEmail.setText(item.email)
            val requestOptions = RequestOptions.placeholderOf(R.drawable.ic_launcher_background).
            error(R.drawable.ic_launcher_foreground)
            Glide.with(itemView.context).applyDefaultRequestOptions(requestOptions)
                .load(item.avatar).into(ivProfilePic)

            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }


        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: UserDetails)
    }
}
