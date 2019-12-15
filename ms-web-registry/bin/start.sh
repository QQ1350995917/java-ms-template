#!/bin/sh 
rm -f pid
 # nohup java -jar ms-web-registry-0.0.1-SNAPSHOT.jar >/dev/null 2>&1  & 
nohup java -Xms30m -Xmx60m -XX:NewSize=8m -XX:MaxNewSize=16m -jar ms-web-registry-0.0.1-SNAPSHOT.jar > console.out 2>&1  &
  echo $! > pid 
echo "server is runing" 