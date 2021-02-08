package com.mazeltov.productservice.endpoints

import com.mazeltov.common.dto.*
import com.mazeltov.common.response.ResponseBody
import com.mazeltov.common.spring.*
import com.mazeltov.productservice.services.*
import org.slf4j.*
import org.springframework.beans.factory.annotation.*
import org.springframework.http.*
import org.springframework.web.bind.annotation.*

@RestController
class ProductController {

    @Autowired
    private lateinit var productService: ProductService

    @InjectLogger(ProductController::class)
    private lateinit var logger: Logger

    @GetMapping("\${api.products.rout}")
    fun getAllProductsByProductGroup(
        @PathVariable(value = "groupId") groupId: Long
    ) = productService.getAllProductsByProductGroup(groupId).wrapToResponseEntity()

    @GetMapping("\${api.products.current.rout}")
    fun getCurrentProductByProductGroup(
        @PathVariable(value = "groupId") groupId: Long,
        @PathVariable(value = "id") productId: Long
    ): ResponseEntity<Any> = try {
        productService.getCurrentProduct(groupId, productId).wrapToResponseEntity()
    } catch (e: Exception) {
        e.message?.wrapToResponseEntity(HttpStatus.BAD_REQUEST) ?: ResponseBody.RESOURCE_NOT_FOUND("Product")
            .wrapToResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @PostMapping("\${api.products.rout}")
    fun addProduct(
        @PathVariable(value = "groupId") groupId: Long,
        productDto: ProductDto
    ) = try {
        productService.addProduct(groupId, productDto)
    } catch (e: Exception) {
        e.message?.wrapToResponseEntity(HttpStatus.BAD_REQUEST) ?: ResponseBody.RESOURCE_NOT_FOUND("Product")
            .wrapToResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @DeleteMapping("\${api.products.current.rout}")
    fun deleteProduct(
        @PathVariable(value = "groupId") groupId: Long,
        @PathVariable(value = "id") productId: Long
    ) = productService.removeProduct(groupId, productId).let {
        "Product deleted".wrapToResponseEntity()
    }
}

@RestController
class ProductGroupController {
    @Autowired
    private lateinit var productGroupService: ProductGroupService

    @InjectLogger(ProductController::class)
    private lateinit var logger: Logger

    @GetMapping("\${api.groups.rout}")
    fun getAllProductGroupsByGroupVariant(
        @PathVariable(value = "groupVariantId") groupVariantId: Long
    ) = productGroupService.getAllProductGroupsByGroupVariant(groupVariantId).wrapToResponseEntity()

    @GetMapping("\${api.groups.current.rout}")
    fun getCurrentProductGroup(
        @PathVariable(value = "groupVariantId") groupVariantId: Long,
        @PathVariable(value = "id") groupId: Long
    ): ResponseEntity<Any> = try {
        productGroupService.getCurrentProductGroup(groupVariantId, groupId).wrapToResponseEntity()
    } catch (e: Exception) {
        e.message?.wrapToResponseEntity(HttpStatus.BAD_REQUEST) ?: ResponseBody.RESOURCE_NOT_FOUND("Product Group")
            .wrapToResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @PostMapping("\${api.groups.rout}")
    fun addProductGroup(
        @PathVariable(value = "groupVariantId") groupVariantId: Long,
        productGroupDto: ProductGroupDto
    ) = try {
        productGroupService.addProductGroup(groupVariantId, productGroupDto)
    } catch (e: Exception) {
        e.message?.wrapToResponseEntity(HttpStatus.BAD_REQUEST) ?: ResponseBody.RESOURCE_NOT_FOUND("Product Group")
            .wrapToResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @DeleteMapping("\${api.groups.current.rout}")
    fun deleteProductGroup(
        @PathVariable(value = "groupVariantId") groupVariantId: Long,
        @PathVariable(value = "id") groupId: Long
    ) = productGroupService.removeProductGroup(groupVariantId, groupId).let {
        "Product Group deleted".wrapToResponseEntity()
    }
}


fun Any.wrapToResponseEntity(httpStatus: HttpStatus = HttpStatus.OK) = ResponseEntity<Any>(this, httpStatus)

