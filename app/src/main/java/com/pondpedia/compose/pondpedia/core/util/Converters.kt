package com.pondpedia.compose.pondpedia.core.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.PondEntity

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromPondDataJson(json: String): PondEntity? {
        return jsonParser.fromJson<PondEntity>(
            json,
            object: TypeToken<PondEntity>(){}.type
        )
    }

    @TypeConverter
    fun toPondDataJson(pondData: PondEntity): String {
        return jsonParser.toJson(
            pondData,
            object: TypeToken<PondEntity>(){}.type
        ) ?: ""
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toList(data: String): List<String> {
        return data.split(",")
    }

}