package ro.cojocar.dan.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_item_add.*
import kotlinx.android.synthetic.main.activity_item_delete.*
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.activity_item_detail.detail_toolbar
import ro.cojocar.dan.recyclerview.dummy.DummyContent

class ItemRemoveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_delete)

        val symbol = intent.getStringExtra(ARG_ITEM_SYMB).toString()
        tvConfirm.text = "Are you sure you want to remove " + symbol + " ?"
        btnRemoveNo.setOnClickListener{
            finish()
        }

        btnRemoveYes.setOnClickListener {
            DummyContent.removeItem(symbol)
            finish()
        }

    }
    companion object {
        const val ARG_ITEM_SYMB = "symbol"
    }
}