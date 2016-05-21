
object CheckoutRunner {
  def main(args: Array[String]) {

    val promo1 = Promotion('A', 3, 130d)
    val promo2 = Promotion('B', 2, 45d)

    val rules = Seq(
      PricingRule('A', 50),
      PricingRule('B', 30),
      PricingRule('C', 20),
      PricingRule('D', 15)
    )

    def scanAndTotal(items: String) = {
      val checkout = new Checkout(rules, Seq(promo1, promo2))
      items foreach checkout.scan
      checkout.total()
    }

    println(s"Write the sequence of items you want to buy. do CTRL+C to end session")
    for (ln <- io.Source.stdin.getLines) println(s"The total of the checkout is: ${scanAndTotal(ln)}")
  }
}


class Checkout(pricingRules: Seq[PricingRule], promotions: Seq[Promotion]) {

  private var items: Map[Char, Int] = Map.empty

  def scan(item: Char): Unit = {
    val count = items getOrElse (item, 0)
    items = items + (item -> (count + 1 ))
  }

  def total(): Double = {

    items.foldLeft(0d) {case (acc, (item, count)) =>
      val rule = pricingRules.find(_.item == item) getOrElse PricingRule(item, 0)
      val promo = promotions.find(_.item == item)
        acc + calculatePrice(item, count, rule, promo)
    }
  }

  def calculatePrice(item: Char, qty: Int, rule: PricingRule, promotion: Option[Promotion]): Double = {

    val promotionalPackPrice = promotion map (p => qty / p.numberOfItems * p.price) getOrElse 0d
    val regularItems = promotion.map(qty % _.numberOfItems) getOrElse qty
    regularItems * rule.price + promotionalPackPrice
  }
}


case class PricingRule(item: Char, price: Double)
case class Promotion(item: Char, numberOfItems: Int, price: Double)