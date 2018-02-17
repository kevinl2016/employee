
# Employee Management Demo - Spring Boot app & Postgresql & Docker compose


## 1. Build app & Dockerfile

`mvn clean install`


## 2. Run docker-compose

`cd src/main/docker`

`docker-compose up`

**What happens:**

1. Starts Postgresql and waits up to 15 seconds for it to finish ([using wait-for-it](https://github.com/vishnubob/wait-for-it))
2. Starts Spring boot application which populates database with some test data

## 3. Test

Navigate to <http://localhost:8080/employees> and you should see: `[{"id":150,"name":"Jamie","manager":null},{"id":100,"name":"Alan","manager":{"id":150,"name":"Jamie","manager":null}},{"id":220,"name":"Martin","manager":{"id":100,"name":"Alan","manager":{"id":150,"name":"Jamie","manager":null}}},{"id":275,"name":"Alex","manager":{"id":100,"name":"Alan","manager":{"id":150,"name":"Jamie","manager":null}}},{"id":400,"name":"Steve","manager":{"id":150,"name":"Jamie","manager":null}},{"id":190,"name":"David","manager":{"id":400,"name":"Steve","manager":{"id":150,"name":"Jamie","manager":null}}}]`

## 2. Web

`cd web`

## Available Scripts

In the project directory, you can run:

### `npm install`
Install dependencies

### `npm start`

Runs the app in the development mode.<br>
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.<br>
You will also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.<br>
