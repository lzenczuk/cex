# Simple RESTful ECB exchange rates client

## Build and run
On Unix systems: ./gradlew run

Windows: gradlew.bat

## Description

### REST urls
- /
- /currencies
- /currencies/USD
- /currencies/USD/2017-03-23

### Explanation of spring configuration
Application use java configuration instead of just pure spring annotations. Reason of this is to avoid "polluting" non
spring classes. On the other hand classes based on spring framework use annotations. In example:

- **ExchangeRateController** - this is spring based class and it's fully annotated using in example Autowired.
- **EcbServiceImpl** - this is business logic class. There is no reason to introduce spring to this class. All dependencies are provided using constructor.
 
This also allow to use business logic classes with other IOC framework (Guice, Dagger) without rewriting classes.
