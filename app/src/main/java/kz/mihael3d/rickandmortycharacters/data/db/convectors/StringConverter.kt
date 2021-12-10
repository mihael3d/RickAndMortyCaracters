package kz.mihael3d.rickandmortycharacters.data.db.convectors

import androidx.room.TypeConverter

class StringConverter {
    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        return stringListString.split(",").map{it}
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString {","}
    }
}

