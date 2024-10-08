
# SOCIAL BLOGGING SITE DEMO API

This is a demo api social blogging site


## Requirements
This demo is build with with Maven 3.8.x and Java 21.
## Installation

### 1. Clone the application

```bash
  git clone https://github.com/duyxuan11/SocialBloggingSite.git
```
### 2. Build and run the app using maven
```bash
  cd SocialBloggingSite
  mvn package
  java -jar target/SocialBloggingSite-0.0.1-SNAPSHOT.jar
```
Alternatively, you can run the app directly without packaging it like so -
```bash
  mvn spring-boot:run
```
### 3. Build and run the app using IDE
You can import the packages in the file pom.xml and start if done
## Functionality overview
The example application is a social blogging site (i.e. a Medium.com clone) called "Conduit". It uses a custom API for all requests, including authentication.
### General functionality:
- Authenticate users via JWT (login/signup pages + logout button on settings page)
- CRU* users (sign up & settings page - no deleting required)
- CRUD Articles
- CR*D Comments on articles (no updating required)
- GET and display paginated lists of articles
- Favorite articles
- Follow other users
#### The general page breakdown:
- Sign in/Sign up pages (URL: api/auth/login, api/auth/signup )
  - Uses JWT (store the token in localStorage)
  - Refresh Token (URL: api/auth/refresh-token)
- Profile users (URL: api/users/{userId} )
  - Edit user
  - Show basic user info
- Articles CRUD (URL: api/articles/{articleId} )
  - Create article
  - Delete article by Id
  - List and Update article by Id
  - Pagination for list of articles
- Send and delete Comments
- Follow other users
- Favorite articles
