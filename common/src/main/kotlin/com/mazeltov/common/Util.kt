package com.mazeltov.common

import com.mazeltov.common.security.*
import org.springframework.http.*
import org.springframework.web.context.request.*
import javax.servlet.http.*

/**
 * Утилитный метод для получение текущего запроса
 */
fun getActualRequest(): HttpServletRequest =
    ((RequestContextHolder.getRequestAttributes()) as ServletRequestAttributes).request
/**
 * Утилитный метод для получение имени юзера из запроса
 */
fun getUserIdFromRequest(header: String, secret: String) =
    getToken(getActualRequest(), header)?.getUserNameFromToken(secret)

fun getUserRoleFromRequest(header: String, secret: String) =
    getToken(getActualRequest(), header)?.getRoleFromToken(secret)

/**
 * Утилитный метод для трансформации любого объекта к @see ResponseEntity
 */
fun Any.toResponseEntity(httpStatus: HttpStatus = HttpStatus.OK) = ResponseEntity(this, httpStatus)
