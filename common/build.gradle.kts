plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("org.springframework:spring-core:5.1.12.RELEASE")
    compileOnly("org.springframework:spring-beans:5.2.12.RELEASE")
    compileOnly("org.slf4j:slf4j-api:1.7.30")
}
