plugins {
    kotlin("jvm")
}

repositories {
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("org.springframework:spring-core:5.1.12.RELEASE")
    compileOnly("org.springframework.security:spring-security-core:5.1.12.RELEASE")
    compileOnly("org.springframework:spring-beans:5.2.12.RELEASE")
    compileOnly("org.springframework:spring-context:5.2.12.RELEASE")
    compileOnly("org.springframework.boot:spring-boot:2.3.7.BUILD-SNAPSHOT")
    compileOnly("org.slf4j:slf4j-api:1.7.30")
}
