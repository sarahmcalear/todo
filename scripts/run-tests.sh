#!/usr/bin/env bash

function set_bash_fail_on_error() {
  set -o errexit
  set -o errtrace
  set -o nounset
  set -o pipefail
}

function check_scripts() {
  shellcheck scripts/*.sh
}

function go_to_root_directory() {
  root_directory=$(git rev-parse --show-toplevel)
  cd "$root_directory"
}

function build_project() {
  ./scripts/build_project.sh
}

function purge_local_db() {
    ./scripts/purge_db.sh
}

function e2e_tests() {
  java -jar server/build/libs/server-0.0.1-SNAPSHOT.jar &
  local -r RED_COLOR_CODE='\033[0;31m'
  local -r WHITE_COLOR_CODE='\033[0;37m'
  local -r GREEN_COLOR_CODE='\033[0;32m'

  ./gradlew yarn_run_cypress-run || {
    echo -e "${RED_COLOR_CODE}\\nEnd to end tests failed... \\nExiting!${WHITE_COLOR_CODE}" && shut_down_app >&2
    exit 1
  }
  echo -e "${GREEN_COLOR_CODE}\\nEnd to end tests successful! \\nExiting${WHITE_COLOR_CODE}"
  shut_down_app
}

function display_success_message() {
  local -r GREEN_COLOR_CODE='\033[0;32m'
  echo -e  "${GREEN_COLOR_CODE}\\n$(cat scripts/success_ascii_art.txt)"
}

function shut_down_app() {
  # shellcheck disable=SC2046
  kill $(lsof -t -i :8080)
}

function main() {
  set_bash_fail_on_error
  go_to_root_directory
  check_scripts
  build_project
  purge_local_db
  e2e_tests
  display_success_message
}

main "$@"