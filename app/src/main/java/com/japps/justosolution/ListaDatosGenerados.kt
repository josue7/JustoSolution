package com.japps.justosolution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.japps.justosolution.adaptador.DataGenerateAdapter
import com.japps.justosolution.constant.Constantes
import com.japps.justosolution.databinding.ActivityListaDatosGeneradosBinding
import com.japps.justosolution.model.GenerateData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.japps.justosolution.model.Result
import com.japps.justosolution.network.GenerateDataService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ListaDatosGenerados : AppCompatActivity() {
    private lateinit var binding: ActivityListaDatosGeneradosBinding
    private lateinit var itemGD: List<Result>
    private lateinit var dataGenerateAdapter: DataGenerateAdapter
    private lateinit var urlGenerate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaDatosGeneradosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        val resultGenerateGender = bundle?.getString("resultGenerateGender")
        val resultGenerateNationaliti = bundle?.getString("resultGenerateNationaliti")
        val numberResult = bundle?.getString("numberResult")
        urlGenerate = ""

        if (numberResult != null && numberResult != ""){
            if (numberResult == resources.getString(R.string.text_numberResult)){
                    urlGenerate = ""
                } else {
                urlGenerate += "?results=$numberResult"
            }

        }

        if (resultGenerateGender != null && resultGenerateGender != ""){
            if (urlGenerate != "") {
                urlGenerate += "&"
            } else {
                urlGenerate += "?"
            }
            urlGenerate += "gender=$resultGenerateGender"
        }

        if (resultGenerateNationaliti != null && resultGenerateNationaliti != ""){
            if (urlGenerate != "") {
                urlGenerate += "&"
            } else {
                urlGenerate += "?"
            }
            urlGenerate += "nat=$resultGenerateNationaliti"
        }


        Toast.makeText(this, "$urlGenerate", Toast.LENGTH_LONG).show()

        showDataGenerate()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constantes.USER_BASE_URL)
            .build()
    }

    private fun initRecyclerView(dataG: GenerateData) {
        if (dataG.info.results > 0){
            itemGD = dataG.results
        }

        dataGenerateAdapter = DataGenerateAdapter(itemGD)
        binding.rvListaDatosGenerate.setHasFixedSize(true)
        binding.rvListaDatosGenerate.layoutManager = LinearLayoutManager(this)
        binding.rvListaDatosGenerate.adapter = dataGenerateAdapter
    }

    private fun showError(){
        Toast.makeText(this, "Ha ocurrido algun error", Toast.LENGTH_SHORT).show()
    }

    private fun showDataGenerate(){
        Log.i("INIT", "Entro a la funcion showByOnlyProduct de Entrada")
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<GenerateData> = getRetrofit().create(GenerateDataService::class.java).getDataRandom(urlGenerate)
            //val call: Response<GenerateData> = getRetrofit().create(GenerateDataService::class.java).getDataRandom("?results=2&gender=male&nat=au")

            Log.i("INIT", "EntradaProducto ---- $call")
            if (call.code() == 200){
                val productEntrada: GenerateData? = call.body()
                runOnUiThread {
                    if (productEntrada?.info != null){
                        initRecyclerView(productEntrada)
                    } else {
                        showError()
                    }
                }//FIN runOnUiThread
            }//FIN PRIMER If
        } //FIN CoroutineScope
    }
}