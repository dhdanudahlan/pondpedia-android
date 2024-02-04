package com.pondpedia.android.pondpedia.core.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.PondEntity
import java.util.Date

@ProvidedTypeConverter
class Converters(
) {
    /*@TypeConverter
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
    }*/


    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
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
//object JsonAdapter {
//    @FromJson
//    fun fromJson(idIndeterminate: IdIntermediate): Id {
//        return idIndeterminate.id?.let { Id(idIndeterminate.id) }
//            ?: idIndeterminate.pk?.let { Id(idIndeterminate.pk) } ?: Id(null)
//    }
//    @ToJson
//    fun toJson(@IdQualifier id: Id): IdIntermediate {
//        return IdIntermediate(id = id.id, pk = null)
//    }
//}