## Introduction

Web browsers render pages consisting of HTML, CSS, and JavaScript, but how does the browser retrieve this content in the first place? In this article, we will set up a basic **web server** to host our pages.

## Getting Started

```no-highlight
et get http-and-express
cd http-and-express
yarn install
```

### Learning Goals

- Understand the role of a **framework** in software applications
- Build our first server in a simple web app with **Express**
- Manually send an HTTP GET request with the cURL command

### A Basic Web Page

Let's start with a simple HTML page representing a TODO list. In this assignment's project directory, we have provided a few files within the `public` folder: namely, an `index.html` file with a to-do list, and an `index.css` file with styling for our page. If we run `open public/index.html`, we should see a flashback to 1999, with a list of things to do on our page. However, we'll notice that the URL in our browser doesn't look like a typical web address! Instead, it will look something like: 

```no-highlight
file:///Users/myUserName/challenges/http-and-express/public/index.html
```

What's happening here?! Our computer opened a static HTML file using our browser: which means that our browser is loading the files directly from our file system, rather than accessing them via the web using an HTTP request and response, as we do with most web pages!

This is fine for testing out our web pages, but doesn't really work when we want to share our site with the world. What we can do instead is use a **web server** to host our files somewhere publicly accessible so others can view them.

### Hosting Pages

When we visit [http://www.google.com][google], we're communicating with a **web server** that will send back the HTML, CSS, and JavaScript for the Google homepage. A web server is a software application hosted on the web that listens for requests for web pages and will respond with the appropriate content. Every website has one or more web servers listening for requests, running various kinds of software that will determine how each request will be handled.

_Note: A web server can also refer to a physical machine running a web application, usually sitting in a data center somewhere with [lots of blinky lights][physical-server]. For this article when we talk about a web server, we're referring to the software, not the hardware._

On our own machine, we can set up a web server (which we could run locally and later push to be _live on the web!_) using the **Express** framework. There are many different frameworks used in the world of software development! We will be using Express because it is a lightweight framework written in JavaScript. For more information on the Express framework you can find the documentation [here][express-docs].

> **An Important Note on Frameworks:** A framework is packaged code that lays out the structure for what kind of programs can be built and how to build them. Frameworks provide us with some built-in functionality that we can build on top of, and give us guidelines for keeping our code organized and efficient. Instead of performing the same type of task again and again in our own code, we can use a framework that has those functions together in one nice package. With Express, rather than having to custom-write all of the code necessary to simply get a server up and running, we can use the pre-built functionality made available via _Express_ to boot up a server nice and quickly! 

### Adding the Express Framework

Let's use Express to create a very simple web application that will serve our HTML and CSS. Install Express with the following command:

```bash
yarn add express
```

We're also going to add some helpful logs to our Express app using a package called [Morgan][morgan-npm]. Let's install that package as well:

```bash
yarn add morgan
```

Now we can create our Express app in a file called `app.js`, which should be saved in our project's `src` directory:

```javascript
// src/app.js

import express from 'express'
import logger from 'morgan'
import path from 'path'
import { fileURLToPath } from 'url'

const __filename = fileURLToPath(import.meta.url)
const __dirname = path.dirname(__filename)

const app = express()

app.use(logger('dev'))
app.use(express.static(path.join(__dirname, '../public')))

app.listen(3000, '0.0.0.0', () => {
  console.log('Server is listening...')
})

export default app
```

Let's take a look at what's happening in this file. First, we are including the Express framework via an `import`. We then use this framework to create a new Express server using this line:

```javascript
const app = express()
```

This does the work of setting up a functional web server for us, without us having to write any code around the actual creation of the server itself! Now, it's our job to _configure_ that server to have the specific functionality we desire.

We attach our logging functionality using `morgan` and Express's built-in `use` function as so (we'll see those logs in action shortly):

```javascript
app.use(logger('dev'))
```

Finally, we need to let our Express server know that our static files are in the `public` folder, which happens in a few steps.

First, we use ES Module's `import.meta.url` to point at our current file's location and make it a path object using the helper function `fileURLToPath`, and then get the current directory using the `path.dirname` function. We do all of this to recreate the `__dirname` variable that was accessible with CommonJS syntax in Node (as opposed to the ES Module syntax we are using!). Finally, we go up one directory and over to our public directory using `../public` and provide that path for Express to set up our static routes. This isn't something you should have to custom-write all that often, but it's helpful to understand should you ever need to use it.

Then, we instruct the server to listen on port 3000, so that we can visit our page at `http://localhost:3000/index.html`. Express handles setting up a web server for us so there is not much else we need to do. It's worth mentioning that we are also binding the Express webserver to all network interfaces, so we can run this code within a Virtual Machine, if necessary.

To test out our web app we can start up the server with the following command:

```no-highlight
yarn run dev

Server is listening...
```

> **Note:** This command is running a script that lives under the `scripts` section of your `package.json` file. If you're curious what it's doing, you can look there! Additionally, just like we can run files directly using the `--inspect-brk` flag to use a debugger, we've also provided a debug script in your `package.json`. You can run this using `yarn run dev:debug`. You can always _change_ this script in `package.json` to use the `--inspect` flag if that's what you prefer.

To view our TODO list we can now visit <http://localhost:3000/index.html> which should return the same HTML and CSS we had before. This time, however, we'll see an actual URL in our browser! While it's currently loaded at a _host_ of `localhost:3000`, if we were to publish this code on the web, we could change that host to be something like `my-fancy-website.com`.

When we're done with our web server, we can terminate it in the terminal with `Ctrl` + `c`. If we want to start our server again, we can run it at any time with `yarn run dev`.

### Looking at our Logs

Boot up your server once more using `yarn run dev`, and let's take a look at the logs coming through.

When we first run our server, we'll see the below in our terminal:

```bash
yarn run dev

$ node src/app.js
Server is listening...
```

Our server is up and running, so let's visit <http://localhost:3000/index.html> again.  If we look at the output in our terminal, we'll see the following lines:

```no-highlight
Server is listening...
GET /index.html 200 12.173 ms - 274
GET /index.css 200 1.894 ms - 310
```

This is a little peek beneath the hood, via logs of HTTP requests that were received and handled by the web server! To review, **HTTP** is the protocol that web servers and their clients use to communicate. In this case, the client is typically a web browser. When we want to view a page, we enter the URL into our browser which then sends an **HTTP request** to the web server at the given address. The server receives the request and then returns with an **HTTP response** containing the content for that page (usually HTML, CSS, or Javascript). When the browser receives the HTML and CSS, it renders it in the window. Here, our server is saying that it received a request, and packages up the `index.html` and `index.css` files to send as a response, with a `200` successful status code.

### Using cURL

We've seen behind the scenes a bit thanks to our Express logs, but it would be nice to get an even more visible look of our HTTP request and response as we get used to conceptualizing what those things are. Thankfully, we have a tool called `cURL` with which we can do this.

**HTTP is a plain-text protocol**. It is both human and machine readable. An HTTP request is just a stream of characters sent over the network to a listening web server. Typically, our browser handles sending and receiving HTTP requests and responses in the background and translates them into a pretty web page, but `cURL` allows us to send this request and see the response via our terminal instead! If we call the `curl` command with a single argument, a URL, it will make an HTTP GET **request** to the web server. Then, the **response** from the web server will be printed to your screen.

For example, to get the `index.html` file from our Express app, **open a new terminal window**, and type the following command while your server is left running in the current terminal session.

```no-highlight
curl --request GET http://localhost:3000/index.html
```

This command will only show you the **body** of the response, and none of the associated HTTP metadata.

```no-highlight
<!DOCTYPE html>
<html>
  <head>
    <title>Basic HTML Page</title>
    <link rel="stylesheet" href="index.css" />
  </head>

  <body>
    <h1>TODO list</h1>

    <ul>
      <li>pay bills</li>
      <li>buy milk</li>
      <li>learn JavaScript</li>
    </ul>
  </body>
</html>
```

If you would like to see the response headers in addition to the response body, use the `--verbose` option.

```no-highlight
curl --verbose --request GET http://localhost:3000/index.html

*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to localhost (127.0.0.1) port 3000 (#0)
> GET /index.html HTTP/1.1
> Host: localhost:3000
> User-Agent: curl/7.64.1
> Accept: */*
>
< HTTP/1.1 200 OK
< X-Powered-By: Express
< Accept-Ranges: bytes
< Cache-Control: public, max-age=0
< Last-Modified: Tue, 08 Dec 2020 16:26:13 GMT
< ETag: W/"111-176432cb9cc"
< Content-Type: text/html; charset=UTF-8
< Content-Length: 273
< Date: Tue, 08 Dec 2020 17:04:20 GMT
< Connection: keep-alive
< Keep-Alive: timeout=5
<
<!DOCTYPE html>
<html>
  <head>
    <title>Basic HTML Page</title>
    <link rel="stylesheet" href="index.css">
  </head>

  <body>
    <h1>TODO list</h1>

    <ul>
      <li>pay bills</li>
      <li>buy milk</li>
      <li>learn JavaScript</li>
    </ul>
  </body>
* Connection #0 to host localhost left intact
</html>* Closing connection 0
```

The **response** is divided up into two sections: the response **headers** and the response **body**. 

Within the headers, we can see some pretty cool information, such as the `200 OK` status code, the `Content-Type` of "text/html", and the `Content-Length` of 273! Thanks to our `200` status code, we know that the server was able to successfully process the request. 

After the last header there is a blank line and then comes the response **body**. This is where the HTML we created earlier gets included. The contents of the HTTP response body should be identical to the contents of the `index.html` file in our `public` directory.

Notice that we didn't receive any CSS back. This is because we are keeping our CSS in an _external_ stylesheet that is linked to from our HTML page (`<link rel="stylesheet" href="index.css">` in the `<head>` section). To retrieve the stylesheet, we must issue a second HTTP GET request targeting the `/index.css` path instead of `/index.html` (this explains why we saw **two** `200` status responses in our Express logs earlier!)

When a browser receives this response, it can then apply these styles to the HTML it received earlier. **It is typical for a browser to send many HTTP requests to load a single page**. After getting back the initial response, a browser will search the HTML for any other resources that it needs to retrieve. External stylesheets, JavaScript files, images, and other assets each get their own HTTP requests.

Most modern browsers include a way to monitor the HTTP requests and responses going back and forth. In Chrome, clicking on _View > Developer > Developer Tools_ and opening the _Network_ tab will show all communication between the browser and the server. For Firefox the same information can be found in _Tools > Web Developer > Network_. Once the tool is running, visiting a new page will populate it with all of the requests being made. For example, visiting [http://www.google.com][google] resulted in over 20 requests being sent to load the home page with all of its assets!

### In Summary

In this article, we discussed the benefits of **frameworks**, and set up our first **server** using the JavaScript framework **Express**. We set up our server so that it would load static assets such as an `index.html` file which loaded at `http://localhost:3000/index.html`. In the future, we'll also set up different paths and write logic for what should happen at those paths, so that our user can visit a URL such as `http://localhost:3000/news` and get the latest news.

Using `cURL`, we sent a `GET` request to our server so that we could review the details of our server's response. We've only looked at the **HTTP GET** method which is used for _retrieving_ resources from a web server here, but there are a handful of other methods in use as well (e.g. **HTTP POST** for submitting data to a server) which we will be working with as we build out our web servers.

[express-docs]: https://expressjs.com/en/api.html
[google]: http://www.google.com
[google-no-www]: http://google.com
[matrix-style]: https://www.google.com/search?tbm=isch&q=the+matrix+style
[morgan-npm]: https://www.npmjs.com/package/morgan
[physical-server]: https://s3.amazonaws.com/horizon-production/images/data-center-servers-t001.jpg
[status-codes]: http://en.wikipedia.org/wiki/List_of_HTTP_status_codes
