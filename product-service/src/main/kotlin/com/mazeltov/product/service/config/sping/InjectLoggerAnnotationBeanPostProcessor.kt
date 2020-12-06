package com.mazeltov.product.service.config.sping

import org.slf4j.*
import org.springframework.beans.factory.config.*
import org.springframework.util.*

class InjectLoggerAnnotationBeanPostProcessor : BeanPostProcessor {

    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any {
        bean.javaClass.fields
                .filter { field -> field.isAnnotationPresent(InjectLogger::class.java) }
                .forEach { field ->
                    val clazz = field.getAnnotation(InjectLogger::class.java).clazz
                    field.isAccessible = true
                    ReflectionUtils.setField(field, bean, LoggerFactory.getLogger(clazz::class.java))
                }
        return bean
    }

}
