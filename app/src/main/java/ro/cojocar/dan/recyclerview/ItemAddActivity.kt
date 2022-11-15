package ro.cojocar.dan.recyclerview

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_item_add.*
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.activity_item_detail.detail_toolbar
import ro.cojocar.dan.recyclerview.dummy.DummyContent

class ItemAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_add)
        setSupportActionBar(detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        btnAdd.setOnClickListener{
            val stock: DummyContent.Stock = DummyContent.Stock(edSymbol.text.toString(), edDesc.text.toString(), Integer.parseInt(edBuy.text.toString()), Integer.parseInt(edSell.text.toString()) )
            DummyContent.addItem(stock)
            finish()
        }
    }
}