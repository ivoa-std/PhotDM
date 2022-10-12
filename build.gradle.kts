
plugins {
    id("net.ivoa.vo-dml.vodmltools") version "0.3.9"
}

vodml {
    vodmlDir.set(file("schema"))
    bindingFiles.setFrom(file("photdmBinding.xml"))
}

dependencies {
    implementation("org.javastro.ivoa.vo-dml:ivoa-base:1.0-SNAPSHOT") //IMPL the "standard" base library
    // Use JUnit Jupiter for testing.
    implementation("org.slf4j:slf4j-api:1.7.32")
    testRuntimeOnly("ch.qos.logback:logback-classic:1.2.3")
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.2")
    testImplementation("org.javastro:jaxbjpa-utils:0.1.2:test")
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
