#!/bin/sh 
rm -f pid
 # nohup java -jar ms-web-article-0.0.1-SNAPSHOT.jar >/dev/null 2>&1  & 
nohup java -Xms16m -Xmx32m -XX:NewSize=4m -XX:MaxNewSize=8m -jar ms-web-article-0.0.1-SNAPSHOT.jar > console.out 2>&1  &
  echo $! > pid 
echo "server is runing" 
