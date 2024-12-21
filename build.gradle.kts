plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    maven {
        url = uri("https://maven.aliyun.com/nexus/content/groups/public/")
    }
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    implementation("io.netty:netty-all:4.2.0.RC1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}