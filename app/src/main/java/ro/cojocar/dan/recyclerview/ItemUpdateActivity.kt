package ro.cojocar.dan.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_item_add.*
import kotlinx.android.synthetic.main.activity_item_delete.*
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.activity_item_detail.detail_toolbar
import kotlinx.android.synthetic.main.activity_item_update.*
import ro.cojocar.dan.recyclerview.dummy.DummyContent

class ItemUpdateActivity: AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_update)
        setSupportActionBar(detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val symbol = intent.getStringExtra(ARG_ITEM_SYMB).toString()
        val stock = DummyContent.ITEM_MAP[symbol]


        tvUpdate.text = "Update " + symbol
        edUpdDesc.setText(stock?.desc)
        edUpdBuy.setText(stock?.buy_price.toString())
        edUpdSell.setText(stock?.sell_price.toString())

        btnUpdate.setOnClickListener{
            val desc = edUpdDesc.text.toString()
            val buy = Integer.parseInt(edUpdBuy.text.toString())
            val sell = Integer.parseInt(edUpdSell.text.toString())
            DummyContent.updateItem(symbol, desc, buy, sell)
            finish()
        }

    }
    companion object {
        const val ARG_ITEM_SYMB = "symbol"
    }
}