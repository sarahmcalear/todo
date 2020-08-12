#!/usr/bin/env bash

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

  source ./scripts/common.sh || exit 1
  common.set_bash_fail_on_error

  purge_db
}

main "$@"