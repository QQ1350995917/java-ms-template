#!/bin/sh 
rm -f pid
# nohup java -jar ${projectName}-${projectVersion}.jar >/dev/null 2>&1  &
nohup java -Xms16m -Xmx32m -XX:NewSize=4m -XX:MaxNewSize=8m -jar ${projectName}-${projectVersion}.jar > console.out 2>&1  &
echo $! > pid 
echo "server is runing" 
