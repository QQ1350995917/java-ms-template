#!/bin/sh 
rm -f pid
nohup java -Xms16m -Xmx32m -XX:NewSize=4m -XX:MaxNewSize=8m -jar ms-web-configure-0.0.1-SNAPSHOT.jar > console.out 2>&1  &
  echo $! > pid 
echo "server is runing" 
