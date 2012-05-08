package dojo.items.house
import dojo.items.TimedItem

class JukeBox(id:Long) extends HouseItem(id) with TimedItem{
	def delay = 5
	def actReady = Some("Blah-Blah")
}
