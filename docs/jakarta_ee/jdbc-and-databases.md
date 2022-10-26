# JDBC And Databases

## Introduction
JDBC library  to connect many type of 
## Install a database - MySQL

### Windows 
Download the MSI installer at [MySQL Community 8.0](https://dev.mysql.com/downloads/installer/)

Next, install the application and its features.
You will also need to configure a root user and an additional users.
#### Create a database 

````sql
drop database javaee;
create database javaee default character set utf8mb4 collate utf8mb4_general_ci;
create table javaee.names (
	id int not null auto_increment,
    firstname varchar(45) not null,
    lastname varchar(45) not null,
    primary key (id)
) engine = InnoDB;
````

### Add dependency
In the service consumer module of the application, 
we add the dependency ``mysql-connector-j`` which manage communication with the database MySQL.

````xml
<dependencies>
  ...
  <!-- ===== MySQL Database Connector ========== -->
  <dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.0.31</version>
  </dependency> 
</dependencies>
  
````

### Mock Data 

````sql
insert into javaee.names(firstname, lastname) values("John", "Doe");
insert into javaee.names(firstname, lastname) values("Jane", "Doe");
insert into javaee.names(firstname, lastname) values("Jack", "Sparrow");
````