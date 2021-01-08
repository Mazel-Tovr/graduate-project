package com.mazeltov.productservice.services

import com.mazeltov.common.dto.*
import com.mazeltov.productservice.models.*
import com.mazeltov.productservice.repositories.*
import org.springframework.beans.factory.annotation.*
import org.springframework.stereotype.*

@Service
class ProductService {

    @Autowired
    private lateinit var productOperations: ProductOperations


    fun getProduct(productId: String, productGroupId: String): Product {
        TODO()
    }

    fun getAllProduct(productGroupId: String): List<Product> {
        TODO()
    }

    fun addProduct(productDto: ProductDto): Product {
        TODO()
    }

}

@Service
class ProductGroupService {
    @Autowired
    private lateinit var productGroupOperations: ProductGroupOperations

    fun getAllProductGroup(): List<ProductGroup> {
        return productGroupOperations.findAll()
    }
}

@Service
class ProductGroupVariantService {
    @Autowired
    private lateinit var groupVariantOperations: GroupVariantOperations

    fun getAllGroupVariant(): List<GroupVariant> {
        return groupVariantOperations.findAll()
    }
}

