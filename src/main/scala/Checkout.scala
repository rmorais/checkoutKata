
object CheckoutRunner {
  def main(args: Array[String]) {

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

    println(s"Write the sequence of items you want to buy. do CTRL+C to end session")
    for (ln <- io.Source.stdin.getLines) println(s"The total of the checkout is: ${scanAndTotal(ln)}")
  }
}

class Checkout(pricingRules: Seq[PricingRule]) {

  def calculateTotal(items: Seq[Item]): Double = {
    pricingRules.foldLeft(0d) { (z, r) =>
      z + r.calculatePrice(items)
    }
  }
}
