#!/usr/bin/env bash

function go_to_root_directory() {
  root_directory=$(git rev-parse --show-toplevel)
  cd "$root_directory" || exit 1
}

function build() {
    ./scripts/build_project.sh
}

function start_app() {
    java -jar server/build/libs/server-0.0.1-SNAPSHOT.jar
}

function main() {
  go_to_root_directory

  source ./scripts/common.sh
  common.set_bash_fail_on_error

  build
  start_app
}

main "$@"