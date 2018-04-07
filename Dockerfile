from openjdk:latest
COPY build/libs/CryptoCurrency-all.jar .
CMD java -cp CryptoCurrency-all.jar  hugo.CryptocurrencyClient
