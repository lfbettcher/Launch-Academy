## Learning Objectives

- Understand the role of external packages in web development
- Become familiar with `npm` and `yarn` package managers and why we like `yarn` best!
- Identify the roles of `node_modules`, `package.json`, and `yarn.lock`

### Getting Started

```no-highlight
et get node-packages
cd node-packages
code .
```

## Packages and Package Management

As developers, we often want our code to benefit from the amazingly vast collection of open source software on the web. We can do this by importing _packages_ provided by other developers into our applications.

Let's pause for some terminology, supported by a recipe metaphor:

- A **library** is a set of related modules that are used collectively to achieve a particular goal. If modules are like recipes, then libraries are like cookbooks containing recipes along a particular theme.
- A **package** is what we call it when the files comprising a library are bundled together to be downloaded. Continuing our cookbook metaphor, the package is the cardboard box of cookbooks that your vegetarian friend gives you when she finds out you're giving up meat.

Why are packages useful? Well, I _could_ write my own function to return only the unique values of an array, but I could make my life even easier by installing the [Lodash library][lodash] and using the `_.uniq()` method they've already written for me (we'll see _how_ to do this later on in this article). Meanwhile, if I'd like my app to upload files to the Amazon Web Services (AWS) cloud, I'm probably going to need to use the [official AWS software development kit library][aws-sdk], saving me the months of work it would take for me to create and maintain the same functionality myself.

When we install packages, we want to

1. download the package from the internet -- but only if the package is legitimate and safe
2. keep track of the specific _version_ of each package that our app requires
3. organize our app directory so that the package files are clearly separated from "our" code
4. easily import the functionalities into our other files

Package managers help us do all these things, so that we can kick back and focus on our awesome app!

Let's learn about the two most popular JavaScript package managers: `npm` and `yarn`.

### `npm`

`npm` (which used to, but no longer, stands for `Node package manager`) is

- a package registry: a directory of open-source JavaScript packages. Fun fact: it's the largest software registry in the world!
- an installation tool: a program that downloads, installs, and manages packages
- installed automatically when you install Node.js

`npm` is pretty awesome, but its installation tool aspect could be better. In particular, downloading packages can be slow, and the file in your app that `npm` uses to store detailed info about packages changes frequently and trivially, which is annoying.

If only there were a better way...

### Yarn

To address the shortcomings of `npm`, the engineering team at Facebook created [Yarn][yarn]. Yarn is considered a _wrapper_ for npm, meaning that Yarn itself utilizes `npm` for its core functionality and then adds features and improvements.

Whereas `npm` is both an installation tool and a package registry, Yarn is an installation tool that uses the `npm` package registry. With Yarn, you can install any package that is available on the npm registry.

#### Install Yarn

Let's start by installing Yarn. If you have already done this, you can skip this section:

```no-highlight
brew install yarn
```

To verify the install:

```no-highlight
 yarn --version
```

This should just tell you what version you installed. So long as returns a version number, it installed properly.

#### Getting Started with Yarn

Let's set up yarn in this article's directory.

```no-highlight
yarn init
```

You'll be prompted to enter additional information -- hitting `enter` will proceed with the defaults, which is fine for now. You should now have a `package.json` file in your project directory:

```json
{
  "name": "node-packages",
  "version": "1.0.0",
  "main": "index.js",
  "license": "MIT"
}
```

This file lists some basic info about our app but isn't terribly useful. We do need to make one small tweak to allow our app to run ESM modules; also, let's set `main` to `main.js`, since that's the main "entrypoint" into our app (the file that will run any other files).

```json
{
  "name": "node-packages",
  "version": "1.0.0",
  "main": "main.js", // changed this to main.js
  "license": "MIT",
  "type": "module" // we've added this line
}
```

Now that we have a `package.json` file, we can begin adding packages.

#### Installing and Uninstalling Packages with Yarn

There are a few different ways we might want to install a package:

- as a _runtime dependency_, meaning that the package is necessary for this particular application to run
- as a _development dependency_, meaning that the package is necessary for development or testing of this particular app but is not necessary to run the app
- _globally_, meaning that the package should be installed on the operating system level, rather than in your application's directory. This is necessary if you need to use the package outside the context of your application; for example, as terminal commands.

You'll use the commands below to add or remove packages:

| To perform this action... | Run this command... |
| --- | --- |
| Install a package as a runtime dependency | `yarn add name_of_package` |
| Install a package as a development dependency | `yarn add name_of_package --dev` |
| Install a package as a development dependency | `yarn global add name_of_package` |
| Uninstall a package | `yarn remove name_of_package` |
| Install all packages from a `package.json` provided to you | `yarn install` |

Now that we've seen the basic commands, let's put some to use! We'll start with the [lodash][lodash] JavaScript utility package mentioned previously. To install this package (as a runtime dependency, because it will be necessary for our code to run), we can run:

```no-highlight
 yarn add lodash
```

This command has made three notable changes to our application directory:

- `package.json` has been modified to include our new package
- a new file called `yarn.lock` was created
- a `node_modules` directory was created

Let's walk through these changes individually so we can understand what each file or directory is doing for us.

##### `package.json`

First, in **`package.json`**, `lodash` has been added under a new key, `dependencies`. The value next to the name `lodash` denotes the [semantic versioning][semantic-versioning] of the package.

```json
{
  "name": "node-packages",
  "version": "1.0.0",
  "main": "main.js",
  "license": "MIT",
  "type": "module",
  "dependencies": {
    "lodash": "^4.17.15"
  }
}
```

Other developers who downloaded your code can now easily install all of the project's dependencies by simply running `yarn install`.

##### `yarn.lock`

Second, a new file called `yarn.lock` has been created. Whereas the `dependencies` section of `package.json` provides a nice, easy-to-read listing of your packages and allowable versions, `yarn.lock` lists out every single dependency, its version, its location in the registry, and a hash used to validate the integrity of the package. This allows all developers to install the _exact same version_ of each package.

In real-world codebases, this file can be tens of thousands of lines long. If you'd like to start getting a sense of how this file grows with time, try installing the following packages: `aws-sdk`, `react`, and `express`. Luckily, you won't need to refer to this file often!

##### `node_modules`

Finally, your application's root directory now includes a `node_modules` directory, which contains a `lodash` folder containing all the code for the `lodash` package. Any changes you make to these files will only change the packages for **you** in this application on your local machine, and not for any other users of the package. As such, you should **never** modify any of these files, because other developers working on your project won't receive your changes when they run `yarn install`.

If you're using an open-source library and find a bug or want to improve something for everyone, you can [contribute][open-source-contribute]!

### External Libraries in Action

Now that we've installed `lodash`, let's use it!

```javascript
// main.js
import _ from 'lodash'

let result = _.uniq([1, 1, 0])
console.log(result)
// [1, 0]
```

The syntax for importing an external library is just like the syntax for importing a module you've written yourself, except that we hand it the name of the library rather than a file path. We can now use the [`.uniq`][lodash-uniq] function, which removes all duplicated values from an array.

### Summary

In this article, we learned a bit about packages and how to install and manage them using `yarn`. We then saw how to use a function from a library that we imported.

[lodash]: https://lodash.com/
[lodash-uniq]: https://lodash.com/docs/4.17.15#uniq
[aws-sdk]: https://www.npmjs.com/package/aws-sdk
[semantic-versioning]: https://www.jvandemo.com/a-simple-guide-to-semantic-versioning/
[yarn]: https://yarnpkg.com/en/
[open-source-contribute]: https://opensource.guide/how-to-contribute/
