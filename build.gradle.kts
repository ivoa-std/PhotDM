
plugins {
    id("net.ivoa.vo-dml.vodmltools") version "0.3.4"
    java
}

vodml {
    vodmlDir.set(file("schema"))
    bindingFiles.setFrom(file("photdmBinding.xml"))
}



dependencies {
    implementation("org.javastro.ivoa.vo-dml:ivoa-base:1.0-SNAPSHOT")
    // Use JUnit Jupiter for testing.
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.2")
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
