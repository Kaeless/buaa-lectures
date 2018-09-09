#!/usr/bin/env bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-10.0.2.jdk/Contents/Home
mvn clean package -DskipTests -U
