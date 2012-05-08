package dojo.items.house
import dojo.items.Purchasable

class Chair(id:Long) extends HouseItem(id) with Purchasable{
	def price = 3
}
