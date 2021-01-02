package com.mazeltov.recommendationservice.config


import org.springframework.beans.factory.annotation.*
import org.springframework.beans.factory.config.*
import org.springframework.boot.context.properties.*
import org.springframework.boot.jdbc.*
import org.springframework.boot.orm.jpa.*
import org.springframework.context.annotation.*
import org.springframework.data.jpa.repository.config.*
import org.springframework.orm.jpa.*
import org.springframework.transaction.*
import org.springframework.transaction.annotation.*
import javax.persistence.*
import javax.sql.*
import com.mazeltov.common.spring.*

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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "productdbEntityManagerFactory",
        basePackages = ["com.mazeltov.recommendationservice.product"]
)
class ProductDbConfig {
    @Primary
    @Bean(name = [productDb])
    @ConfigurationProperties(prefix = productDbProp)//TODO possible i should change "datasource and productDb"
    fun dataSource(): DataSource = DataSourceBuilder.create().build()
    //TODO Switch to this if doesn't work
    //return DataSourceBuilder.create()
    //          .driverClassName("com.mysql.cj.jdbc.Driver")
    //          .url("jdbc:mysql://localhost:3306/myDb")
    //          .username("user1")
    //          .password("pass")
    //          .build();


    @Primary
    @Bean(name = ["${productDb}EntityManagerFactory"])
    fun entityManagerFactory(
            builder: EntityManagerFactoryBuilder,
            @Qualifier(productDb) dataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean = builder
            .dataSource(dataSource)
            .packages("com.mazeltov.recommendationservice.product.model")
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
        entityManagerFactoryRef = "recommendationDbEntityManagerFactory",
        basePackages = ["com.mazeltov.recommendationservice.recommendation"]
)
class RecommendationDbConfig {

    @Bean(name = [recommendationDb])
    @ConfigurationProperties(prefix = recommendationDbProp)//TODO possible i should change "datasource and recommendationDb"
    fun dataSource(): DataSource = DataSourceBuilder.create().build()


    @Bean(name = ["${recommendationDb}EntityManagerFactory"])
    fun entityManagerFactory(
            builder: EntityManagerFactoryBuilder,
            @Qualifier(recommendationDb) dataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean = builder
            .dataSource(dataSource)
            .packages("com.mazeltov.recommendationservice.recommendation.model")
            .persistenceUnit("recommendation")
            .build()


    @Bean(name = ["${recommendationDb}TransactionManager"])
    fun transactionManager(
            @Qualifier("recommendationdbEntityManagerFactory") entityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager = JpaTransactionManager(entityManagerFactory)

}
