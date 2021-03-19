## Learning Goals

- Justify the need for aggregate queries
- Use the `COUNT`, `AVG`, and `SUM` aggregate functions
- Write queries with a `GROUP BY` clause
- Write queries with a `HAVING` clause

## Getting Started

```
et get database-aggregations
cd database-aggregations
createdb world
psql world

world=# \i world.sql
```

For this exercise, we'll use a popular "sample database" to learn about aggregate queries. It doesn't use all of our conventions, so be sure to follow along closely with the SQL provided.

## What's an Aggregate Query?

Databases are powerhouses when it comes to...well, data. So far, we've been using databases to perform basic CRUD. Particularly when dealing with numeric values, databases can also perform calculations on our data.

```sql
SELECT COUNT(*) FROM city;
```

Here, we're returning the count of all cities found in the sample database. When we execute this query, we're presented with the following output from `psql`:

```no-highlight
 count
-------
  4079
```

## Why not use programming?

The thought may come to mind. To date, we've executed SQL that would retrieve all of the rows in a given table. Using a Node library, we can return an array of all of the records in the system.

This is ok for hundreds, or even thousands of records, but production databases can scale to be millions or billions of records. If we load *all* of the records in such a database table into memory, we will run out of available memory on our production machines, which will crash our backend systems.

Databases are built for this type of computation. In our backend applications, we should always rely heavily on the database to perform filtration, sorting, and aggregation of our data.

# Aggregate Functions

There are a host of aggregate functions we can use with our database.

## Getting an Average
One of the most essential computations that we need to run when crunching data is getting the average of some dataset. Thankfully SQL provides the `AVG()` aggregate query to help us get the average of all of the values across a specific column in one of our tables.

```sql
SELECT AVG(population) FROM city;
```

```no-highlight
avg
---------------------
350468.223584211817
```

Because we are trying to *retrieve* data from our table, our aggregate queries still begin with the `SELECT` keyword. The `AVG()` keyword is used, and receives an argument matching a specific column (in this case population). Of course, we need to finish this query by designating the table we are concerned with as well, and our query should return to us the average population of all of the cities in our `city` table.

### Finding the Maximum City Population

```sql
SELECT MAX(population) FROM city;
```

```no-highlight
   max
----------
 10500000
```

This provides us with the largest population value in the database. We can take this value to determine the city with the highest population.

```sql
SELECT name FROM city WHERE population = 10500000;
```

```no-highlight
      name
-----------------
 Mumbai (Bombay)
```

### Finding the Minimum City Population

Similarly, we can find the smallest population value in the database.

```sql
SELECT MIN(population) FROM city;
```

```no-highlight
 min
-----
  42
```

```sql
SELECT name FROM city WHERE population = 42;
```

```no-highlight
   name
-----------
 Adamstown
```

### Getting the Sum of City Population

We can get the total logged population in our city database by using the `SUM` function:

```sql
SELECT sum(population) FROM city;
```

```no-highlight
    sum
------------
 1429559884
```

If we programmatically added up all the `population` values present in the `city` table, we'd arrive at the same result.

## You May Not Need Aggregates

It's worth mentioning that sometimes, aggregates are not the answer. If we go back to our `MIN` and `MAX` examples, finding the largest population is simpler with a single query:

```sql
SELECT name
FROM city
ORDER BY population DESC
LIMIT 1;
```

Notice this does not use an aggregate query, but it accomplishes the same task.

## GROUP BY Results

While reporting out on aggregates for an entire table is useful, sometimes we also want to calculate aggregates in buckets. For example, how can we calculate the population of each country using the city database?

```sql
SELECT sum(population) AS country_population, countrycode
FROM city
GROUP BY countrycode;
```

```no-highlight
              62915 | BLZ
            8569906 | BGD
           15087019 | ITA
             537096 | OMN
            2944034 | TZA
             786755 | PAN
            7469006 | ROM
            2953310 | HUN
```

As you can see, the `GROUP BY` clause essentially runs a sum calculation for each country code. In this way, we receive more specific aggregate data respective to each country, rather than the total population of all countries, which is less useful to us.

Similarly, we can use `GROUP BY` in conjunction with out other aggregate queries, such as `AVG` as well.

```sql
SELECT AVG(population) AS average_city_population, countrycode
FROM city
GROUP BY countrycode;
```

```no-highlight
average_city_population | countrycode
----------------------------+-------------
       224632.200000000000 | MDG
    12000.0000000000000000 | PLW
     1500.0000000000000000 | BMU
       150393.333333333333 | PSE
       725000.000000000000 | COG
```

### GROUP BY and SELECT

Once we incorporate a `GROUP BY` clause, it's really difficult to include additional field values in our `SELECT`. For example, let's say we wanted to also output the name of a country using an `INNER JOIN` statement.

```sql
SELECT sum(city.population) AS country_population, countrycode, country.name
FROM city
INNER JOIN country ON city.countrycode = country.code
GROUP BY countrycode
ORDER BY countrycode;
```

```no-highlight
ERROR:  column "country.name" must appear in the GROUP BY clause or be used in an aggregate function
```

There are a few things worth mentioning here:

- Because both the `city` table and the `country` table have a `population` value, we must disambiguation between the two when making our column selections. The `city.` prefix explicitly tells PostgreSQL that we want to retrieve the `population` field on the `city` table.
- We're also *aliasing* the aggregate function with a column name using the `AS` keyword. This provides a more friendly name for the resulting column in our output.
- We can `JOIN` on the basis of the Country Code even though it is not defined as a PRIMARY KEY or FOREIGN KEY. In fact, we can perform a join on the basis of any column, provided the values correlate on both sides of the join.

Beyond that, when we use a `GROUP BY` clause, and include columns that either are not aggregated or used in the `GROUP BY`, we're presented with an error message. In order to include additional columns, we must employ a nested query.

#### Nested Queries

In order to display additional information alongside an aggregate query, we have to *nest* that query inside another statement. Let's improve our City population tabulation.

```sql
SELECT country_population, countrycode, country.name
FROM (
  SELECT sum(city.population) AS country_population, countrycode
  FROM city
  GROUP BY countrycode
) AS population_totals
INNER JOIN country ON population_totals.countrycode = country.code
ORDER BY country.code;
```

We've placed our aggregate query inside parenthesis to effectively create a dynamic view or table. When we execute this SQL, PostgreSQL will temporarily create a table allowing us to perform a JOIN like we've done above.

Notice that after the parens we have used a **table alias** to easily identify our dynamic view. We then perform an `INNER JOIN` using that table alias. Once we've performed the JOIN successfully, we can then select columns from both the aggregate query and our `country` table.

## The HAVING Clause

Because of the way SQL works, we cannot use an aggregate query in a `WHERE` clause. SQL provides us with a distinct keyword for filtering our data on the basis of an aggregation.

```sql
SELECT sum(population) AS country_population, countrycode
FROM city
GROUP BY countrycode
HAVING sum(population) > 5000000
ORDER BY countrycode;
```

This `HAVING` clause will only return countries that have a population that exceeds 5,000,000.

## Aggregate Queries Have Limitations

It's important to note that while databases provide us with a computational powerhouse, there are limitations. When we're dealing with large volumes of data, we may need more computing power and time to perform various tabulations. That's where the field of "big data" comes into play. Big data or data science are disciplines that require the distributed processing of data, using methodologies such as [map reduce][map-reduce]. For our purposes and in most practical applications, SQL aggregations will get us very far.

## In Summary

Aggregate functions afford us with the ability to delegate a lot of math to the database. Databases are well-engineered to perform these computations, and we should always rely heavily on the Database Management System to do this work in our software.

[map-reduce]: https://en.wikipedia.org/wiki/MapReduce
