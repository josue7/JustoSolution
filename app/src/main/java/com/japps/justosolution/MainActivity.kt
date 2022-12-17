package com.japps.justosolution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.japps.justosolution.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binder: ActivityMainBinding
    private lateinit var resultGenerateGender: String
    private lateinit var resultGenerateNationaliti: String

    override fun onCreate(savedInstanceState: Bundle?) {
        val numberResult = resources.getStringArray(R.array.number_result)

        binder = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binder.root)

        val arrAdapter = ArrayAdapter(this, R.layout.dropdown_item, numberResult)
        binder.actvResult.setAdapter(arrAdapter)

        binder.btnDataGenerate.setOnClickListener{
            Toast.makeText(this, "${generateQueryGender()} and ${generateQueryNationaliti()}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateQueryGender(): Boolean {
        var isValid = true
        resultGenerateGender = ""
        if (binder.cbMale.isChecked == true && binder.cbFemale.isChecked == true){
            binder.cbMale.error = "Solo elegir un genero"
            binder.cbMale.requestFocus()
            isValid = false
        } else {
            if (binder.cbMale.isChecked == true) {
                resultGenerateGender += "male"
            } else if (binder.cbFemale.isChecked == true){
                resultGenerateGender += "female"
            }
        }

        return isValid
    }

    private fun generateQueryNationaliti() {
        val query: MutableList<String> = mutableListOf()
        if (binder.cbBr.isChecked == true){
            query.add(resources.getString(R.string.br))
        }

        if (binder.cbAu.isChecked == true){
            query.add(resources.getString(R.string.au))
        }

        if (binder.cbCa.isChecked == true){
            query.add(resources.getString(R.string.ca))
        }

        if (binder.cbCh.isChecked == true){
            query.add(resources.getString(R.string.ch))
        }
        resultGenerateNationaliti = query.joinToString( separator = ",")

    }
}