package uz.muhamadaziz.contactviewpager

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import uz.muhamadaziz.contactviewpager.adapters.MyDiffUtilAdapter
import uz.muhamadaziz.contactviewpager.databinding.ActivityMainBinding
import uz.muhamadaziz.contactviewpager.kesh_xotira.MySharedPreference
import uz.muhamadaziz.contactviewpager.models.UserData

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyDiffUtilAdapter
    private lateinit var contactList: ArrayList<UserData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MySharedPreference.init(this)
        contactList = MySharedPreference.contactList

        for (i in 0..500){
            contactList.add(UserData("Muhammadaziz", "+998907859907"))
        }
        adapter = MyDiffUtilAdapter(this, contactList, object : MyDiffUtilAdapter.OnItemClick{
            override fun onClick(userData: UserData, position: Int) {

                if (ActivityCompat.checkSelfPermission(this@MainActivity,android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this@MainActivity,
                        arrayOf(android.Manifest.permission.CALL_PHONE), 101)
                    return
                }
                val intent = Intent(Intent.ACTION_CALL)
                intent.data = Uri.parse("tel:${userData.phoneNumbers}")
                startActivity(intent)
            }


        })
        binding.recyclerView.adapter = adapter

        binding.add.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }

        binding.klaviatura.setOnClickListener {
            startActivity(Intent(this, Klaviatura::class.java))
            finish()
        }

    }
}