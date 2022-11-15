package ro.cojocar.dan.recyclerview

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list_content.view.*
import ro.cojocar.dan.recyclerview.dummy.DummyContent

class ItemListActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_item_list)

    setSupportActionBar(toolbar)
    toolbar.title = title

    fab.setOnClickListener {
      val intent = Intent(this, ItemAddActivity::class.java)
      startActivity(intent)
    }

    setupRecyclerView(item_list)

    ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
      override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
      ): Boolean {
        return false
      }

      override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // this method is called when we swipe our item to right direction.
        // on below line we are getting the item at a particular position.
        val deletedStock: DummyContent.Stock =
          DummyContent.ITEMS.get(viewHolder.adapterPosition)
        val intent = Intent(viewHolder.itemView.context, ItemRemoveActivity::class.java).apply{
          putExtra(ItemRemoveActivity.ARG_ITEM_SYMB, deletedStock.symbol)
        }
        startActivity(intent)
        item_list.adapter?.notifyItemRemoved(viewHolder.adapterPosition)

      }

    }).attachToRecyclerView(item_list)

  }



  override fun onResume() {
    super.onResume()
    item_list.adapter?.notifyDataSetChanged()
  }

  private fun setupRecyclerView(recyclerView: RecyclerView) {
    recyclerView.adapter = SimpleItemRecyclerViewAdapter(DummyContent.ITEMS)
  }

  class SimpleItemRecyclerViewAdapter(
      private val values: List<DummyContent.Stock>
  ) : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener = View.OnClickListener { v ->
      val item = v.tag as DummyContent.Stock
      val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
        putExtra(ItemDetailFragment.ARG_ITEM_SYMB, item.symbol)
      }
      v.context.startActivity(intent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view = LayoutInflater.from(parent.context)
          .inflate(R.layout.item_list_content, parent, false)
      return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val item = values[position]
      holder.symbView.text = item.symbol
      holder.sellView.text = "S:"+ item.sell_price.toString()
      holder.buyView.text = "B: "+ item.buy_price.toString()

      with(holder.itemView) {
        tag = item
        setOnClickListener(onClickListener)
      }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
      val symbView: TextView = view.symb_text
      val sellView: TextView = view.sell_text
      val buyView: TextView = view.buy_text
    }
  }
}
