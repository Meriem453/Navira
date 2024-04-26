package com.example.hackathonapp


import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore


class Test {
     fun loginUser(
        email: String,
        password: String,
        result: (String) -> Unit) {
         FirebaseAuth.getInstance().signInWithEmailAndPassword("${email}@gmail.com",password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    result.invoke("Login successfully!")
                }
            }.addOnFailureListener {
                result.invoke("Authentication failed, Check email and password")
            }
    }

     fun getChips( result: (List<ships>) -> Unit) {
         FirebaseFirestore.getInstance().collection("ships")
            .get()
            .addOnSuccessListener {
                val notes = arrayListOf<ships>()
                for (document in it) {
                    val name = document.get("name").toString()
                    notes.add(ships(name))
                }
                result(
                    notes)
            }
            .addOnFailureListener {
                result(
                        emptyList()
                )
            }
    }

}