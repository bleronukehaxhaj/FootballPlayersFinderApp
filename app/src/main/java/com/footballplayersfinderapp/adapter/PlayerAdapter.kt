package com.footballplayersfinderapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.footballplayersfinderapp.R
import com.footballplayersfinderapp.models.Player

class PlayerAdapter(private val context: Context, private val list: List<Player>) : BaseAdapter() {


    private val layoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = layoutInflater.inflate(R.layout.player_item, parent, false)

        val ivPhoto = rowView.findViewById<ImageView>(R.id.ivPhoto)
        val tvFirstName = rowView.findViewById<TextView>(R.id.tvFirstName)
        val tvLastName = rowView.findViewById<TextView>(R.id.tvLastName)
        val tvAge = rowView.findViewById<TextView>(R.id.tvAge)

        ivPhoto.setImageResource(list[position].photo)
        tvFirstName.text = list[position].firstName
        tvLastName.text = list[position].lastName
        tvAge.text = list[position].age.toString()

        return rowView
    }
}