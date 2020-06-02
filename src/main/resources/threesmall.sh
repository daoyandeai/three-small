#!/bin/sh
#author tsh
#date 2019-07-22
process=three-small.jar
echo "正在进行$process检测..."
pid=`ps -ef | grep $process | grep -v grep | awk '{print $2}'`
if [ -n "$pid" ] ; then
 kill -9 $pid
  sleep 3
   pid2=`ps -ef | grep $process | grep -v grep | awk '{print $2}'`
    if [ -n "$pid2" ] ; then
      echo "$process进程杀死失败!"$pid2
        exit 1
    else
        echo "$process进程杀死成功"$pid2
    fi
else
	 echo "$process暂未启动"
fi

cd /home/project/threesmallsys && nohup java -Xms512m -Xmx512m -jar three-small.jar & >> "/home/project/threesmallsys/$(date +"%Y-%m-%d").log"  #&&代表执行完前部分代码后立刻执行后面代码
pid3=`ps -ef | grep $process | grep -v grep | awk '{print $2}'`

if [ -n "$pid3" ] ; then
	 echo "$procee服务启动成功,pid:"$pid3
else
	 echo "$process服务启动失败!"
fi

		 
