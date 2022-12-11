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
chmod 777 /usr/local/bin/argocd

umask 0027

/usr/local/bin/argocd login argocd-server.argocd:80 --username  admin --insecure --password   $ARGOTOKEN ;
echo /usr/local/bin/argocd login argocd-server.argocd:80 --username  admin --insecure --password   $ARGOTOKEN

/usr/local/bin/argocd app create  $APPNAME  --repo  $APPREPO  --path  $APP --dest-server https://kubernetes.default.svc --dest-namespace default
echo /usr/local/bin/argocd app create  $APPNAME  --repo  $APPREPO  --path  $APP  --dest-server https://kubernetes.default.svc --dest-namespace default

/usr/local/bin/argocd app set  $APPNAME  --sync-policy automated
echo /usr/local/bin/argocd app set  $APPNAME  --sync-policy automated



#java -Dlog4j2.formatMsgNoLookups=true -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 /spring-boot-2-rest-service-basic-0.0.1-SNAPSHOT.jar
