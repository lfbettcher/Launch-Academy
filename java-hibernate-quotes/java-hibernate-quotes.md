## Getting Started

```no-highlight
et get java-hibernate-quotes
cd java-hibernate-quotes
idea .
```

***Note: you may need to run `dropdb quotes` prior to the next step.***

```no-highlight
createdb quotes
psql quotes
\i src/main/resources/schema.sql
\q
```

## Quotes in Java

We have set up your application to link Hibernate to your `quotes` Postgres database, and created a `quotes` table. Now, work through the steps below!

* Create a `Quote` entity with fields for each field in the schema
* Ensure that you are generating the Id sequentially
* Ensure you have annotations for each database constraint (see the `src/main/resources/db/schema.sql` file for reference!)
* Update your `Main.java` to create some quote records and ensure that your records save to the database correctly
  * Make sure to close your `EntityManager` and `EntityManagerFactory`


## Authors for your Quotes

We will learn about associations later in the week but let's build out our application a little further in anticipation.

* The `Author` class has been provided for you
* Update your schema to create an `authors` table based on the information in the class
* Ensure your database constraints match the ones in the class
* Update your `Main.java` to create Authors and ensure the records save to the database correctly
