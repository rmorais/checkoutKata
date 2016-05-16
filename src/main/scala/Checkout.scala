class Checkout(pricingRules: Seq[PricingRule]) {

  var items: List[Char] = List.empty

  def scan(item: Char): Unit = items = item +: items

  def total(): Double = {
    pricingRules.foldLeft(0d) { (z, r) =>
      z + r.calculatePrice(items)
    }
  }
}
