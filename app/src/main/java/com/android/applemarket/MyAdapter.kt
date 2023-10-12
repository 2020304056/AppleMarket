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
        val Image = binding.itemImage
        val Id = binding.itemId
        val Address = binding.itemAddress
        val Price = binding.itemPrice
        val Chatting = binding.itemChatting
        val HeartNum = binding.itemHeartnum
        val Heart = binding.itemHeart
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

        holder.Image.setImageResource(mItems[position].Image)
        holder.Id.text = mItems[position].Id
        holder.Address.text = mItems[position].Address

        val price = mItems[position].Price
        holder.Price.text = DecimalFormat("#,###").format(price)+"원"

        holder.Chatting.text = mItems[position].Chatting.toString()
        holder.HeartNum.text = mItems[position].Interest.toString()

        if(mItems[position].heart)
            holder.Heart.setImageResource(R.drawable.heart2)
        else
            holder.Heart.setImageResource(R.drawable.heart)
    }
}