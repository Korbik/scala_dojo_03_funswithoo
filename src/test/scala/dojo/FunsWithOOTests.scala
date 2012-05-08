package dojo

import items.artifacts.Unicorn
import items.{Item, Purchasable, TimedItem, User}
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import dojo.items.fashion.Hat
import dojo.items.house.Chair
import dojo.items.artifacts.MachineGunUnicorn
import dojo.items.house.JukeBox

@RunWith(classOf[JUnitRunner])
class FunsWithOOTests extends FunSuite with ShouldMatchers{

  // all items should have an id
  test("Unicorn should have id of 1"){
    assert((new Unicorn(1).id == 1))
  }

  test("anonymous items should have an id"){
    class AnonymousItem(id:Long) extends Item(id)
    val anon = new AnonymousItem(2)
    assert(anon.id == 2)
  }

  // all fashion and house items should be able to be bought for cash deducted from user

  test("should buy hat and deduct 5 cash from user"){
    //assert(false)
    val hat = new Hat(3)
    assert(assertPurchasableItemCashDeduction(hat) == 5)
  }

  test("should buy chair and deduct 3 cash from user"){
    val chair = new Chair(4)
    assert( assertPurchasableItemCashDeduction(chair) == 7)
  }

  def assertPurchasableItemCashDeduction(item:Purchasable) = {
    val balance = 10
    val user = new User(balance)
    user.buy(item)
    // result needs to be asserted
    user.cash
  }

  // MachineGunUnicorn and JukeBox have special actions that are available a set time after creation
  // * MachineGunUnicorn prints Bam-Bam
  // * JukeBox prints Blah-Blah

  test("MachineGunUnicorn goes Bam-Bam"){
    val machineGunUnicorn = new MachineGunUnicorn(5)
    val delay = 2
    assertTimedItem(Some("Bam-Bam"), machineGunUnicorn, delay)
  }

  test("JukeBox goes Blah-Blah"){
    val jukeBox = new JukeBox(5)
    val delay = 5
    assertTimedItem(Some("Blah-Blah"), jukeBox, delay)
  }

  def assertTimedItem(expected: Some[Any], timedItem: TimedItem, delay: Int){
    val now = 10
    timedItem.startClock(now)

    timedItem.ready(now)          should  equal (false)
    timedItem.act(now + delay -1) should  equal (None)

    timedItem.ready(now + delay)  should  equal (true)
    timedItem.act(now + delay)    should  equal (expected)
  }

}
