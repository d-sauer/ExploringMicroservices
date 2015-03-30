#!/bin/bash
if [ $# -gt 2 ]; then
	#Check URLs
	for t_url in "${@:2}"
	do
		URL=$1/$t_url
		echo -e "--- curl $URL \n"
		curl "$URL" | python -m json.tool | pygmentize -l json
		echo -e "\n"
	done	

else
	echo 'Provide URL and endpoints for request! e.g. http://localhost:8080 info discover time'
fi

