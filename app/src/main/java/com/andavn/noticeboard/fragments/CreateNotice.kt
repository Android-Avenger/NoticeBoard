package com.andavn.noticeboard.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.andavn.noticeboard.NoticeModel
import com.andavn.noticeboard.R
import com.andavn.noticeboard.databinding.FragmentCreateNoticeBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class CreateNotice : Fragment() {

    lateinit var mBinding: FragmentCreateNoticeBinding
    lateinit var reference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentCreateNoticeBinding.inflate(layoutInflater)

        mBinding.Done.setOnClickListener {

            val nTitle = mBinding.title.text.toString()
            val nDescription = mBinding.details.text.toString()
            val nDepartment = mBinding.department.text.toString()
            val nSignature = mBinding.signature.text.toString()


            val notice = NoticeModel(nTitle, nDescription, nDepartment, nSignature)

            reference = Firebase.database.getReference("Notice")

            reference.child(nTitle).setValue(notice).addOnSuccessListener {

                mBinding.title.text.clear()
                mBinding.details.text.clear()
                mBinding.department.text.clear()
                mBinding.signature.text.clear()


                Toast.makeText(context, "Data has been successfully", Toast.LENGTH_LONG).show()

            }.addOnFailureListener {

                Toast.makeText(context, "failure", Toast.LENGTH_LONG).show()

            }
            findNavController().findDestination(R.id.home)

        }

        return (mBinding.root)
    }
}
