package com.sgkang.ch13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast

class AddActivity : AppCompatActivity() {
    lateinit var edit: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        edit = findViewById(R.id.edit)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        R.id.add -> {
            intent.putExtra("todo", edit.text.toString())
            setResult(RESULT_OK, intent)
            Toast.makeText(this, "${edit.text.toString()}", Toast.LENGTH_LONG).show()
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}