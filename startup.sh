#!/bin/sh
hostname="$(hostname| cut -d' ' -f 1)"
containerip="$(hostname -i)"
hostip="$HostIp"
echo "$containerip , $hostip"

if [ -n "$hostip"  ]; then
   hostip="$(hostname -i)"
fi

DIR=logs/"$ImageName"$(hostname)
mkdir -p $DIR
cd $DIR

echo 'jbot web back starting...'

export LANG=zh_CN.UTF-8
export instanceId=$hostname
java -Dfile.encoding=UTF8 -jar /home/listen/Apps/jbot-0.0.1-SNAPSHOT.jar