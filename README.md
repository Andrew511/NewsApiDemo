# NewsApiDemo
Demo application using Java spring to interact with a news api and count the number of articles for a topic, grouped by the article source.

In order to successfully query the NewsApi a api key must be generated from https://newsapi.org/ and kept within a secrets.properties file located within src/main/resources
 with the key of API_KEY.

This application is separated into three layers, the controller layer, the service layer, and the Data Access layer.

The controller layer handles recieving requests for artics based on topic, it also handles any exceptions thrown and passes them back to the client with the appropriate message.

The controller layer passes the topic request to the service layer through NewsApiService.java, which handles all business logic and evaluating the data,
this includes formatting response data from the Data Access layer into a well defined java object for easier use by the rest of the application.

The data access layer handles formatting the the url request and sending the request. this is handled through the TopHeadlinesRequestBuilder class to provide
 a modular interface for creating a request for any of the TopHeadline api's possible endpoints.
