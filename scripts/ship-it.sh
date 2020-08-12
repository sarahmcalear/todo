#!/usr/bin/env bash

function go_to_root_directory() {
  root_directory=$(git rev-parse --show-toplevel)
  cd "$root_directory" || exit
}

function fail_for_unstaged_files() {
  local -r unstaged_files_count=$(git status --porcelain | wc -l)
  local -r trimmed_unstaged_files_count=$(echo -e -n "$unstaged_files_count" | tr -d ' ')
  if [ "$trimmed_unstaged_files_count" != "0" ]; then
    local -r unstaged_files=$(git status --porcelain)
    local -r RED_COLOR_CODE='\033[0;31m'
    echo -e "${RED_COLOR_CODE}\nERROR!\\nUnstaged and/or uncommitted files found! Please clean these up and try again:\\n${unstaged_files}"
    exit
  fi
}

function push() {
    git push origin head
}

function main() {
  go_to_root_directory
  fail_for_unstaged_files
  ./scripts/run-tests.sh
  push
}

main "$@"