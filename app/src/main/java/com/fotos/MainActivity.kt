package com.fotos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import fotosGlobal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = FotoAdapter(this)
        listaFotos.adapter = adapter

        botaoAdicionar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        BotaoConsultar.setOnClickListener {
            this.carregarFotos()
            val adapter = listaFotos.adapter as FotoAdapter
            adapter.clear()
            adapter.addAll(fotosGlobal)
        }

        listaFotos.setOnItemLongClickListener {adapterView: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = adapter.getItem(position)
            adapter.remove(item)
            fotosGlobal.remove(item)
            return@setOnItemLongClickListener true
        }
    }

    override fun onResume() {
        super.onResume()
        val adapter = listaFotos.adapter as FotoAdapter
        adapter.clear()
        adapter.addAll(fotosGlobal)
    }

    fun carregarFotos() {
        try {
            val fotos = HttpService().execute().get()
            fotosGlobal.clear()
            fotosGlobal.addAll(fotos)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}