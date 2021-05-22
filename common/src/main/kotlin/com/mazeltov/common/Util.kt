package com.mazeltov.common

import com.mazeltov.common.security.*
import org.springframework.http.*
import org.springframework.web.context.request.*
import javax.servlet.http.*

fun getActualRequest(): HttpServletRequest =
    ((RequestContextHolder.getRequestAttributes()) as ServletRequestAttributes).request

fun getUserIdFromRequest(header: String, secret: String) =
    getToken(getActualRequest(), header)?.getUserNameFromToken(secret)

fun getUserRoleFromRequest(header: String, secret: String) =
    getToken(getActualRequest(), header)?.getRoleFromToken(secret)

fun Any.toResponseEntity(httpStatus: HttpStatus = HttpStatus.OK) = ResponseEntity(this, httpStatus)
