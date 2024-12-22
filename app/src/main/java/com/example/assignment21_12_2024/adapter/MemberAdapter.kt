package com.example.assignment21_12_2024.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment21_12_2024.databinding.ItemMemberBinding
import com.example.assignment21_12_2024.RoomDatabase.MemberEntity

class MemberAdapter(private val memberList: List<MemberEntity>) : RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {

    class MemberViewHolder(private val binding: ItemMemberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(member: MemberEntity) {
            binding.textName.text = "Member Name: ${member.name}"
            binding.textMobile.text = "Mobile Number: ${member.mobileNumber}"
            binding.textRole.text = "Role: ${member.role}"
            binding.textSubscriptionAmount.text = "Subscription Amount: ₹${member.subscriptionAmount}"
            binding.loanAmount.text = "₹${member.loanAmount}"
            binding.DepositAmount1.text = "₹${member.depositAmount}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val binding = ItemMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val member = memberList[position]
        holder.bind(member)
    }

    override fun getItemCount(): Int = memberList.size
}
