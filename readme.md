## Readme for the Learning Enhancement system, developed by Brukerfeil

# System Architecture

```git clone https://github.com/vegardalvsaker/BrukerfeilLES``` and ```git checkout develop``` in order to download the project.

This system uses Java EE servlets with Tomcat server. The database used is a MySQL-server. Execute the sql-file in the ```LES/sql``` -directory in order to set up your database to work with this project.

The files ```web.xml``` and ```context.xml``` are ignored. These files need to be added individually because they are individual project files with references to your database username and password, as well as jdbc-driver reference.
## context.xml will look like this
### Enter your own stuff where obv.
```
<?xml version="1.0" encoding="UTF-8"?>
<Context path="/NavnPåNetbeansProsjekt">
    <Resource name="jdbc/localhostDS" type="javax.sql.DataSource" auth="Container" 
        username="brukernavnTilMYSQLServer" password="PassordTilMYSQLServer" driverClassName="enten com.mysql.jdbc.Driver eller com.mysql.cj.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/NavnPåSchema">
    </Resource>
</Context>
```

## WHEN MAKING CHANGES TO THE CODE, BRANCH OUT OF THE ```develop```-BRANCH, AND NAME THE BRANCH AS FOLLOWS: ```feature_nameOfTheFeatureYouAreGoingToWorkOn```. WHEN YOU ARE DONE, MAKE A PULL REQUEST TO THE ```develop```-BRANCH.

# Package documentation

### Package Servlets:

Servlets contains all the servlet classes. These are for handling the request and response. Each servlet usually call some
UI-method and some other calculations, e.g., assign an object to a session.

### Database package

Classes in this package handles database queries. The name of each class explains which part of the database the class is going to handle. 
All classes in this package extends (is a subclass of) ```Database.java```, except for ```Database.java``` itself.

### HtmlTemplates

Classes in this package handles HTML codeblocks.

### Printers package
Classes here are mostly responsible for calling the Htmltemplates' classes' methods.
Classes here are also dedicated to the printing of specific elements in a page. For instance, the front page prints all the modules, and therefore there is a method doing this in the ```FrontpagePrinter.java```

### Classes package

Classes here are a representation of the tables in the database. Here you will find classes such as ```Module.java```, which has all the attributes that the module-table in the database has, as ```private``` fields.
