## Table of content

- [Overview](#overview)
- [Environment Requirements](#environment-requirements)
- [Installation](#installation)
    - [docker](#docker)
    - [maven](#maven)
- [Demo](#demo)
    - [Swagger UI](#swagger-ui)
    - [Postman Collection](#postman-collection)
    - [CURL](#curl)

## Overview

This is a spring-boot application. It was developed for personal development purposes. It contains features such as
apache kafka, spring shell, spring data, dockerize, exception handling, lombok.

## Environment Requirements

There are several requirements before startup the application

* You need to have `java`, `maven`, `docker`, `docker-compose` on your PC. Please see;
    * [java installation](https://www.java.com/en/download/help/download_options.html)
    * [maven installation](https://maven.apache.org/install.html)
    * [docker installation](https://docs.docker.com/engine/install/)
    * [docker-compose installation](https://docs.docker.com/compose/install/)

## Installation

Before installation, you need to clone this repository.

```
$ git clone https://github.com/sefatascan/recommendation.git
$ cd recommendation
```

In this step, you will package our files for dockerization. You should run this command
and like this, you should see output showing that the applications have successfully packaged it.

```
$ mvn package
  
  [INFO] ------------------------------------------------------------------------
  [INFO] recommendation ..................................... SUCCESS [  1.567 s]
  [INFO] stream-reader ...................................... SUCCESS [  5.637 s]
  [INFO] view-producer ...................................... SUCCESS [  1.933 s]
  [INFO] rest-api ........................................... SUCCESS [  3.176 s]
  [INFO] etl-api ............................................ SUCCESS [  2.136 s]
  [INFO] ------------------------------------------------------------------------
  [INFO] BUILD SUCCESS
  [INFO] ------------------------------------------------------------------------
```

In this step, you can run the application with `docker` requirement. It may take some time if the _images_ it
needs are not available.

Before running the command, you should check the available of the `ports` on your PC.

If this port is not available, You need to modify the `docker-compose.yml` file.

```yml
    ports: # example
      - 5432:5432 => [changeable port]:5432
```

After all checks are provided you can run the command.

```
$ docker-compose up -d
```

### CURL

<details open>
  <summary> [GET] <code>/history/user/{id}</code> </summary>

  ```bash
  curl -X 'GET' \
    'http://localhost:9004/history/user/user-1' \
  ```

</details>

<details open>
  <summary> [DELETE] <code>/history/user/{id}</code> </summary>

  ```bash
  curl -X 'DELETE' \
    'http://localhost:9004/history/user/user-1?productIds=product-1' \
  ```

</details>

<details open>
  <summary> [GET] <code>/recommendation/user/{id}</code> </summary>

  ```bash
  curl -X 'GET' \
    'http://localhost:9004/recommendation/user/user-1' \
  ```

</details>





