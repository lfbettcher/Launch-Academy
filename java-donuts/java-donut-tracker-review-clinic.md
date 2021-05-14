## Getting Started

```no-highlight
createdb donut_tracker
idea .
```

### List Your Donuts

```no-highlight
As a treat-lover
I want to see a listing of my favorite donuts
So that I can keep an itemized list
```

- When a user navigates to `/donuts` they should see a paginated list of donuts

### Create Your Donuts via Web Form

```no-highlight
As a treat-lover
I want to be able to enter donuts via a form
To keep track of my favorite donuts
```

- Create a `@GetMapping` to show your form
- Create a `@PostMapping` to persist user input
- Saving your donut successfully redirects the user to `/donuts`

### List Your Donuts via API

```no-highlight
As a treat-lover
I want to make my donuts available via an API
So everyone knows which ones are best
```

- Create a RestController for your donuts
- visiting `/api/v1/donuts` should display a JSON of your donuts

### Optional - Individual Donut Detail via API

```no-highlight
As a treat-lover
I want to be able to serve up a single donut
So that people can drool over each one in its own time
```

- visiting `/api/v1/donuts/{id}` should display a JSON for the donut with the matching ID
- ensure that the show endpoint has error handling and results in a 404 if the donut is not found
