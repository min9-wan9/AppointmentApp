package com.example.appointmentapp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appointmentapp.Domain.CategoryModel
import com.example.appointmentapp.Domain.DoctorModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel() : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    // lấy vị trí hiện tại

    private val _category = MutableLiveData<MutableList<CategoryModel>>()
    private val _doctors = MutableLiveData<MutableList<DoctorModel>>()



    val category :LiveData<MutableList<CategoryModel>> = _category
    val doctors : LiveData<MutableList<DoctorModel>> = _doctors


    fun loadCategory(){
        val ref = firebaseDatabase.getReference("Category")
        //lay du liệu có tên là category
        Log.w("dcm","category"+ref.toString())
        ref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<CategoryModel>()
                for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(CategoryModel::class.java)
                    if(list!= null){
                        lists.add(list)
                    }
                }

                _category.value = lists


            }


            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    fun loadDoctors(){
        Log.w("dcm","run load doctor")
        val Ref = firebaseDatabase.getReference("Doctors")
        Log.w("dcm",Ref.toString())

        Ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                Log.w("dcm","run On change")

                val lists = mutableListOf<DoctorModel>()
                for(childSnapshot in snapshot.children){
                    Log.w("dcm",childSnapshot.toString())
                    // nó chạy có đến đây là dừng
                    var list = childSnapshot.getValue(DoctorModel::class.java)
                    Log.w("dcm","$list")
                    if(list != null){
                        Log.w("dcm", "Doctor: ${list.toString()}")
                        lists.add(list)
                    }
                    else {
                        Log.w("dcm", "DoctorModel null hoặc không khớp với dữ liệu trên Firebase")
                    }
                }

                _doctors.value = lists
                Log.w("dcm","list:"+_doctors.value.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

}