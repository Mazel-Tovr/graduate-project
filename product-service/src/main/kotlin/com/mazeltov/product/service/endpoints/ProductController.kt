package com.mazeltov.product.service.endpoints

import org.springframework.stereotype.*
import org.springframework.web.bind.annotation.*

@Controller
class ProductController {

    @GetMapping("\${api.products}")
    fun getAll(){

    }

}
