package com.example.shoppingcart.productstock

import com.example.shoppingcart.productstock
import com.google.protobuf.empty.Empty
import io.grpc.Status
import kalix.scalasdk.valueentity.ValueEntity
import kalix.scalasdk.valueentity.ValueEntityContext

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

class ProductStockEntity(context: ValueEntityContext) extends AbstractProductStockEntity {
  override def emptyState: ProductStock = ProductStock.defaultInstance

  override def create(currentState: ProductStock, createProductStockRequest: CreateProductStockRequest): ValueEntity.Effect[Empty] =
    if (currentState == ProductStock.defaultInstance)
      effects.updateState(ProductStock.of(createProductStockRequest.quantity)).thenReply(Empty.defaultInstance)
    else
      effects.error("Already created")

  override def get(currentState: ProductStock, getProductStock: GetProductStock): ValueEntity.Effect[ProductStock] =
    if (currentState == ProductStock.defaultInstance)
      effects.error("Not found", Status.Code.NOT_FOUND)
    else
      effects.reply(currentState)

  override def update(currentState: ProductStock, updateProductStockRequest: UpdateProductStockRequest): ValueEntity.Effect[Empty] =
    if (currentState == ProductStock.defaultInstance)
      effects.error("Not found", Status.Code.NOT_FOUND)
    else
      effects.updateState(currentState.withQuantity(updateProductStockRequest.quantity)).thenReply(Empty.defaultInstance)

  override def delete(currentState: ProductStock, deleteProductStockRequest: DeleteProductStockRequest): ValueEntity.Effect[Empty] =
    if (currentState == ProductStock.defaultInstance)
      effects.error("Not found", Status.Code.NOT_FOUND)
    else
      effects.updateState(ProductStock.defaultInstance).thenReply(Empty.defaultInstance)

}

