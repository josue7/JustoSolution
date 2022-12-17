package com.japps.justosolution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.japps.justosolution.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binder: ActivityMainBinding
    private lateinit var attributesAPI: String

    override fun onCreate(savedInstanceState: Bundle?) {
        val numberResult = resources.getStringArray(R.array.number_result)

        binder = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binder.root)

        val arrAdapter = ArrayAdapter(this, R.layout.dropdown_item, numberResult)
        binder.actvResult.setAdapter(arrAdapter)

        binder.btnDataGenerate.setOnClickListener{
            attributesAPI = generateQueryGender()
        }
    }

    private fun generateQueryGender(): String {
        var query = ""
        if (binder.cbMale.isChecked == true && binder.cbFemale.isChecked == true){
            binder.cbMale.error = "Solo elegir un genero"
            binder.cbMale.requestFocus()
        } else {
            if (binder.cbMale.isChecked == true) {
                query += "male"
            }
            if (binder.cbFemale.isChecked == true){
                query += "female"
            }
        }
        return query
    }

    private fun generateQueryNationaliti(): String {
        var query = ArrayList<String>()
        if (binder.cbBr.isChecked == true){
            query.add(resources.getString(R.string.br))
        }
    }
}