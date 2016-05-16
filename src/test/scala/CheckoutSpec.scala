import org.specs2.mutable.Specification

class CheckoutSpec extends Specification {

  "Checkout" should {
    "calculate the total given no products are scanned" in {
      val checkout = new Checkout()
      val total = checkout.total()
      total === 0
    }
  }
}

