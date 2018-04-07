This is a java application that takes a crypto-currency name from the 
command-line and returns its price and market cap obtained from the API at 
[https://coinmarketcap.com/api/]

##### Docker Build Instructions:
Clone the repository 

 ```
 git clone git@github.com:pwachira/CryptoCurrencyLookup.git 
 ```

Run a gradle build

 ```
 cd CryptoCurrencyLookup && ./gradlew build shadowJar 
 ```

Build the docker image

 ```
 docker build -t cryptocurrency:latest .
 ```

##### Docker Run Instructions:

 ```
 docker run -it --rm  --name cryptocurrency  cryptocurrency:latest
 ```
