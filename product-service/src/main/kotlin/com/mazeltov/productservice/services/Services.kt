package com.mazeltov.productservice.services

import com.mazeltov.common.dto.*
import com.mazeltov.common.exception.*
import com.mazeltov.productservice.models.*
import com.mazeltov.productservice.repositories.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*

@Service
class ProductService {

    @Autowired
    private lateinit var productOperations: ProductOperations

    @Autowired
    private lateinit var productGroupOperations: ProductGroupOperations


    fun getCurrentProduct(
        productGroupId: Long,
        productId: Long
    ): ProductDto = productOperations.findByIdAndProductGroupId(productId, productGroupId)?.toDto()
        ?: throw ProductDoesNotExistException("Product with such id=${productId} doesn't exist")


    fun getAllProductsByProductGroup(
        productGroupId: Long
    ): List<ProductDto> = productOperations.findByProductGroupId(productGroupId).toDto()


    fun addProduct(
        productGroupId: Long,
        productDto: ProductDto
    ): ProductDto = productGroupOperations.findById(productGroupId).takeIf { it.isPresent }?.let {
        productOperations.save(productDto.run {
            Product(
                name = name,
                created = created,
                description = description,
                productGroup = it.get(),
                amount = amount,
                price = price
            )
        })
    }?.toDto()
        ?: throw ProductGroupDoesNotExistException("Product group with such id=${productDto.productGroupId} doesn't exist")

    fun removeProduct(
        productGroupId: Long,
        productId: Long
    ) = productOperations.findByIdAndProductGroupId(productId, productGroupId)
        ?.let { productOperations.delete(it) }


    private fun List<Product>.toDto() = map { it.toDto() }

    private fun Product.toDto() = ProductDto(id, name, amount, price, created, description, productGroup.id, amount > 0)

}

@Service
class ProductGroupService {
    @Autowired
    private lateinit var productGroupOperations: ProductGroupOperations

    @Autowired
    private lateinit var groupVariantOperations: GroupVariantOperations


    fun getCurrentProductGroup(
        groupId: Long,
        groupVariantId: Long
    ): ProductGroupDto = productGroupOperations.findByIdAndGroupVariantId(groupId, groupVariantId)?.toDto()
        ?: throw ProductDoesNotExistException("Product group with such id=${groupId} doesn't exist")


    fun getAllProductGroupsByGroupVariant(
        groupVariantId: Long
    ): List<ProductGroupDto> = productGroupOperations.findByGroupVariantId(groupVariantId).toDto()


    fun addProductGroup(
        groupVariantId: Long,
        productGroupDto: ProductGroupDto
    ): ProductGroupDto = groupVariantOperations.findById(groupVariantId).takeIf { it.isPresent }?.let {
        productGroupOperations.save(productGroupDto.run {
            ProductGroup(
                name = name,
                groupVariants = it.get()
            )
        })
    }?.toDto()
        ?: throw GroupVariantDoesNotExistException("Group variant with such id=${groupVariantId} doesn't exist")

    fun removeProductGroup(
        groupId: Long,
        groupVariantId: Long
    ) = productGroupOperations.findByIdAndGroupVariantId(groupId, groupVariantId)
        ?.let { productGroupOperations.delete(it) }

    private fun List<ProductGroup>.toDto() = map { it.toDto() }

    private fun ProductGroup.toDto() = ProductGroupDto(id, name, groupVariants.id)

}

@Service
class GroupVariantService {

    @Autowired
    private lateinit var groupVariantOperations: GroupVariantOperations

    fun getAllGroupVariant(): List<GroupVariantDto> = groupVariantOperations.findAll().toDto()

    fun getCurrentProductGroup(
        groupVariantId: Long
    ): GroupVariantDto = groupVariantOperations.findById(groupVariantId)
        .orElseThrow { GroupVariantDoesNotExistException("Group variant with such id=${groupVariantId} doesn't exist") }
        .toDto()

    fun addGroupVariant(
        groupVariantDto: GroupVariantDto
    ): GroupVariantDto = groupVariantOperations.save(GroupVariant(name = groupVariantDto.name)).toDto()

    fun removeGroupVariant(groupVariantId: Long) = groupVariantOperations.deleteById(groupVariantId)

    private fun List<GroupVariant>.toDto() = map { it.toDto() }

    private fun GroupVariant.toDto() = GroupVariantDto(id, name)

}

