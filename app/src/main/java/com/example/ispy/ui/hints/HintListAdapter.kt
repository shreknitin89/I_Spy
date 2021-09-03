package com.example.ispy.ui.hints

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ispy.R
import com.example.ispy.data.model.HintData
import com.example.ispy.databinding.HintCardBinding
import com.example.ispy.domain.entity.HintEntity

class HintListAdapter(_data: List<HintEntity>) : RecyclerView.Adapter<HintViewHolder>() {
    private val data = ArrayList(_data)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HintViewHolder {
        val binding = HintCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HintViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HintViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateList(newList: List<HintEntity>) {
        val callback = HintDiffUtil(data, newList)
        val result = DiffUtil.calculateDiff(callback)
        data.clear()
        data.addAll(newList)
        result.dispatchUpdatesTo(this)
    }
}

class HintViewHolder(private val binding: HintCardBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(hintData: HintEntity) {
        with(binding) {
            hint.text = hintData.hint
            rating.text = hintData.rating
            winsCount.text = hintData.wins.toString()
            userName.text =
                binding.root.context.getString(R.string.by_author, hintData.user)
            distance.text = hintData.distance
        }
    }
}
