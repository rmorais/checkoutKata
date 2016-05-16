import org.specs2.mutable.Specification

class CheckoutSpec extends Specification {

  val itemA = Item('A')
  val itemB = Item('B')
  val itemC = Item('C')
  val itemD = Item('D')

  val rules = Seq(
    PricingRule(itemA, 50, (3, 130)),
    PricingRule(itemB, 30, (2, 45)),
    PricingRule(itemC, 20),
    PricingRule(itemD, 15)
  )

  def scanAndTotal(items: String) = {
    val checkout = new Checkout(rules)
    val itemsList = items.toList map Item
    checkout.calculateTotal(itemsList)
  }

  "Checkout" should {
    "calculate the total given no products are scanned" in {
      scanAndTotal("") === 0
    }

    "calculate the total given a single product is scanned" in {
      scanAndTotal("A") === 50
    }

    "calculate the total given a single product is scanned multiple times" in {
      scanAndTotal("AA") === 100
    }

    "calculate the total given multiple products are scanned a single time" in {
      scanAndTotal("ABCD") === 115
    }

    "calculate the total given a single product have a special price" in {
      scanAndTotal("AAAA") === 180
    }

    "calculate the total given multiple products have special prices" in {
      scanAndTotal("ABAACAABAAB") === 405
    }
  }
}

