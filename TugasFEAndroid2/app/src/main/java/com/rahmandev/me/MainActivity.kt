package com.rahmandev.me

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.compose.material3.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var rvUsers: RecyclerView
    private lateinit var loadingme: LinearLayout

    private lateinit var adapter: UsersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listuser)
        loadingme  = findViewById(R.id.loadingLayout)
        rvUsers = findViewById(R.id.recyclerviewme)
        rvUsers.layoutManager = LinearLayoutManager(this)
        adapter = UsersAdapter()
        rvUsers.layoutManager = LinearLayoutManager(this)
        rvUsers.adapter = adapter

        lifecycleScope.launch {
            try {
                onSuccess()
                val users = RetrofitInstance.api.getUsers()
                for (user in users) {

                    val users = RetrofitInstance.api.getUsers()
                    adapter.submitList(users)
                    Log.v("/b/", "ID: ${user.id}, Name: ${user.name}, Email: ${user.email}")
                }
            } catch (e: Exception) {
                onError(e)
            }
        }



    }

    private fun onSuccess() {
        loadingme.visibility = View.GONE

    }

    private fun onError(e: Exception) {
        Toast.makeText(this, "Koneksi terputus", Toast.LENGTH_SHORT).show()

    }

}