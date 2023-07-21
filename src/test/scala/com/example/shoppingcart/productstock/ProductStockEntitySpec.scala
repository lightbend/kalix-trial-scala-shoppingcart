package com.example.shoppingcart.productstock

import com.example.shoppingcart.productstock
import com.google.protobuf.empty.Empty
import kalix.scalasdk.testkit.ValueEntityResult
import kalix.scalasdk.valueentity.ValueEntity
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.util.UUID

class ProductStockEntitySpec
    extends AnyWordSpec
    with Matchers {

  "ProductStockEntity" must {


    "handle command Create" in {
      val productId = UUID.randomUUID.toString
      val quantity = 10
      val testKit = ProductStockEntityTestKit(productId, new ProductStockEntity(_))
      val result = testKit.create(CreateProductStockRequest(productId = productId, quantity = quantity))
      result.isError shouldBe false
      result.reply shouldBe Empty.defaultInstance
      testKit.currentState().quantity shouldBe quantity
    }



  }
}
