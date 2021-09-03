package com.example.ispy.ui.hints

import androidx.recyclerview.widget.DiffUtil
import com.example.ispy.domain.entity.HintEntity

class HintDiffUtil(
    private val oldList: List<HintEntity>,
    private val newList: List<HintEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.distance == newItem.distance && oldItem.user == newItem.user
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.hint.contentEquals(newItem.hint)
    }
}
