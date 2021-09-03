package com.example.ispy.domain.repo

import com.example.ispy.domain.entity.HintEntity

interface HintRepo {
    fun getHints(): List<HintEntity>
}
