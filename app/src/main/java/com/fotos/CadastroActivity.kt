package com.fotos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cadastro.*
import fotosGlobal

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        botaoIncluir.setOnClickListener {
            val albumId      = txt_album_id.text.toString()
            val id           = txt_id.text.toString()
            val title        = labelTitulo.text.toString()
            val url          = txt_url.text.toString()
            val thumbnailUrl = txt_thumbnail_url.text.toString()

            if (albumId.isNotEmpty() && id.isNotEmpty() && title.isNotEmpty()) {
                val foto = Foto(id.toInt(), albumId.toInt(), title, url, thumbnailUrl)

                fotosGlobal.add(foto)

                txt_album_id.text.clear()
                txt_id.text.clear()
                labelTitulo.text.clear()
                txt_url.text.clear()
                txt_thumbnail_url.text.clear()

            } else {

                txt_album_id.error = if (txt_album_id.text.isEmpty()) "Preencha o id do album" else null
                txt_id.error       = if (txt_id.text.isEmpty())       "Preencha o id da foto"  else null
                labelTitulo.error    = if (labelTitulo.text.isEmpty())    "Preencha o titulo"      else null
            }

            finish();
        }
    }
}