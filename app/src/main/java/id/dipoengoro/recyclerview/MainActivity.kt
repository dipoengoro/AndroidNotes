package id.dipoengoro.recyclerview

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this@MainActivity)[MainActivityViewModel::class.java]

        val viewRecycler = findViewById<RecyclerView>(R.id.recyclerView)
        val addEdit = findViewById<EditText>(R.id.editAdd)
        val addButton = findViewById<Button>(R.id.buttonAdd)

        viewRecycler.layoutManager = LinearLayoutManager(this@MainActivity)

        viewModel.items.observe(this@MainActivity) {

            viewRecycler.adapter = MyRecyclerViewAdapter(it) { name, position ->
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.message).format(name, position),
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.removeItem(position)
            }

            addButton.setOnClickListener { _ ->
                viewModel.addItem(addEdit.text.trim().toString())
                addEdit.text.clear()
                viewRecycler.scrollToPosition(it.lastIndex)
            }
        }
    }
}