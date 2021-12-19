package kz.mihael3d.rickandmortycharacters.data.db.convectors

import androidx.room.TypeConverter
import com.google.gson.annotations.SerializedName
import kz.mihael3d.rickandmortycharacters.data.model.Status

class CharacterStatusTypeConvertor {
    @TypeConverter
    fun fromString(value: String): Status {
        return when(value){
          "Alive","alive" -> Status.ALIVE
            "Dead", "dead" -> Status.DEAD
            "Unknown",  "unknown" -> Status.UNKNOWN
            else -> Status.UNKNOWN
        }
    }


        @TypeConverter
        fun statusToString(status: Status): String {
            return status.status
        }
}
