package com.example.shoppingcart.productstock

import akka.actor.ActorSystem
import com.google.protobuf.empty.Empty
import kalix.scalasdk.testkit.KalixTestKit
import org.scalatest.BeforeAndAfterAll
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.should.Matchers
import org.scalatest.time.Millis
import org.scalatest.time.Seconds
import org.scalatest.time.Span
import org.scalatest.wordspec.AnyWordSpec

import java.util.UUID

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

class ProductStockServiceIntegrationSpec
    extends AnyWordSpec
    with Matchers
    with BeforeAndAfterAll
    with ScalaFutures {

  implicit private val patience: PatienceConfig =
    PatienceConfig(Span(5, Seconds), Span(500, Millis))

  private val testKit = KalixTestKit(Main.createKalix()).start()

  private val client = testKit.getGrpcClient(classOf[ProductStockService])

  "ProductStockService" must {

    "have example test that can be removed" in {
      val productId = UUID.randomUUID.toString
      val quantity = 10
      val createResult = client.create(CreateProductStockRequest(productId = productId, quantity = quantity))
      createResult.futureValue
      val getResult = client.get(GetProductStock(productId = productId))
      getResult.futureValue.quantity shouldBe quantity
    }

  }

  override def afterAll(): Unit = {
    testKit.stop()
    super.afterAll()
  }
}
