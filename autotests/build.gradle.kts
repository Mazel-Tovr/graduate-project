plugins {
    kotlin("jvm") version "1.4.32"
}

group = "com.mazel.tov"
version = "1.0-SNAPSHOT"

val jUnitVersion = "5.6.2"
val restAssuredVersion = "4.0.0"

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.rest-assured:rest-assured:$restAssuredVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
    testCompile("org.junit.jupiter:junit-jupiter-params:$jUnitVersion")
    testCompile("junit:junit:4.12")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
