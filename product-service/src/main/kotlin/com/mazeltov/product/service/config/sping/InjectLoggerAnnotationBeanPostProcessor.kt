package com.mazeltov.product.service.config.sping

import org.slf4j.*
import org.springframework.beans.factory.config.*
import org.springframework.stereotype.*
import org.springframework.util.*
import kotlin.reflect.jvm.*

@Component
class InjectLoggerAnnotationBeanPostProcessor : BeanPostProcessor {

    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any {
        bean.javaClass.declaredFields
                .filter { field -> field.isAnnotationPresent(InjectLogger::class.java) }
                .forEach { field ->
                    val clazz = field.getAnnotation(InjectLogger::class.java).clazz
                            .let { if (it == Any::class) beanName else it.jvmName }
                    field.isAccessible = true
                    ReflectionUtils.setField(field, bean, LoggerFactory.getLogger(clazz))
                }
        return bean
    }

}
