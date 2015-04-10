#!/bin/bash
HOST="localhost"
PORT="8080"
URL="http://$HOST:$PORT"
CONTENT="monolithic-0.0.1"
WARFOLDER="/Users/davor/development/my_repos/ExploringMicroservices/monolithic/build/libs"
WARPATH="$WARFOLDER/monolithic-0.0.1.war"
SERVER_BIN="/Users/davor/development/servers/apache-tomcat-8.0.20/bin"
TEST_URL=('/info' '/api/discover' '/time' '/calculate?expression=5/6')

export JAVA_HOME=$JAVA_8_HOME && JDK_HOME=$JAVA_8_HOME && JRE_HOME=$JAVA_8_HOME
java -version

# remove old war
echo "WAR path: $WARPATH"
rm -rf $WARPATH
rm -rf "$WARFOLDER/${CONTENT}-war"


#build new WAR
gradle war

mkdir "$WARFOLDER/${CONTENT}-war"
unzip $WARPATH -d "${WARFOLDER}/${CONTENT}-war"

#Check if tomcat is running
echo 'Check if Tomcat is running'
STAT=$(curl -sL -w "%{http_code}" http://${HOST}:${PORT} -o /dev/null)

if [ $STAT -ne 200 ]
then
   echo "  Starting Tomcat     ${SERVER_BIN}/startup.sh"
   $SERVER_BIN/startup.sh

   sleep 5
else
	echo "  Tomcat already started"
fi

# undeploy war from tomcat
STAT=$(curl -sL -w "%{http_code}" http://${HOST}:${PORT} -o /dev/null)

if [ $STAT -eq 200 ]
then
	echo "Undeploying: $CONTENT"
	wget --http-user=tomcat --http-password=tomcat "http://$HOST:$PORT/manager/text/undeploy?path=/$CONTENT" -O - -t 2

	echo "Restarting server"
	echo "  Stoping.."
	ps aux | grep -i 'tomcat' | awk '{print $2}' | xargs kill || echo "Killing Tomcat process"
	sleep 5

	echo "  Starting...   $SERVER_BIN/startup.sh"
	$SERVER_BIN/startup.sh
	sleep 5
	echo "------------"
	
	STAT=$(curl -sL -w "%{http_code}" http://${HOST}:${PORT} -o /dev/null)

	if [ $STAT -eq 200 ]
	then
		echo "Deploying WAR: $WARPATH"
		wget --http-user=tomcat --http-password=tomcat "http://$HOST:$PORT/manager/text/deploy?war=file:$WARPATH&path=/$CONTENT" -O - -t 2
		sleep 10

	
		#Check URLs
		echo -e "\n"
		for url in "${TEST_URL[@]}"
		do
			echo -e "--- curl http://$HOST:$PORT/$CONTENT$url \n"
			curl "http://$HOST:$PORT/$CONTENT$url" | python -m json.tool | pygmentize -l json
			echo -e "\n"
		done
	else
		echo "Tomcat NOT started"
	fi
else
	echo "Can't undeploy $CONTENT! Server not running on $HOST:$PORT"
fi