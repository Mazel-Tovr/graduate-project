plugins {
    kotlin("jvm")
    id("org.jetbrains.dokka")
}

repositories {
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
    mavenCentral()
    jcenter()
}

apply(plugin= "org.jetbrains.dokka")

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("org.springframework:spring-core:5.1.12.RELEASE")
    compileOnly("org.springframework:spring-web:5.1.12.RELEASE")
    compileOnly("org.springframework.security:spring-security-core:5.1.12.RELEASE")
    compileOnly("org.springframework:spring-beans:5.2.12.RELEASE")
    compileOnly("org.springframework:spring-context:5.2.12.RELEASE")
    compileOnly("org.springframework.boot:spring-boot:2.3.7.BUILD-SNAPSHOT")
    compileOnly("org.apache.tomcat.embed:tomcat-embed-core:9.0.41")
    implementation("io.jsonwebtoken:jjwt:0.9.0")
    compileOnly("org.slf4j:slf4j-api:1.7.30")
    compileOnly("io.springfox:springfox-swagger2:3.0.0")
    compileOnly("io.springfox:springfox-swagger-ui:2.9.2")
}
