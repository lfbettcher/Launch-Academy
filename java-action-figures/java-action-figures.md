Now that you've had some thyme to read about Thymeleaf, it's time to get on the clock and put that knowledge to use. You're going to create an action figure product catalog.

## Getting Started

```no-highlight
et get java-action-figures
cd java-action-figures
idea .
```

You have been provided with an index page and a show page for all products, complete with the relevant methods in your controller, model and service layers. Notice that your products are being stored in a session using a service. Your job is to build out the functionality to display and submit a form to add new products.

To get started, boot up your `spring-boot` server and navigate to <http://localhost:8080/products>. Click around to see the products and pages that have been provided to you. You'll notice that the link to "Add a New Product" is currently breaking.

### Add a New Product

There is rumbling around the office that your app might soon become a listing place for collectors to sell their figures. You now need to create a form for all those hoarders to list their original 1979 Tie Fighter.

Acceptance Criteria:

- When I navigate to `/products/new`, I am presented with a form to input a new product
- I must enter the product's name, price, and description
  - Use the appropriate input types for the data the user must supply
- Make sure your new product appears in the list upon submission, and that you can visit the show page

Implementation Details:
- First, build a `@GetMapping` to view your form at the `/products/new` path
- Build out your form to take in the attributes listed above
  - _Hint: you can use the `step` HTML attribute to ensure valid prices!_
  - Don't worry about server side validation
- Set up a `@PostMapping` to submit the new product
  - This mapping should use the `Product` model to create a new product object
    - _Hint: add an id to the product using the length of the array: `product.setId(service.getProducts().size());`. While this is a bit hacky before we get to databases, we need an `id` to access our show pages!
  - The mapping should then use the service to add our new product to the session
    - Make sure you add the relevant methods to your `ProductService` interface and `ProductSessionService` service class as well!

