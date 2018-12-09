package com.infrasoft.infraoffer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_notification_list.*


class NotificationListActivity : AppCompatActivity() {

    val list = ArrayList<Offer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_list)
        initRecycler()
    }

    private fun initRecycler() {
        recycler.adapter = MyAdapter(list, this)
        getOfferList()
    }

    private fun getOfferList() {
        val db = FirebaseFirestore.getInstance()
        db.collection("notification")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        val offer = Offer(
                            document.data.get("offerId") as String,
                            document.data.get("dealname") as String,
                            document.data.get("discription") as String
                        )
                        list.add(offer)
                    }
                    recycler.adapter?.notifyDataSetChanged()
                }
            }
    }
}
