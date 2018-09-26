# Elasticsearch
Elastic Search Sample Application Using Spring Data JPA and Spring Data Elastic Search. 

We are using inbuilt elastic search of spring, we do  not require external elastic search engine application to be running . 

# Requirement
1. Java 1.8 and above updated jre path
 
 # Installation Steps
 1. Import this project in your IDE.
 2. Build it using maven clean install command.
 3. Start server.
 
 # Database Configuration
 We are using in memory databasei.e H2 database for the application.
 
 # Table Structure
 https://github.com/thundercool/elasticsearch/blob/master/DBMS%20ER%20Diagram.png
 
 
 # Api Documentation
 For accessing api we have used swagger, which can be accessed via url - http://localhost:8080/swagger-ui.html
 
 # Json Structure 
 {
  "title": "",
  "metadata": {
    "region": "",
    "long_synopsis": "",
    "meta_desc": "",
    "tags": [
      ""
    ]
  },
  "status": "",
  "id": "",
  "date": 0
}

# Error Response Strucutre

{
  "errorCode": "",
  "message": ""
}
 
 
 
 




