#!/bin/sh

message="auto-commit from $USER@$(hostname -s) on $(date)"
GIT=`which git`
REPO_DIR=/helm-charts
cd ${REPO_DIR}
${GIT} add --all .
${GIT} commit -m "$message"

#gitPush=$(${GIT} push -vvv git@github.com:shashikanth-vh/helm-charts.git master 2>&1)
#echo "$gitPush"
git push origin main
