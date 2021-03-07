package com.mazeltov.productservice.endpoints

import com.mazeltov.common.*
import com.mazeltov.common.dto.*
import com.mazeltov.common.response.ResponseBody
import com.mazeltov.common.spring.*
import com.mazeltov.productservice.feignclients.*
import com.mazeltov.productservice.services.*
import kotlinx.coroutines.*
import org.slf4j.*
import org.springframework.beans.factory.annotation.*
import org.springframework.http.*
import org.springframework.web.bind.annotation.*

@RestController
class ProductController {

    @Autowired
    private lateinit var productService: ProductService

    @Autowired
    private lateinit var recommendationServiceFeignClient: RecommendationServiceFeignClient

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Value("\${jwt.header}")
    private lateinit var header: String

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
    ): ResponseEntity<Any> {
        val response = mutableMapOf<String, Any?>()

        val userName = getUserIdFromRequest(header, secret)
        val visited = GlobalScope.launch {
            recommendationServiceFeignClient.userViewedProduct(VisitDto(userName, productId));
        }
        val recommendations = GlobalScope.async {
            recommendationServiceFeignClient.getRecommendations(userName);
        }

        response["Product"] = runCatching {
            productService.getCurrentProduct(groupId, productId)
        }.getOrElse {
           it.message
        }
        response["recommends"] = runBlocking { visited.join(); recommendations.await() }
        return response.wrapToResponseEntity()
    }

    @PostMapping("\${api.products.rout}")
    fun addProduct(
        @PathVariable(value = "groupId") groupId: Long,
        @RequestBody productDto: ProductDto
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

    @InjectLogger(ProductGroupController::class)
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
        @RequestBody productGroupDto: ProductGroupDto
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

@RestController
class GroupVariantController {
    @Autowired
    private lateinit var groupVariantService: GroupVariantService

    @InjectLogger(GroupVariantController::class)
    private lateinit var logger: Logger

    @GetMapping("\${api.group-variants.rout}")
    fun getAllGroupVariant() = groupVariantService.getAllGroupVariant()

    @GetMapping("\${api.group-variants.current.rout}")
    fun getCurrentGroupVariant(
        @PathVariable(value = "id") groupVariantId: Long
    ) = groupVariantService.getCurrentProductGroup(groupVariantId)

    @PostMapping("\${api.group-variants.rout}")
    fun addGroupVariant(
        @RequestBody groupVariantDto: GroupVariantDto
    ) = groupVariantService.addGroupVariant(groupVariantDto)

    @DeleteMapping("\${api.group-variants.current.rout}")
    fun removeGroupVariant(
        @PathVariable(value = "id") groupVariantId: Long
    ) = groupVariantService.removeGroupVariant(groupVariantId)

}

@RestController
class ReviewController {

    @Autowired
    private lateinit var reviewServiceFeignClient: ReviewServiceFeignClient

    @InjectLogger(ReviewController::class)
    private lateinit var logger: Logger

    @GetMapping("\${api.review-services.rout}")
    fun getAllReviews(
        @PathVariable(value = "productId") productId: Long
    ): List<ReviewDto> = reviewServiceFeignClient.getAllReviews(productId)

    @GetMapping("\${api.review-services.current.rout}")
    fun getReviewById(
        @PathVariable(value = "productId") productId: Long,
        @PathVariable(value = "id") reviewId: Long
    ): ResponseEntity<Any> = reviewServiceFeignClient.getReviewById(productId, reviewId)

    @GetMapping("\${api.review-services.between.rout}")
    fun getReviewsBetween(
        @PathVariable(value = "productId") productId: Long,
        @RequestParam(value = "start") start: Int,
        @RequestParam(value = "finish") finish: Int
    ): List<ReviewDto> =
        reviewServiceFeignClient.getReviewsBetween(productId, start, finish)

    @PostMapping("\${api.review-services.rout}")
    fun addReview(
        @PathVariable(value = "productId") productId: Long,
        @RequestBody review: ReviewDto
    ): ResponseEntity<Any> = reviewServiceFeignClient.addReview(productId, review)

    @PatchMapping("\${api.review-services.current.rout}")
    fun editReview(
        @PathVariable(value = "productId") productId: Long,
        @RequestBody review: ReviewDto
    ): ResponseEntity<Any> = reviewServiceFeignClient.editReview(productId, review)

    @DeleteMapping("\${api.review-services.current.rout}")
    fun deleteReview(
        @PathVariable(value = "productId") productId: Long,
        @PathVariable(value = "id") reviewId: Long
    ): ResponseEntity<Any> = reviewServiceFeignClient.deleteReview(productId, reviewId)


}


internal fun Any.wrapToResponseEntity(httpStatus: HttpStatus = HttpStatus.OK) = ResponseEntity<Any>(this, httpStatus)

