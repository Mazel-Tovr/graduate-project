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
    fun getAllProductsByProductGroup(@PathVariable(value = "groupId") groupId: Long) {

    }

}
