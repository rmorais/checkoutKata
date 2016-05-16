
case class PricingRule(item: Item, price: Double, promotion: (Int, Double)) {

  def calculatePrice(items: Seq[Item]): Double = {

    val (itemsInPromotion, promotionalPrice) = promotion

    val numberOfItems = items.count(_ equals item)
    val promotionalPacks = numberOfItems / itemsInPromotion
    val promotionalPackPrice = promotionalPacks * promotionalPrice
    val regularItems = numberOfItems % itemsInPromotion
    regularItems * price + promotionalPackPrice
  }
}

object PricingRule {
  def apply(item: Item, price: Double): PricingRule = new PricingRule(item, price, (1, price))
}


case class Item(value: Char) extends AnyVal
