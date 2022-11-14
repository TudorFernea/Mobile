package ro.cojocar.dan.recyclerview.dummy

import java.util.*

object DummyContent {

  val ITEMS: MutableList<Stock> = ArrayList()

  val ITEM_MAP: MutableMap<String, Stock> = HashMap()

  private const val COUNT = 25

  init {
    // Add some sample items.
    for (i in 1..COUNT) {
      addItem(createDummyItem(i))
    }
  }

  private fun addItem(item: Stock) {
    ITEMS.add(item)
    ITEM_MAP[item.symbol] = item
  }

  private fun createDummyItem(position: Int): Stock {
    return Stock(   "APL", makeDetails(position),10,20)
  }

  private fun makeDetails(position: Int): String {
    val builder = StringBuilder()
    builder.append("Details about Item: ").append(position)
    for (i in 0 until position) {
      builder.append("\nMore details information here.")
    }
    return builder.toString()
  }

  data class Stock(val symbol: String, val desc: String, val buy_price: Int, val sell_price: Int) {
    override fun toString(): String = desc
  }
}
