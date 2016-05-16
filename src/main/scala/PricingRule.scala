
case class PricingRule(item: Char, price: Double, promotion: (Int, Double)) {

  def calculatePrice(items: List[Char]): Double = {

    val (itemsInPromotion, promotionalPrice) = promotion

    val numberOfItems = items.count(_ == item)
    val promotionalPacks = numberOfItems / itemsInPromotion
    val promotionalPackPrice = promotionalPacks * promotionalPrice
    val regularItems = numberOfItems % itemsInPromotion
    regularItems * price + promotionalPackPrice
  }
}

object PricingRule {
  def apply(item: Char, price: Double): PricingRule = new PricingRule(item, price, (1, price))
}
