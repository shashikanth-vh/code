#!/bin/bash
# Copyright 2020 Huawei Technologies Co., Ltd.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# Contain at most 63 characters
# Contain only lowercase alphanumeric characters or '-'
# Start with an alphanumeric character
# End with an alphanumeric character


echo "Starting app"
#chmod 777 /usr/local/bin/argocd

git config --global user.email "shashikanth.vh@gmail.com"
git config --global user.name "shashikanth-vh"

umask 0027
#cd /usr/app || exit
java -Dlog4j2.formatMsgNoLookups=true -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 /spring-boot-2-rest-service-basic-0.0.1-SNAPSHOT.jar
