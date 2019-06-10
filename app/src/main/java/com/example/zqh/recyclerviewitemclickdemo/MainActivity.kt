package com.example.zqh.recyclerviewitemclickdemo

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.recycler_item.view.*

/**
 * 参考：https://juejin.im/post/5cf9f184e51d45775b419b9f
 * （非个人实现）
 * 简单的实现类型listview的onitemclicklistener
 */
class MainActivity : AppCompatActivity() {
    lateinit var mRecyclerView: BaseRecyclerView

    lateinit var mAdapter: MyRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView = findViewById(R.id.recyclerview)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        var list = mutableListOf<String>()
        for (i in 0 until 20) {
            list.add("hello world $i")
        }

        mAdapter = MyRecyclerAdapter(this, list)
        mRecyclerView.adapter = mAdapter

        mRecyclerView.setOnItemClickListener(object : BaseRecyclerView.ItemOnClickListener {
            override fun onItemClick(view: View, position: Int, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
                val titleView = view.findViewById<TextView>(R.id.item_title_tv)
                if (titleView == null) return
                val str = titleView.text

                Log.i("onItemClick", str.toString())

            }
        })

    }


    class MyRecyclerAdapter(val context: Context, val list: MutableList<String>) : RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, type: Int): MyViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)
            return MyViewHolder(view)
        }

        override fun getItemCount(): Int {
            if (list != null) return list.size

            return 0
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.itemView.item_title_tv.text = list.get(position)

        }


        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    }


}
