package com.example.jetpacksample2.slice

import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.*
import com.example.jetpacksample2.MainActivity

class MySliceProvider : SliceProvider() {
    /**
     * Instantiate any required objects. Return true if the provider was successfully created,
     * false otherwise.
     */
    lateinit var brightnessPendingIntent: PendingIntent
    override fun onCreateSliceProvider(): Boolean {
        return true
    }

    /**
     * Converts URL to content URI (i.e. content://com.example.jetpacksample2.slice...)
     */
    override fun onMapIntentToUri(intent: Intent?): Uri {
        // Note: implementing this is only required if you plan on catching URL requests.
        // This is an example solution.
        var uriBuilder: Uri.Builder = Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT)
        if (intent == null) return uriBuilder.build()
        val data = intent.data
        val dataPath = data?.path
        if (data != null && dataPath != null) {
            val path = dataPath.replace("/", "")
            uriBuilder = uriBuilder.path(path)
        }
        val context = context
        if (context != null) {
            uriBuilder = uriBuilder.authority(context.packageName)
        }
        return uriBuilder.build()
    }

    /**
     * Construct the Slice and bind data if available.
     */
    override fun onBindSlice(sliceUri: Uri): Slice? {
        // Note: you should switch your build.gradle dependency to
        // slice-builders-ktx for a nicer interface in Kotlin.
        val context = context ?: return null

       // println(sliceUri.path)
        val activityAction = createActivityAction() ?: return null
        println(sliceUri.path.equals("/hello"))
         if (sliceUri.path.equals("/hello") == true) {

             return createBrightnessSlice(sliceUri)
//             list(context, sliceUri, ListBuilder.INFINITY)
//             {
//                 header {
//                   setPrimaryAction(activityAction)
//                   setTitle("Hello World.")
//
//                 }
//             }
        } else {
             return  ListBuilder(context, sliceUri, ListBuilder.INFINITY)
                .addRow(
                    ListBuilder.RowBuilder(context, sliceUri)
                        .setTitle("URI not found.")
                        //.setPrimaryAction(activityAction)
                )
                .build()
        }
    }

    private fun createActivityAction(): SliceAction? {
        val intent = Intent(context, MainActivity::class.java)
        return SliceAction(
            PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), 0),
            IconCompat.createWithResource(context, R.drawable.abc_menu_hardkey_panel_mtrl_mult),
            ListBuilder.ICON_IMAGE,
            "Enter app"
        );

    }

    fun createSliceWithHeader(sliceUri: Uri) =
        list(context!!, sliceUri, ListBuilder.INFINITY) {
            setAccentColor(0xff0F9D) // Specify color for tinting icons
            header {
                setTitle("Get a ride")
                setSubtitle("Ride in 4 min")
               setSummary("Work in 1 hour 45 min | Home in 12 min")
            }
            row {
                setTitle("Home")
                setSubtitle("12 miles | 12 min | $9.00")

            }
        }
    fun createBrightnessSlice(sliceUri: Uri): Slice {
        val toggleAction =
            SliceAction (
                createToggleIntent(),
                "Toggle adaptive brightness",
                true
            )
        return list(context!!, sliceUri, ListBuilder.INFINITY) {
            row {
                setTitle("Adaptive brightness")
                setPrimaryAction( toggleAction)
            }

        }
    }
    fun createToggleIntent(): PendingIntent {
        val intent = Intent(context, MyBroadcastReceiver::class.java)
        return PendingIntent.getBroadcast(context, 0, intent, 0)
    }
    /**
     * Slice has been pinned to external process. Subscribe to data source if necessary.
     */
    override fun onSlicePinned(sliceUri: Uri?) {
        // When data is received, call context.contentResolver.notifyChange(sliceUri, null) to
        // trigger MySliceProvider#onBindSlice(Uri) again.
    }

    /**
     * Unsubscribe from data source if necessary.
     */
    override fun onSliceUnpinned(sliceUri: Uri?) {
        // Remove any observers if necessary to avoid memory leaks.
    }
}