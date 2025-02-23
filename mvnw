#!/bin/sh
set -e

MAVEN_VERSION=3.8.6
MVN_CMD="mvn"

if [ ! -z "$MAVEN_OPTS" ]; then
  MVN_CMD="$MVN_CMD $MAVEN_OPTS"
fi

if [ -z "$M2_HOME" ]; then
  M2_HOME="$HOME/.m2"
fi

if [ -z "$MAVEN_HOME" ]; then
  MAVEN_HOME="$M2_HOME"
fi

if [ -z "$JAVA_HOME" ]; then
  echo "JAVA_HOME is not set. Please set it to your JDK installation."
  exit 1
fi

if [ ! -d "$MAVEN_HOME" ]; then
  echo "Maven home directory does not exist: $MAVEN_HOME"
  exit 1
fi

exec "$MVN_CMD" "$@"