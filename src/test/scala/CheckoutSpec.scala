import org.specs2.mutable.Specification

class CheckoutSpec extends Specification {

  val rules = Seq(
    PricingRule('A', 50, (3, 130)),
    PricingRule('B', 30, (2, 45)),
    PricingRule('C', 20),
    PricingRule('D', 15)
  )

  def scanAndTotal(items: String) = {
    val checkout = new Checkout(rules)
    checkout.calculateTotal(items.toList)
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

