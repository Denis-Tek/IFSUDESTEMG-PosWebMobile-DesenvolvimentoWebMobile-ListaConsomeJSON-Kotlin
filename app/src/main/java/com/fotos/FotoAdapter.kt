package com.fotos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class FotoAdapter(contexto: Context): ArrayAdapter<Foto>(contexto, 0) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val v: View

        if (convertView != null) {
            v = convertView
        } else {
            v = LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false)
        }

        val labelTitulo  = v.findViewById<TextView>(R.id.labelTitulo)
        val labelURL     = v.findViewById<TextView>(R.id.labelURL)

        val foto = this.getItem(position)

        if (foto != null) {
            labelTitulo.text = foto.title
            labelURL.text    = foto.url
        }

        return v
    }
}