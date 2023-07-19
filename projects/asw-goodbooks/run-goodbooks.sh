#!/bin/bash

# Script per avviare l'applicazione GoodBooks 

echo Running GOODBOOKS 

# Consul deve essere avviato separatamente 
sudo docker-compose up --build
#java -Xms64m -Xmx128m -jar recensioni/build/libs/recensioni.jar &
#java -Xms64m -Xmx128m -jar connessioni/build/libs/connessioni.jar &
#java -Xms64m -Xmx128m -jar recensioni-seguite/build/libs/recensioni-seguite.jar &

#java -Xms64m -Xmx128m -jar api-gateway/build/libs/api-gateway.jar &
