package uz.muhamadaziz.contactviewpager

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import uz.muhamadaziz.contactviewpager.databinding.ActivityKlaviaturaBinding

class Klaviatura : AppCompatActivity() {
    private lateinit var binding: ActivityKlaviaturaBinding
    var numberEdt = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKlaviaturaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()

    }

    private fun loadData() {

        binding.nolPlus.setOnLongClickListener {
            numberEdt += "+"
            binding.edit.setText(numberEdt)
            true
        }

        binding.nolPlus.setOnClickListener {
            numberEdt += "0"
            binding.edit.setText(numberEdt)
        }
        binding.bir.setOnClickListener {
            numberEdt += "1"
            binding.edit.setText(numberEdt)
        }
        binding.ikki.setOnClickListener {
            numberEdt += "2"
            binding.edit.setText(numberEdt)
        }
        binding.uch.setOnClickListener {
            numberEdt += "3"
            binding.edit.setText(numberEdt)
        }
        binding.tort.setOnClickListener {
            numberEdt += "4"
            binding.edit.setText(numberEdt)
        }
        binding.besh.setOnClickListener {
            numberEdt += "5"
            binding.edit.setText(numberEdt)
        }
        binding.olti.setOnClickListener {
            numberEdt += "6"
            binding.edit.setText(numberEdt)
        }
        binding.yetti.setOnClickListener {
            numberEdt += "7"
            binding.edit.setText(numberEdt)
        }
        binding.sakkiz.setOnClickListener {
            numberEdt += "8"
            binding.edit.setText(numberEdt)
        }
        binding.toqqiz.setOnClickListener {
            numberEdt += "9"
            binding.edit.setText(numberEdt)
        }
        binding.yulduzcha.setOnClickListener {
            numberEdt += "*"
            binding.edit.setText(numberEdt)
        }
        binding.rishotka.setOnClickListener {
            numberEdt += "#"
            binding.edit.setText(numberEdt)
        }

        binding.backSpace.setOnClickListener {

            val a = numberEdt
            if (numberEdt.isNotEmpty()) {
                numberEdt = a.substring(0, a.length - 1)
                binding.edit.setText(numberEdt)
            } else numberEdt = ""
        }
        binding.backSpace.setOnLongClickListener {
            numberEdt = ""
            binding.edit.setText(numberEdt)
            true
        }


        binding.call.setOnClickListener {

            if (ActivityCompat.checkSelfPermission(
                    this@Klaviatura,
                    android.Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@Klaviatura,
                    arrayOf(android.Manifest.permission.CALL_PHONE),
                    101
                )
                return@setOnClickListener
            }
            val call = Intent(Intent.ACTION_CALL)
            if (numberEdt in "*" && numberEdt in "#") {
                val a = Uri.encode("#")
                call.data = Uri.parse("tel:$numberEdt$a")
                if (ActivityCompat.checkSelfPermission(
                        this@Klaviatura,
                        Manifest.permission.CALL_PHONE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return@setOnClickListener
                }
            } else {
                call.data = Uri.parse("tel:$numberEdt")
                startActivity(call)

                numberEdt = ""
                binding.edit.setText(numberEdt)
            }
        }

        binding.kontaktlar.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}