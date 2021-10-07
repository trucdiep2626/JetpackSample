package com.example.jetpacksample2.slice

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.jetpacksample2.R
import com.example.jetpacksample2.databinding.ActivityDownloadManagerBinding
import com.example.jetpacksample2.databinding.ActivitySliceBinding

class SliceActivity : AppCompatActivity() {
    lateinit var binding: ActivitySliceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slice)

        binding = ActivitySliceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSlice.setOnClickListener {
            launchSliceViewerApplication()
        }
    }

    private fun launchSliceViewerApplication() {

        if (isSliceViewerApplicationInstalled() && isSliceViewerApplicationEnabled()) {
            val uri = getString(R.string.uri_specific_for_slice_viewer_application)
            val sliceViewerIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(sliceViewerIntent)
        }
    }

    private fun isSliceViewerApplicationInstalled(): Boolean {

        val packageManager = applicationContext.packageManager

        try {
            packageManager.getPackageInfo(getString(R.string.slice_viewer_application_package_name), PackageManager.GET_ACTIVITIES)
            return true
        } catch (ignored: PackageManager.NameNotFoundException) {

            val notInstalledToast = Toast.makeText(
                applicationContext,
                getString(R.string.slice_viewer_application_not_installed),
                Toast.LENGTH_LONG)

            notInstalledToast.show()

            //Log.e(TAG, getString(R.string.error_message_slice_viewer_APK_missing))
        }

        return false
    }

    private fun isSliceViewerApplicationEnabled(): Boolean {
        var status = false
        try {
            val applicationInfo =
                applicationContext.packageManager.getApplicationInfo(getString(R.string.slice_viewer_application_package_name), 0)

            if (applicationInfo != null) {
                status = applicationInfo.enabled
            }
        } catch (ignored: PackageManager.NameNotFoundException) {

            val notEnabledToast = Toast.makeText(
                applicationContext,
                getString(R.string.slice_viewer_application_not_enabled),
                Toast.LENGTH_LONG)

            notEnabledToast.show()

          //  Log.e(TAG, getString(R.string.error_message_slice_viewer_APK_disabled))
        }

        return status
    }
}