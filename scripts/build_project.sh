#!/usr/bin/env bash

function set_bash_fail_on_error() {
  set -o errexit
  set -o errtrace
  set -o nounset
  set -o pipefail
}

function go_to_root_directory() {
  root_directory=$(git rev-parse --show-toplevel)
  cd "$root_directory"
}

function build() {
    ./gradlew clean build
}

function main() {
  set_bash_fail_on_error
  go_to_root_directory
  build
}

main "$@"