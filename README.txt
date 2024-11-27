mvn spring-boot:run -Dspring-boot.run.arguments=--grpc.server.port=50051 -Dspring-boot.run.arguments="Firefly1 50051 localhost:50052 localhost:50053"

mvn spring-boot:run -Dspring-boot.run.arguments=--grpc.server.port=50052 -Dspring-boot.run.arguments="Firefly2 50052 localhost:50051 localhost:50054"

mvn spring-boot:run -Dspring-boot.run.arguments=--grpc.server.port=50053 -Dspring-boot.run.arguments="Firefly3 50053 localhost:50051 localhost:50054"

mvn spring-boot:run -Dspring-boot.run.arguments=--grpc.server.port=50054 -Dspring-boot.run.arguments="Firefly4 50054 localhost:50052 localhost:50053"
