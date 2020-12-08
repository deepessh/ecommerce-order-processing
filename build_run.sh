cd order-service
chmod +x mvnw
./mvnw package

cd ..

cd discovery-service
chmod +x mvnw
./mvnw package

cd ..

cd item-service
chmod +x mvnw
./mvnw package

cd ..

cd api-gateway
chmod +x mvnw
./mvnw package

cd ..

cd address-service
chmod +x mvnw
./mvnw package

cd ..

cd customer-service
chmod +x mvnw
./mvnw package

cd ..

cd payment-method-service
chmod +x mvnw
./mvnw package

cd ..

docker-compose build --no-cache
docker-compose up