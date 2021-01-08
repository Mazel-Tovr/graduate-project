package com.mazeltov.productservice.endpoints

import com.mazeltov.common.spring.*
import com.mazeltov.productservice.models.*
import com.mazeltov.productservice.repositories.*
import org.slf4j.*
import org.springframework.beans.factory.annotation.*
import org.springframework.web.bind.annotation.*

@RestController
class ProductController {

    @Autowired
    private lateinit var productOperations: ProductOperations

    @Autowired
    private lateinit var productGroupOperations: ProductGroupOperations

    @Autowired
    private lateinit var groupVariant: GroupVariantOperations

    @InjectLogger(ProductController::class)
    private lateinit var logger: Logger

    @GetMapping("\${api.products.rout}")
    fun getAll(): List<Product> {
//        logger.info("I am here")
//        val technique = groupVariant.save(GroupVariant(name = "technique"))
//        val phone = productGroupOperations.save(ProductGroup(name = "Phone", groupVariants = technique))
//        productOperations.save(Product(name = "product", productGroup = phone, amount = 0, price = 200))
        return productOperations.findAll()
    }

}
