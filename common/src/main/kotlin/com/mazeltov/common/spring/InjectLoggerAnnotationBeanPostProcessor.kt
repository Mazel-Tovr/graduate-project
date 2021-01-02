package com.mazeltov.common.spring


import org.slf4j.*
import org.springframework.beans.factory.config.*
import org.springframework.util.*


class InjectLoggerAnnotationBeanPostProcessor : BeanPostProcessor {

    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any {
        bean.javaClass.declaredFields
                .filter { field -> field.isAnnotationPresent(InjectLogger::class.java) }
                .forEach { field ->
                    val clazz = field.getAnnotation(InjectLogger::class.java).clazz
                            .let { if (it == Any::class) bean.javaClass.name else it.java.name }
                    field.isAccessible = true
                    ReflectionUtils.setField(field, bean, LoggerFactory.getLogger(clazz))
                }
        return bean
    }

}
