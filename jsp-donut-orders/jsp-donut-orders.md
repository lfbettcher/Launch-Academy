In this challenge you will use your JSP knowledge to build a page with several functionalities in Java.

You have been provided with the necessary dependencies and plugins in your `pom.xml`. You have also been provided with the `WEB-INF/web.xml` file and a mostly-empty `index.jsp` file.

## Getting Started

```no-highlight
et get jsp-donut-orders
cd jsp-donut-orders
```

You have been tasked by your office to keep track of the number of orders needed for Donut Day (a Friday morning ritual that every company should embrace). We want a webpage to refer to so that we can know exactly how many donuts to buy and deliver to which employees, so let's use JSP and Tomcat to build a Java webapp!

## Core User Stories


```no-highlight
As a donut runner
I want to know what day I'm buying for
So that I buy the right number of donuts for today
```

Acceptance Criteria:

- When I visit `/` I see a header that says "Donut Orders"
- Using Java's date util, the current date is displayed below that header


```no-highlight
As a donut runner
I want to see a list of how many donuts to buy for each person
So that I can make sure to not skimp on treats
```

Acceptance Criteria:

- When I visit `/` I see a list of all employees who want donuts
- Next to each name is the number of donuts they want

Implementation Details:

- There should be an unordered list on the page
- Each person's name and number of donuts should be displayed in a list item


```no-highlight
As a donut runner
I want a total number of donuts to buy
So that I can double-check my purchase
```

Acceptance Criteria:

- I can see a total number of donuts below the list of employees


```no-highlight
As a donut runner
I want to make sure people don't order too many donuts
So that people don't take advantage of donut day
```

Acceptance Criteria:

- If a person is ordering 5 or fewer donuts, we should see their name and quantity on the page as normal
- If the person is ordering more than 5 donuts, we should _instead_ see a message saying that we should talk to them about not trying to game the donut system (and the perils of sugar crashes!)
- We can also add their name to the list with a modest 2 donuts for this week
- Be sure to update the "total" number of donuts to include the proper number!


```no-highlight
As a donut runner
I want to know what people's favorite donuts are
So I can try to surprise them with their favorites
```

Acceptance Criteria:

- If a person has a favorite donut, it should appear next to their donut quantity in the same list item, e.g.: "Alex: 2 Donuts (Blackberry Hibiscus are his favorite!)"
- If a person's favorite donut is not found, it should simply skip outputting their favorite

Implementation Details:

- Define a new HashMap which matches an employee's name to the name of their favorite donut
- Then, use this HashMap to add their favorite donut to the page

When you are done, if you like, refactor your `if` and `each` statements to use JSTL (you may need to read ahead!)