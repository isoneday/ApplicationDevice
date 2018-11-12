package com.isoneday.applicationdevice

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.ActivityCompat
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_call_phone.*
import org.jetbrains.anko.makeCall
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class CallPhoneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_phone)

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission
                    (Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.CALL_PHONE),
                    10)
            }
            return
        }

        btncall.onClick {
            toast("test")
            if (TextUtils.isEmpty(edtnumber.text.toString())){
                edtnumber.error = "tidak boleh kosong"
                edtnumber.requestFocus()
            }else{
                makeCall(edtnumber.text.toString())
            }
        }
        btntampilcall.onClick {
            if (TextUtils.isEmpty(edtnumber.text.toString())){
                edtnumber.error = "tidak boleh kosong"
                edtnumber.requestFocus()
            }else{
                startActivity(Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:"+edtnumber.text.toString()
                )))
            }
        }
        btnlistcontact.onClick {
            val i = Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI)
            i.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(i,1)
        }
    }
    // untuk menangkap result dari aksi yang dilakukuan ex : memilih kontak telfon
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==1&&resultCode== Activity.RESULT_OK){
            var cursor: Cursor? = null
            try {
                val uri = data?.data
                cursor = contentResolver.query(uri!!, arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER), null, null, null)
                if (cursor != null && cursor!!.moveToNext()) {
                    val phone = cursor!!.getString(0)
                    edtnumber.setText(phone)
                }
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }

}
