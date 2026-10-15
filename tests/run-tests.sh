#!/usr/bin/env bash
set -euo pipefail

if [[ -n "${JAVA_HOME:-}" ]]; then
  javac_bin="$JAVA_HOME/bin/javac"
else
  javac_bin="$(command -v javac)"
fi

javac_version="$("$javac_bin" -version 2>&1)"
if [[ ! "$javac_version" =~ javac\ (2[5-9]|[3-9][0-9])(\.|$) ]]; then
  printf 'JDK 25 ou superior é necessário. Encontrado: %s\n' "$javac_version" >&2
  exit 2
fi

mvn -Dmaven.repo.local="${MAVEN_REPO_LOCAL:-/tmp/sorvete-patterns-m2}" test
