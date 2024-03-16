package com.furkanmulayim.tarifce.presentation.fragment.shopping

interface ShoppingItemClickListener {
    fun onItemIsSold(id: Int, isSold: Int)
    fun onItemDelete(id: Int)
}