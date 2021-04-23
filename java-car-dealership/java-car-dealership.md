You've been tasked with building the backend to manage a used car lot.

## Getting Started

```no-highlight
createdb car_dealership
et get java-car-dealership
cd java-car-dealership
idea .
```

Read all of the directions *at least twice* for meeting expectations prior to building your application. Also, be sure to review the code that has been provided to you carefully before getting started.

## Meets Expectations Requirements

### Add a Car

```no-highlight
As a car dealer
I want to enter a car
So that I can sell it on the lot
```

Acceptance Criteria:

- On the main menu of my application, when I select the option to add a car, I am prompted for the car's information
- I must enter a VIN, year, make, asking price, and model
- The VIN can be an arbitrary string between 5 and 20 characters. While these values should be unique on a database level, do not worry about validating on the model level for this. This should **not** be your primary key
- The asking price can be an integer between 500 and 50,000
- The year must be greater than 1980 and less than 2030
- If the car does not adhere to the requirements above, the user is provided with helpful errors to see what they did wrong. It is not added to the database, and I am sent back to the main menu

Implementation Details:

- Design your schema and write migrations to bring your schema to the required state
- Include appropriate schema constraints and JPA validations

### List Cars

```no-highlight
As a car dealer
I want to list cars in inventory
So that I can see what's on the lot
```

Acceptance Criteria:

- I can select an option from the main menu to list the cars in inventory
- Each car in inventory should be listed
- For each car, I should see the VIN, make, model, year, and asking price
- Cars should be listed by price in descending order (highest priced cars first)

## Exceeds Expectations Requirements

### Normalize the Makes

```no-highlight
As a car dealer
I want an easy way to organize cars by Make
So that I can search for options quickly
```

Acceptance Criteria:
- The `make` of each car must be normalized to a separate table, and referenced for cars through an association

Implementation Details:
- Be sure to update your existing `cars` table in your schema using a migration, as well as an additional migration for adding the `makes` table
- You will also need to update the logic for adding a new car to handle for this association
- While the schema changes should be made via additional migrations, feel free to drop and recreate your database in order to re-seed your data

### Search for a car

```no-highlight
As a car dealer
I want to search for a car based on VIN
So that I can manage the car
```

Acceptance Criteria:

- I can select an option from the main menu to search for a car by VIN
- When I select that option, I can enter the car's VIN I'm searching for
- If there is a car with that VIN in the inventory, I'm presented with a submenu to manage the car
- If there is not a car with that VIN in the inventory, I'm told the VIN is not found and I'm returned to the main menu

### Update the Car's Price

```no-highlight
As a car dealer
I want to update a car's price
So that I can make it a more attractive listing
```

- The car submenu provides an option to update the car's asking price. If I select that option, I'm prompted for a new price
- If I enter a valid price, the car's record is updated, and I'm returned to the main menu
- If I enter an invalid price, the car's record is not updated and I'm prompted to enter a valid price

### Delete the Car

```no-highlight
As a car dealer
I want to remove a car
So that it is no longer in inventory when sold
```

- The car submenu provides an option to delete the car from inventory. If I select that option, I'm asked to confirm
- Once I've confirmed, the car is removed, I receive a message letting me know the car has been deleted, and I'm returned to the main menu
- If I do not confirm, I'm returned to the main menu after being told that my action was canceled
