class Checkout(pricingRules: Seq[PricingRule]) {

  var items: List[String] = List.empty

  def scan(item: String): Unit = items = item +: items

  def total(): Double = {
    pricingRules.foldLeft(0d) { (z, r) =>
      z + r.calculatePrice(items)
    }
  }
}
