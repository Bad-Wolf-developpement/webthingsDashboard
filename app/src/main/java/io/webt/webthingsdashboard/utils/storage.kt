package io.webt.webthingsdashboard.utils

import android.content.Context
import android.util.Log
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.File

/*
 * library of function that handle the storage of data
 */
val gwSettingsFilename = "GwSettings.wt"
val TAG = "storage"

fun SaveGwSettings(context: Context, data: MutableMap<String, Any>){
    Log.d(TAG,"Data to save : $data")
    val fileOutput = context.openFileOutput(gwSettingsFilename, Context.MODE_PRIVATE)
    val outputStream = ObjectOutputStream(fileOutput)
    outputStream.writeObject(data)
}

fun LoadGwSettings(context: Context): Any{

    var file = File(context.filesDir.absolutePath + "/" + gwSettingsFilename)
    if (!file.exists()){
        Log.d(TAG, "Gw settings file not found")
        return false
    }
    val fileInput = context.openFileInput(gwSettingsFilename)
    val inputStream = ObjectInputStream(fileInput)
    val data = inputStream.readObject() as MutableMap<String, Any>
    Log.d(TAG, "Data to save $data")
    return data
}
