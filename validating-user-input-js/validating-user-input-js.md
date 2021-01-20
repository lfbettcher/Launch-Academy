It is a well-known fact of software development that somehow, some way, our users will use our applications in a way we do not intend for them to. The more we handle for possible errors, the smoother our user's experience will be. Now that we've started interacting with user input in our JavaScript code, let's take a look at how we can validate their input in our code!

### Learning Goals
- Discuss defensive programming
- Assess and validate input entered by the user in our JavaScript programs
- Use a `while` loop to provide helpful feedback to the user in case of error

### Why This Matters

Handling invalid user input smoothly is crucial in implementing a fluid user interface and an enjoyable user experience. User validation can help our application handle invalid user input without a hitch, so that our program doesn't crash. Additionally, giving the user clear guidance on what they need to do differently can allow them to continue using our application without frustration, even when they do something incorrectly!

### Getting Started

```no-highlight
et get validating-user-input-js
cd validating-user-input-js
open index.html
```

### Defensive Programming

Validating a user's input is a form of **defensive programming**. Defensive programming can protect our application any time a user takes an unexpected action! Defensive programming is all about accounting for the **edge cases**, or situations where our code does not work as expected.

Let's take a look at a sample application. When we open up our `index.html` file, we see a `prompt` box come up that asks us to enter a username. If we enter a username and hit "OK", our page greets the user, updating a paragraph tag to say "Hi there, _____!"

If we look in our `main.js` file, we see that it is the below JavaScript code which is creating this behavior:

```javascript
let username = prompt("Please enter a username of 6 characters or more.")

console.log(`Hi there, ${username}!`)
```

Assuming our user does everything exactly as we expect (fills in a username and hits "OK"), this functions fantastically. However, what happens if the user hits "Cancel" instead of "OK"?

![Screenshot of webpage with "Hi there, null!"][user-input-null]

This just won't do! By hitting "Cancel", the user managed to escape our intended user flow, and now our page is displaying `null` where the username can be.

Our solution is to utilize a `while` loop to continue requesting the proper information from our user until they provide us with valid input.

We can implement this by remembering that `null` is a falsey value, and updating our code to include the below logic:

```javascript
let username = prompt("Please enter a username of 6 characters or more.")

while(!username) {
  username = prompt(`Please click "OK" after entering your username`)
}

console.log(`Hi there, ${username}!`)
```

Now, when a user navigates to the page and hits "Cancel", it continues to ask them to try entering again, this time specifically telling them to click "OK" when they're done! We've given our user clear information on what they're doing wrong, so that they know how to fix it in order to proceed.

Go ahead and try it out by updating `main.js`, and running a *hard refresh* of your page to remove caching (`Shift` + `Command` + `R`).

### Providing Clear User Feedback

This is working fantastically. However, we have another edge case we may want to account for. What happens if our user navigates to the page, and clicks "OK" without entering a username? Try it out for yourself to find out.

What ends up happening is that our `while` loop starts to run. Let's pop a `debugger` in to take a closer look at things:

```javascript
while(!username) {
  debugger
  username = prompt(`Please click "OK" after entering your username`)
}
```

By hard refreshing our page and clicking "OK" immediately, we can take a look at the value of `username`. Here, we'll see it's `""`. Unfortunately, empty strings also calculate as falsey! We can double-check this by running `!!username` in our console during our debugger session to get the boolean value of that variable.

It seems we've run into another edge case: our user hit "OK", so we don't want to give them our existing error message. Instead, we want to clearly instruct them to enter a username again.

Let's update our `while` loop to account for these two separate situations.

```javascript
while(!username) {
  if(username === null) {
    username = prompt(`Please click "OK" after entering your username`)
  } else {
    username = prompt(`Please enter a username before clicking "OK"`)
  }
}
```

We've now provided two separate error messages to our user: one in the case of clicking "Cancel", and one for clicking "OK". All is looking great!

Our final edge case to account for has to do with the fact that we asked the user to input a username "of 6 characters or more". So far, we're not doing anything to make sure their username is long enough!

Our final update to our code will add another `while` loop to account for this situation:

```javascript
let username = prompt("Please enter a username of 6 characters or more.")
while(!username || username.length < 6) {
  if(username === null) {
    username = prompt(`Please click "OK" after entering your username`)
  } else {
    if(username.length === 0) {
      username = prompt(`Please enter a username before clicking "OK"`)
    } else {
      username = prompt(`Please be sure to enter a username of 6 characters or more.`)
    }
  }
}

console.log(`Hi there, ${username}!`)
```

In the above, we looped the check for 6 characters into the same `while` loop using the OR (`||`) syntax (as opposed to adding an additional `while` loop below the first one). This is important! This allows our code to account for the user entering a username of fewer than 6 characters on the first try, and then hitting "Cancel" on the second try.

We've done it! Our application is now handling all edge cases necessary to make sure we get the user input we need.

### In Summary

Clear error messages like those set up above make a user's experience on our application so much more enjoyable! By handling each edge case individually, we're able to let the user know exactly what they're doing wrong. We can use a `while` loop to account for each way we can imagine our user using our application incorrectly and provide clear error messages.

[user-input-null]: https://s3.amazonaws.com/horizon-production/images/article/validating-user-input-js/user-input-null.png
