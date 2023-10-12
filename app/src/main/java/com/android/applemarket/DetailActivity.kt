package com.android.applemarket

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.android.applemarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import com.android.applemarket.MyItem
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val item: MyItem? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.ITEM_OBJECT, MyItem::class.java)
        } else {
            intent.getParcelableExtra<MyItem>(Constants.ITEM_OBJECT)
        }
    }

    private val itemPosition: Int by lazy {
        intent.getIntExtra(Constants.ITEM_INDEX, 0)
    }

    private var Love = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.detailImage.setImageDrawable(item?.let {
            ResourcesCompat.getDrawable(resources, it.Image, null)
        })
        binding.detailSolder.text = item?.Solder
        binding.detailAddress.text = item?.Address
        binding.detailId.text = item?.Id
        binding.detailDescription.text = item?.Description
        binding.detailPrice.text = DecimalFormat("#,###").format(item?.Price) + "원"
        Love = item?.heart == true
        binding.detailHeart.setImageResource(if (Love) {R.drawable.heart2}else{R.drawable.heart})

        binding.detailBack.setOnClickListener {
            exit()
        }

        binding.detailLike.setOnClickListener {
            if(!Love){
                binding.detailHeart.setImageResource(R.drawable.heart2)
                Snackbar.make(binding.constLayout, "관심 목록에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
                Love = true
            }else {
                binding.detailHeart.setImageResource(R.drawable.heart)
                Love = false
            }
        }
    }

    fun exit() {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("itemIndex", itemPosition)
            putExtra("Love", Love)
        }
        setResult(RESULT_OK, intent)
        if (!isFinishing) finish()
    }

    override fun onBackPressed() {
        exit()
    }
}