#!/bin/bash

# jar包名称
JAR_NAME="`pwd`/ms-web-gateway-0.0.1-SNAPSHOT.jar"
echo "JAR FILE PATH: $JAR_NAME"

# profile
PROFILE_NAME=$1
PROFILE_OPTS="--spring.profiles.active=$PROFILE_NAME"
if [ -z $PROFILE_NAME ]; then
	PROFILE_NAME="default"
	PROFILE_OPTS=""
fi


# 记录pid的文件名
PID_FILE="$PROFILE_NAME.pid"
rm -rf $PID_FILE

# 日志目录
DIR_LOG="`pwd`/logs"
if [ ! -d $DIR_LOG ]; then
	mkdir $DIR_LOG
fi
STDOUT_FILE="$DIR_LOG/console-$PROFILE_NAME.out"
if [ -f $STDOUT_FILE ]; then
	STDOUT_FILE_BAK="$DIR_LOG/"console-$PROFILE_NAME-`date +%Y%m%d%H%M%S`.out" "
	mv $STDOUT_FILE $STDOUT_FILE_BAK
fi
echo "STDOUT: $STDOUT_FILE"

# 启动内存
JAVA_MEM_OPTS="-XX:MetaspaceSize=32m -XX:MaxMetaspaceSize=128m -Xms256m -Xmn256m -Xmx256m -XX:NewRatio=1 -XX:SurvivorRatio=6 -XX:MaxDirectMemorySize=64m -Xss1024K -XX:ParallelGCThreads=20 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+UseCMSCompactAtFullCollection -XX:CMSInitiatingOccupancyFraction=80 -XX:+PrintCommandLineFlags -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintHeapAtGC -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -Xloggc:./logs/gc`date+%Y%m%d`.log -XX:HeapDumpPath=./logs/dump"
echo "JAVA_MEM_OPTS: $JAVA_MEM_OPTS"
#JAVA_JMX_OPTS="-Dcom.sun.management.jmxremote -Djava.rmi.server.hostname=0.0.0.0 -Dcom.sun.management.jmxremote.port=11220 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"

# 启动
nohup java $JAVA_MEM_OPTS $JAVA_JMX_OPTS -jar $JAR_NAME $PROFILE_OPTS > $STDOUT_FILE 2>&1 &

# 将pid写入文件
echo $! > $PID_FILE

