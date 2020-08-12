#!/usr/bin/env bash

function go_to_root_directory() {
    root_directory=$(git rev-parse --show-toplevel)
    echo "root directory:"
    echo root_directory
    cd "$root_directory" || exit 1
}

function destroy_docker() {
  echo "destroying old docker"
  docker stop todo
  docker rm todo
}

function run_docker() {
  echo "running docker"
  sleep 2
  docker run -d -p 5432:5432 -e POSTGRES_PASSWORD="$DB_PASSWORD" --name todo postgres
#  docker run -d -p 3306:3306 -e MYSQL_PASSWORD="$DB_PASSWORD" --name todo mysql
  sleep 2
}

function rebuild_db() {
  echo "rebuilding db"
#  mysql -h localhost -U mysql todo < scripts/create_databases.sql
  psql -h localhost -U postgres -f scripts/create_databases.sql
}

function main() {
  go_to_root_directory

  source ./scripts/common.sh || exit 1
  common.set_bash_fail_on_error

  destroy_docker
  run_docker
  rebuild_db
}

main "$@"