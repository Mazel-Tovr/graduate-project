package com.mazeltov.common.response

object ResponseBody {

    fun RESOURCE_NOT_FOUND(resourceName:String) = "{\"Exception\":\"$resourceName not found\" }"

}
