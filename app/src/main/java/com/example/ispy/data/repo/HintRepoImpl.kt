package com.example.ispy.data.repo

import android.content.Context
import android.location.Location
import com.example.ispy.R
import com.example.ispy.data.model.HintData
import com.example.ispy.domain.repo.HintRepo
import com.example.ispy.domain.entity.HintEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type


class HintRepoImpl(private val context: Context) : HintRepo {
    val myJson: String? =
        inputStreamToString(context.resources.openRawResource(R.raw.hints_response))
    val listType: Type = object : TypeToken<List<HintData>>() {}.type
    private val hints: MutableList<HintData> = Gson().fromJson(myJson, listType)

    override fun getHints(): List<HintEntity> {
        return hints.map {
            it.toEntity()
        }
    }

    fun saveHint(hintEntity: HintEntity) {

    }

    private fun inputStreamToString(inputStream: InputStream): String? {
        return try {
            val bytes = ByteArray(inputStream.available())
            inputStream.read(bytes, 0, bytes.size)
            String(bytes)
        } catch (e: IOException) {
            null
        }
    }

    fun HintData.toEntity(): HintEntity {
        val results = FloatArray(1)
        Location.distanceBetween(
            0.0, 0.0, latitude, longitude, results
        )
        val rating = ratings.sumOf { it.userRating } / ratings.size
        return HintEntity(
            results[0].toString(),
            rating.toString(),
            createdUser.name,
            hintWins,
            hint
        )
    }
}
