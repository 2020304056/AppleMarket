package com.android.applemarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.applemarket.databinding.ItemBinding
import com.android.applemarket.MyItem
import java.text.DecimalFormat

class MyAdapter(private val mItems: MutableList<MyItem>) : RecyclerView.Adapter<MyAdapter.Holder>() {

    // 아이템 클릭
    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    // 롱 클릭
    interface ItemLongClick {
        fun onLongClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null
    var itemLongClick : ItemLongClick? = null

    // 홀더 바인딩, Holder
    inner class Holder(binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemImageView = binding.itemImage
        val tvItemTitle = binding.itemItemTitle
        val tvAddress = binding.itemAddress
        val tvPrice = binding.itemPrice
        val tvItemChat = binding.itemChatCnt
        val tvItemLike = binding.itemLikecnt
        val ivAdapterLike = binding.itemLike
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        // LongClickListener
        holder.itemView.setOnLongClickListener() OnLongClickListener@{
            itemLongClick?.onLongClick(it, position)
            return@OnLongClickListener true
        }

        holder.itemImageView.setImageResource(mItems[position].Image)
        holder.tvItemTitle.text = mItems[position].ItemTitle
        holder.tvAddress.text = mItems[position].Address

        val price = mItems[position].Price
        holder.tvPrice.text = DecimalFormat("#,###").format(price)+"원"

        holder.tvItemChat.text = mItems[position].ChatCnt.toString()
        holder.tvItemLike.text = mItems[position].InterestCnt.toString()

        if(mItems[position].isLike)
            holder.ivAdapterLike.setImageResource(R.drawable.heart2)
        else
            holder.ivAdapterLike.setImageResource(R.drawable.heart)
    }
}