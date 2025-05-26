import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java")
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
}

repositories {
    mavenCentral()
}

group = "ch.mav.mcve_testcontainers"
version = "1.0-SNAPSHOT"

tasks.getByName<BootJar>("bootJar") {
    enabled = true
}

tasks.getByName<Jar>("jar") {
    enabled = false
}

dependencies {
    implementation("com.giffing.bucket4j.spring.boot.starter:bucket4j-spring-boot-starter:0.12.8")
    implementation("com.github.ben-manes.caffeine:caffeine:2.8.2")
    implementation("com.github.ben-manes.caffeine:jcache:2.8.2")
    implementation("javax.cache:cache-api")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")

    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:postgresql:1.20.0")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testRuntimeOnly("org.liquibase:liquibase-core")
}

tasks.test {
    useJUnitPlatform()
}
