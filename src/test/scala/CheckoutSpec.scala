import org.specs2.mutable.Specification

class CheckoutSpec extends Specification {

  "Checkout" should {
    "calculate the total given no products are scanned" in {
      val checkout = new Checkout(Seq())
      checkout.total() === 0
    }

    "calculate the total given a single product is scanned" in {
      val rules = Seq(
        PricingRule("A", 50)
      )
      val checkout = new Checkout(rules)
      checkout.scan("A")
      checkout.total() === 50
    }

    "calculate the total given a single product is scanned multiple times" in {
      val rules = Seq(
        PricingRule("A", 50)
      )
      val checkout = new Checkout(rules)
      checkout.scan("A")
      checkout.scan("A")
      checkout.total() === 100
    }

    "calculate the total given multiple products are scanned a single time" in {
      val rules = Seq(
        PricingRule("A", 50),
        PricingRule("B", 30),
        PricingRule("C", 20),
        PricingRule("D", 15)
      )
      val checkout = new Checkout(rules)
      checkout.scan("A")
      checkout.scan("B")
      checkout.scan("C")
      checkout.scan("D")
      checkout.total() === 115
    }

    "calculate the total given a single product have a special price" in {
      val rules = Seq(
        PricingRule("A", 50, (3, 130)),
        PricingRule("B", 30, (2, 45)),
        PricingRule("C", 20),
        PricingRule("D", 15)
      )
      val checkout = new Checkout(rules)
      checkout.scan("A")
      checkout.scan("A")
      checkout.scan("A")
      checkout.scan("A")
      checkout.total() === 180
    }

    "calculate the total given multiple products have special prices" in {
      val rules = Seq(
        PricingRule("A", 50, (3, 130)),
        PricingRule("B", 30, (2, 45)),
        PricingRule("C", 20),
        PricingRule("D", 15)
      )
      val checkout = new Checkout(rules)
      checkout.scan("A")
      checkout.scan("A")
      checkout.scan("A")
      checkout.scan("A")
      checkout.scan("B")
      checkout.scan("B")
      checkout.scan("B")

      checkout.total() === 255
    }
  }
}

