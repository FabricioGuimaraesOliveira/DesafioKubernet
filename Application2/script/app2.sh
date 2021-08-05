http_response=0
echo $1
echo $2
localhost=$1
message=$2
request(){
http_response=$(curl -s -o response.json -w "%{http_code}" -X POST -H "Content-Type: application/json" \
	    -d '{"message": "'${message}'"}' \
	    http://${localhost}:8080/log)
	    
}

while true; do
    
request

if [ $http_response != "200" ]; then
    echo "Error ao registrar o log: ${http_response}"
    sleep 2
else
    echo "Log registrado com sucesso : ${http_response}"
    break

fi
   
done
    echo "Fim do cliente"




