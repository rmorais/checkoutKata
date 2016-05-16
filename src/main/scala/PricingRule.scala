
case class PricingRule(item: String, price: Double) {
  def calculatePrice(items: List[String]): Double = {
    val numberOfItems = items.count(_ == item)
    numberOfItems * price
  }
}
