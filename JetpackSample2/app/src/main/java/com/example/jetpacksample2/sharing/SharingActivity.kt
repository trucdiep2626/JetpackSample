package com.example.jetpacksample2.sharing

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.jetpacksample2.R
import com.example.jetpacksample2.databinding.ActivitySharingBinding
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@Suppress("DEPRECATION")
class SharingActivity : AppCompatActivity() {
    lateinit var binding: ActivitySharingBinding
    val CAMERE_REQUEST= 102
    lateinit var filePath:String

    val allPermission : Array<String> = arrayOf( Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sharing)
        binding = ActivitySharingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCapture.setOnClickListener {
            if(Build.VERSION.SDK_INT > 23)
            {
                if(checkPermission(allPermission))
                {
                    captureImage()
                }
                else
                {
                    requestPermission(allPermission)
                }
            }
            else
            {
                captureImage()
            }
        }

        binding.btnShare.setOnClickListener {
            val intent:Intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, "Demo Title")
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            {

                val path: Uri = FileProvider.getUriForFile(this, "com.example.jetpacksample2.sharing",File(filePath))
                println(filePath)
                intent.putExtra(Intent.EXTRA_STREAM, path)
               // intent.putExtra(Intent.EXTRA_TEXT, "sharing")
            }
            else{
                intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(File(filePath)))
            }
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.setType("image/*")
            startActivity(intent)
        }
    }

    fun captureImage(){
        val intent :Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(
            intent,
            CAMERE_REQUEST
        )
    }
    fun checkPermission(permissions: Array<String> ) : Boolean
    {
        for( i in 0..(permissions.size-1))
        {
            val result = ContextCompat.checkSelfPermission(this, permissions[i])
            if(result == PackageManager.PERMISSION_GRANTED)
                continue
            else
                return false
        }
        return true;
    }

    fun requestPermission(permissions: Array<String> )
    {
         ActivityCompat.requestPermissions(this, permissions, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK)
        {
            if(requestCode == CAMERE_REQUEST)
            {
                if(data != null)
                {
                    var image : Bitmap = data.extras?.get("data") as Bitmap
                    var bytes : ByteArrayOutputStream= ByteArrayOutputStream()

                    image.compress(Bitmap.CompressFormat.JPEG,100,bytes)
                    val dest: File = File(Environment.getExternalStorageDirectory(),"temp_img.ipg")
                    if(dest.exists())
                    {
                        dest.delete()
                    }
                    var fileOutputStream:FileOutputStream
                    try {
                        fileOutputStream = FileOutputStream(dest)
                        fileOutputStream.write(bytes.toByteArray())
                        fileOutputStream.close()
                    }
                    catch (e: IOException)
                    {
                        println(e)
                    }
                    binding.ivPreviewImage.setImageBitmap(image)

                    filePath=dest.path
                }
            }
        }
    }
}