#!/usr/bin/env bash

function set_bash_fail_on_error() {
  set -o errexit
  set -o errtrace
  set -o nounset
  set -o pipefail
}

function go_to_root_directory() {
    root_directory=$(git rev-parse --show-toplevel)
    echo "root directory:"
    echo root_directory
    cd "$root_directory"
}

function purge_db() {
    PGPASSWORD=$DB_PASSWORD psql -h localhost -U postgres todo -f scripts/purge_database.sql
}

function main() {
  go_to_root_directory
  purge_db
}

main "$@"