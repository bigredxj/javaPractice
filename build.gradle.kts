plugins {
    id("java")
    //id("org.apache.avro:avro-maven-plugin") version "1.12.0"
   // id("com.github.davidmc24.gradle.plugin.avro") version("0.3.3")
}

group = "org.example"
version = "1.0-SNAPSHOT"


dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.10.0")

    implementation("io.netty:netty-all:4.2.0.RC1")

    implementation("org.slf4j:slf4j-log4j12:2.0.16")

    implementation("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    implementation("ch.qos.logback:logback-core:1.5.18")


    implementation(libs.avro)
    //implementation("org.apache.avro:avro-maven-plugin:1.12.0")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}