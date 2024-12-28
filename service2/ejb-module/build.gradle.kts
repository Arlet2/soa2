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
    implementation("jakarta.ejb:jakarta.ejb-api:4.0.0")
    implementation("jakarta.transaction:jakarta.transaction-api:2.0.0")
    implementation("jakarta.ws.rs:jakarta.ws.rs-api:3.0.0")
    implementation("jakarta.validation:jakarta.validation-api:3.0.0")
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.1")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("javax:javaee-api:7.0")
    implementation("org.jboss.ejb3:jboss-ejb3-ext-api:2.3.0.Final")

}

tasks.test {
    useJUnitPlatform()
}