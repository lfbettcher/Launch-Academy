You've been tasked with making a quiz application in React!

## Setup

From your challenges directory, run the following:

```no-highlight
et get launch-sc-react-quiz-apprenti
cd launch-sc-react-quiz-apprenti
yarn install
yarn run start
```

## Meets Expectations Requirements

**NOTE**: You are not expected to replicate the answer order shown in the wireframes below! That is, don't worry about reordering the answers so that `React` is first, `Ember` is second, etc. -- display them in whatever order is easiest.

1. Using React and the data supplied in `src/constants/data.js`, render a static page displaying the question and its possible answers. **You may not modify the data in `data.js`**.
   - On the initial render, the page should not display either 'Correct!' or 'Incorrect' ![React Quiz 1][react-quiz-1]
2. Clicking on a correct answer updates the page to display `Correct!` below the answers. ![React Quiz 2][react-quiz-2]
3. Clicking on an incorrect answer updates the page to display 'Incorrect' below the answers. ![React Quiz 3][react-quiz-3]
4. Once a button is selected, the button should change color. Check out `src/main.scss` to see if there are any provided classes to help you out!

Additionally, you need only style your app to make the button change color.

### Tips

- Make an outline or plan for how you want to construct your app before beginning.
- We encourage you to first write the code necessary to make the page _look_ the way it is supposed, and then add the behavior and functionality of the page after that.
- Read your errors carefully. They will often point you directly to the offending piece of your code.
- Check your React DevTools often to ensure that the props are what you expect
- Putting a `debugger` at the top of your components to inspect props may help with this, and can be a huge benefit to ensure you are hitting conditionals or `onClicks`
- Look at past assignments for inspiration, but know that this assignment may be different.

## Exceeds Expectations Requirements

**Stop and `et submit` your work _right now_**, leaving a comment on the file in Horizon indicating that it is your Meets submission. The Exceeds Expectations portion will break your Meets Expectations code.

* Change the data you're importing from `data.js` to `exceedsData.js`
* Update your app to map over the questions provided and display each question on the screen
* Each question should only update when one of its answers is clicked
* Clicking a selected answer should unselect it

* Create a new component which keeps track of how many correct answers have been selected
* The component should display a count of correct answers
* It should display the ids of each question that is currently marked correct
* It should display a count of how many incorrect guesses have been made in total since the user began the quiz 




  ![React Quiz 5][react-quiz-5]

[localhost-3000]: http://localhost:3000
[react-quiz-1]: https://s3.amazonaws.com/horizon-production/images/react-quiz-1.png
[react-quiz-2]: https://s3.amazonaws.com/horizon-production/images/react-quiz-2.png
[react-quiz-3]: https://s3.amazonaws.com/horizon-production/images/react-quiz-3.png
[react-quiz-4]: https://s3.amazonaws.com/horizon-production/images/react-quiz-4.png
[react-quiz-5]: https://s3.amazonaws.com/horizon-production/images/react-quiz-5.png
