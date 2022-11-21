package uz.muhamadaziz.contactviewpager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.muhamadaziz.contactviewpager.R
import uz.muhamadaziz.contactviewpager.databinding.UserItemBinding
import uz.muhamadaziz.contactviewpager.models.UserData

class MyDiffUtilAdapter(var context: Context, var contactList: ArrayList<UserData>, var onItemClick: OnItemClick): RecyclerView.Adapter<MyDiffUtilAdapter.DiffUtilViewHolder>()  {

    inner class DiffUtilViewHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
            fun onBind(userData: UserData, position: Int){
                binding.name.text = userData.name
                binding.phoneNumbers.text = userData.phoneNumbers

                binding.root.animation = AnimationUtils.loadAnimation(context, R.anim.combination)

                binding.root.setOnClickListener{
                    onItemClick.onClick(userData, position)
                }
            }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiffUtilViewHolder {
      return DiffUtilViewHolder(UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: DiffUtilViewHolder, position: Int) {
        holder.onBind(contactList[position], position)

    }

    override fun getItemCount(): Int {
        return contactList.size

    }
    interface OnItemClick{
        fun onClick(userData: UserData,position: Int)
    }
}