package com.andavn.noticeboard.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andavn.noticeboard.Adapter
import com.andavn.noticeboard.NoticeModel
import com.andavn.noticeboard.R
import com.andavn.noticeboard.databinding.FragmentHomeBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class Home : Fragment() {


    lateinit var mBinding: FragmentHomeBinding
    lateinit var notice:ArrayList<NoticeModel>
    lateinit var databaseReference: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentHomeBinding.inflate(layoutInflater)

        notice = arrayListOf<NoticeModel>()

        mBinding.recyclerView.layoutManager = LinearLayoutManager(context)


        getNoticeModel()

        mBinding.add.setOnClickListener {
            findNavController().navigate(R.id.createNotice)
        }
        return (mBinding.root)
    }


    private fun getNoticeModel() {

        databaseReference = Firebase.database.getReference("Notice")

        databaseReference.addValueEventListener(object : ValueEventListener {
            lateinit var data:NoticeModel

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists())

                    for (notices in snapshot.children) {

                        var notic = snapshot.getValue(NoticeModel::class.java)

                        data.department = notic!!.department
                        data.title = notic!!.title
                        data.noticeDescription = notic!!.noticeDescription
                        data.profSignature = notic!!.profSignature

                        notice.add(data)

                        Toast.makeText(context,notice[0].department,Toast.LENGTH_LONG).show()

                    }
                mBinding.recyclerView.adapter = Adapter(notice)

            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

}