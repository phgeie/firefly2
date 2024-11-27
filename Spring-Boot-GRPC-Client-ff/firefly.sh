#!/bin/bash

# Farben für die Ausgabe
RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m' # Kein Farbschema


mvn spring-boot:run -Dspring-boot.run.arguments=--grpc.server.port=50051 -Dspring-boot.run.arguments="Firefly1 50051 localhost:50052 localhost:50053"

mvn spring-boot:run -Dspring-boot.run.arguments=--grpc.server.port=50052 -Dspring-boot.run.arguments="Firefly2 50052 localhost:50051 localhost:50053"

mvn spring-boot:run -Dspring-boot.run.arguments=--grpc.server.port=50053 -Dspring-boot.run.arguments="Firefly3 50053 localhost:50051 localhost:50052"
# Warten, bis die Prozesse beendet werden
wait
