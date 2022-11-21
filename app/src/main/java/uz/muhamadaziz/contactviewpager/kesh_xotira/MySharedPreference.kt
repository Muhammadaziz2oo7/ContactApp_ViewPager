package uz.muhamadaziz.contactviewpager.kesh_xotira

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.muhamadaziz.contactviewpager.models.UserData

object MySharedPreference {

    private const val NAME = "contact"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preference: SharedPreferences


    fun init(context: Context) {
        preference = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var contactList: ArrayList<UserData>
    get() =gsonStringToArray(preference.getString("contact", "[]"))
    set(value) = preference.edit {
        if (value != null){
it.putString("contact", arrayToGsonString(value))
        }
    }

    private fun gsonStringToArray(string: String?): java.util.ArrayList<UserData> {
val type = object : TypeToken<ArrayList<UserData>>() {}.type
        return Gson().fromJson(string,type)
    }

    private fun arrayToGsonString(value: java.util.ArrayList<UserData>): String? {
        return Gson().toJson(value)
    }


}