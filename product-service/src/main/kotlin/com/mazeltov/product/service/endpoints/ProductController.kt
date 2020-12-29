package com.mazeltov.product.service.endpoints

import com.mazeltov.product.service.config.sping.*
import com.mazeltov.product.service.models.*
import com.mazeltov.product.service.repositories.*
import org.slf4j.*
import org.springframework.beans.factory.annotation.*
import org.springframework.web.bind.annotation.*

@RestController
class ProductController {

    @Autowired
    private lateinit var productOperations: ProductOperations

    @InjectLogger(ProductController::class)
    private lateinit var logger: Logger

    @GetMapping("\${api.products.rout}")
    fun getAll(): List<Product> {
        logger.info("I am here")
        productOperations.save(Product(name = "product", group = ProductGroup(name = "Group", groupVariants = emptyList())))

        return productOperations.findAll()
    }

}
