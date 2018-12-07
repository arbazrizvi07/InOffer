package com.infrasoft.infraoffer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class NotificationListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_list)
        initRecycler()
    }

    private fun initRecycler() {
//        recycler.adapter = MyAdapter();
    }
}
