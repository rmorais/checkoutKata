
object CheckoutRunner {
  def main(args: Array[String]) {

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

    println(s"Write the sequence of items you want to buy. do CTRL+C to end session")
    for (ln <- io.Source.stdin.getLines) println(s"The total of the checkout is: ${scanAndTotal(ln)}")
  }
}


class Checkout(pricingRules: Seq[PricingRule]) {

  def calculateTotal(items: List[Char]): Double = {
    pricingRules.foldLeft(0d) { (z, r) =>
      z + r.calculatePrice(items)
    }
  }
}
