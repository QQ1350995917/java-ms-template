tpid=`cat pid | awk '{print $1}'` 
tpid=`ps -aef | grep $tpid | awk '{print $2}' |grep $tpid`
  if [ ${tpid} ]; then 
kill -9 $tpid 
fi
echo "server is stop"
