
case class PricingRule(item: String, price: Double, promotion: (Int, Double)) {

  def calculatePrice(items: List[String]): Double = {

    val (itemsInPromotion, promotionalPrice) = promotion

    val numberOfItems = items.count(_ == item)
    val promotionalPacks = numberOfItems % itemsInPromotion
    val promotionalPackPrice = promotionalPacks * promotionalPrice
    val regularItems = numberOfItems - promotionalPacks * itemsInPromotion
    regularItems * price + promotionalPackPrice
  }
}

object PricingRule {
  def apply(item: String, price: Double): PricingRule = new PricingRule(item, price, (1, price))
}
