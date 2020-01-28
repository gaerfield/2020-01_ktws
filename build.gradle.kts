import com.bulwinkel.gitversion.GitVersion

plugins {
    kotlin("jvm") version "1.3.61"
    kotlin("kapt") version "1.3.61"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.3.61"

    id("org.springframework.boot") version "2.2.1.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    kotlin("plugin.spring") version "1.3.60"

    id("com.bulwinkel.gradle.git-version") version "0.2.1"
}

val gitVersion: GitVersion by extra
group = "de.esag.ktws"
version = gitVersion.name
java.sourceCompatibility = JavaVersion.VERSION_1_8

val developmentOnly : Configuration by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.github.microutils:kotlin-logging:1.7.6")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    // spring-boot
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.plugin:spring-plugin-core:1.2.0.RELEASE")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        exclude(module = "mockito-core")
    }
    testImplementation("com.ninja-squad:springmockk:2.0.0")

    // swagger-api
    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")

    // data
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.hibernate:hibernate-validator:6.0.17.Final")
    runtimeOnly("com.h2database:h2")

}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

springBoot {
    buildInfo()
}

