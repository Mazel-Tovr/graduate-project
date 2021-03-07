package com.mazeltov.common

import com.mazeltov.common.security.*
import org.springframework.web.context.request.*

fun getActualRequest() = ((RequestContextHolder.getRequestAttributes()) as ServletRequestAttributes).request

fun getUserIdFromRequest(header: String, secret: String) = getToken(getActualRequest(), header)?.getUserNameFromToken(secret)

