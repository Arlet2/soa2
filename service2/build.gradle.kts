plugins {
    id("java")
    id("war")
}

group = "su.arlet"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("javax:javaee-api:7.0")

    implementation("org.jboss.resteasy:resteasy-servlet-initializer:6.2.10.Final")
    compileOnly("org.jboss.resteasy:resteasy-jaxb-provider:6.2.10.Final")
    compileOnly("javax.xml.bind:jaxb-api:2.3.0")

    compileOnly("org.jboss.resteasy:resteasy-multipart-provider:6.2.3.Final")

    implementation("jakarta.platform:jakarta.jakartaee-api:10.0.0")
}

tasks.test {
    useJUnitPlatform()
}