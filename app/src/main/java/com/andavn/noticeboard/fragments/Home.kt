package com.andavn.noticeboard.fragments

import android.os.Bundle
import android.util.Log
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
    var array = mutableListOf<NoticeModel>()
    lateinit var databaseReference: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentHomeBinding.inflate(layoutInflater)

        databaseReference = Firebase.database.getReference("Notice")


        databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                     val notice = snapshot.getValue<NoticeModel>()

                      notice?.let { array.add(it) }

            }

            override fun onCancelled(error: DatabaseError) {

                Log.d("test",error.message)
            }
        })

        val adapter = Adapter()
        mBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        mBinding.recyclerView.adapter = adapter
        adapter.setData(array)
        mBinding.add.setOnClickListener {
            findNavController().navigate(R.id.createNotice)
        }
        return (mBinding.root)
    }

 //   fun showNotice(database: DatabaseReference) {

//        val dataChanged = object : ValueEventListener {
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//                val notice = snapshot.getValue<NoticeModel>(NoticeModel::class.java)
//
//                if (notice != null) {
//
//                    array.add(notice)
//
//                }
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(context, error.toException().toString(), Toast.LENGTH_LONG)
//                    .show()
//            }
//
//        }
//        database.addValueEventListener(dataChanged)
//    }

}

/*
* ref.addValueEventListener(new ValueEventListener() {
  @Override
  public void onDataChange(DataSnapshot dataSnapshot) {
    NoticeModel notice = dataSnapshot.getValue(NoticeModel.class);
    System.out.println(post);
  }*/


