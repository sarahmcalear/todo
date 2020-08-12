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
    ./scripts/build_project.sh
}

function start_app() {
    java -jar server/build/libs/server-0.0.1-SNAPSHOT.jar
}

function main() {
  set_bash_fail_on_error
  go_to_root_directory
  build
  start_app
}

main "$@"