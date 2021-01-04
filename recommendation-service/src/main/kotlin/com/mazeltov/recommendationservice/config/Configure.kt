package com.mazeltov.recommendationservice.config


import com.mazeltov.common.spring.*
import org.springframework.beans.factory.annotation.*
import org.springframework.beans.factory.config.*
import org.springframework.boot.context.properties.*
import org.springframework.boot.jdbc.*
import org.springframework.boot.orm.jpa.*
import org.springframework.context.annotation.*
import org.springframework.core.io.*
import org.springframework.core.io.support.*
import org.springframework.data.jpa.repository.config.*
import org.springframework.orm.jpa.*
import org.springframework.transaction.*
import org.springframework.transaction.annotation.*
import java.io.*
import java.util.*
import java.util.function.*
import java.util.stream.*
import javax.persistence.*
import javax.sql.*


@Configuration
@PropertySources(value = [
    PropertySource("classpath:routs.yml")]
)
class Configure {

    @Bean
    fun injectLoggerAnnotationBeanPostProcessor(): BeanPostProcessor {
        return InjectLoggerAnnotationBeanPostProcessor()
    }

}

private const val productDb = "productdb"
private const val productDbProp = "spring.$productDb.datasource"
private const val recommendationDb = "recommendationdb"
private const val recommendationDbProp = "spring.$recommendationDb.datasource"

private val hibernateProperties = mapOf(
        "hibernate.dialect" to "org.hibernate.dialect.MySQL57Dialect",
        "hibernate.hbm2ddl.auto" to "update",
        "hibernate.show-sql" to "true"
)

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "${productDb}EntityManagerFactory",
        transactionManagerRef = "${productDb}TransactionManager",
        basePackages = ["com.mazeltov.recommendationservice.dao.product.repository"]
)
class ProductDbConfig {
    @Primary
    @Bean(name = [productDb])
    @ConfigurationProperties(prefix = productDbProp)//TODO possible i should change "datasource and productDb"
    fun dataSource(
            @Value("\${db1.host}") host: String,
            @Value("\${db1.port}") port: String,
            @Value("\${db1.name}") name: String
    ): DataSource = DataSourceBuilder.create()
            .driverClassName("com.mysql.cj.jdbc.Driver")
            .url("jdbc:mysql://$host:$port/$name?useUnicode=true&serverTimezone=UTC")
            .username("root")
            .password("root")
            .build();

    @Primary
    @Bean(name = ["${productDb}EntityManagerFactory"])
    fun entityManagerFactory(
            builder: EntityManagerFactoryBuilder,
            @Qualifier(productDb) dataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean = builder
            .dataSource(dataSource)
            .properties(hibernateProperties)
            .packages("com.mazeltov.recommendationservice.dao.product.model")
            .persistenceUnit("product")
            .build()


    @Primary
    @Bean(name = ["${productDb}TransactionManager"])
    fun transactionManager(
            @Qualifier("${productDb}EntityManagerFactory") entityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager = JpaTransactionManager(entityManagerFactory)

}

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "${recommendationDb}EntityManagerFactory",
        transactionManagerRef = "${recommendationDb}TransactionManager",
        basePackages = ["com.mazeltov.recommendationservice.dao.recommendation.repository"]
)
class RecommendationDbConfig {

    @Bean(name = [recommendationDb])
    @ConfigurationProperties(prefix = recommendationDbProp)
    fun dataSource(
            @Value("\${db2.host}") host: String,
            @Value("\${db2.port}") port: String,
            @Value("\${db2.name}") name: String
    ): DataSource = DataSourceBuilder.create()
            .driverClassName("com.mysql.cj.jdbc.Driver")
            .url("jdbc:mysql://$host:$port/$name?useUnicode=true&serverTimezone=UTC")
            .username("root")
            .password("root")
            .build();// DataSourceBuilder.create().build()


    @Bean(name = ["${recommendationDb}EntityManagerFactory"])
    fun entityManagerFactory(
            builder: EntityManagerFactoryBuilder,
            @Qualifier(recommendationDb) dataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean = builder
            .dataSource(dataSource)
            .properties(hibernateProperties)
            .packages("com.mazeltov.recommendationservice.dao.recommendation.model")
            .persistenceUnit("recommendation")
            .build()


    @Bean(name = ["${recommendationDb}TransactionManager"])
    fun transactionManager(
            @Qualifier("${recommendationDb}EntityManagerFactory") entityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager = JpaTransactionManager(entityManagerFactory)

}

