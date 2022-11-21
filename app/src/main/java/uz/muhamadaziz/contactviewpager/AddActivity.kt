package uz.muhamadaziz.contactviewpager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.muhamadaziz.contactviewpager.databinding.ActivityAddBinding
import uz.muhamadaziz.contactviewpager.kesh_xotira.MySharedPreference
import uz.muhamadaziz.contactviewpager.models.UserData

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MySharedPreference.init(this)

        binding.save.setOnClickListener {
            if (binding.contactname.text!!.isEmpty() && binding.contactnumbers.text!!.isEmpty()){
                Toast.makeText(this, "complete the list", Toast.LENGTH_SHORT).show()
            }else{
                val name = binding.contactname.text.toString()
                val phoneNumbers = binding.contactnumbers.text.toString()

                val contactList = MySharedPreference.contactList
                val userData = UserData(name, phoneNumbers)
                contactList.add(userData)
                MySharedPreference.contactList = contactList

                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
        }
    }
}