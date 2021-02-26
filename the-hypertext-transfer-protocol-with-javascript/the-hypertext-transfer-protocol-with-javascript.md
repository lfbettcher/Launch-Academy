## Introduction

The **HyperText Transfer Protocol** is the protocol of the web. As its name
implies, **it was created to transfer HTML documents from servers to clients.**
As web developers, we should take the time to study the details of what this
protocol can do for us.

In this lesson, we will explore some of the common features of HTTP that we
should know. The first item we will cover, is the **URL**.

## Getting Started

```no-highlight
et get the-hypertext-transfer-protocol-with-javascript
cd the-hypertext-transfer-protocol-with-javascript
yarn install
```

### Learning Goals

- Understand the request-response nature of HTTP
- Identify the different pieces of a URL
- Review the different parts of an HTTP request and response
- Recognize the different HTTP response codes 

## Uniform Resource Locator (URL)

As users of the web, we are accustomed to visiting websites via the following
process:

1. Open up your browser of choice.
2. Type in the web address (or URL) you want to see into the address bar (eg- `reddit.com`).
3. The page (or _resource_) loads, and you start reading.

### URL Basics

In order to get anywhere on the web, we need to know the **location** of the
document, or resource, we want to retrieve. This is specified by a **URL**:

```no-highlight
          hostname
         ┌───┴────┐
 https://reddit.com/r/gifs
 └─┬─┘             └──┬──┘
 scheme              path
```

[Source](https://en.wikipedia.org/wiki/Uniform_Resource_Identifier#Examples)

As we can see, a URL can be broken down into the following parts:

* **scheme** - The scheme helps us to determine the **protocol** which we will
  use to make a request. In this case,
  [HTTP Secure](https://en.wikipedia.org/wiki/HTTPS). Some other schemes we
  could use here are `ftp`, `mailto`, or `news`.
* **hostname** - The name which is used to identify the
  [Host](https://en.wikipedia.org/wiki/Hostname), or **server** of the
  information we wish to retrieve. Behind the scenes, this hostname must be
  translated to an IP address.
  [DNS servers](https://en.wikipedia.org/wiki/Domain_Name_System)
  handle this process.
* **path** - This indicates to the server the **resource** we wish to retrieve.

### URL Query String Parameters

Consider this example URL generated when performing a search via Google.

```no-highlight
             hostname          query string
            ┌───┴────┐       ┌──────┴───────┐
    https://google.com/search?q=cats&tbm=isch
    └─┬─┘             └──┬──┘
    scheme              path
```

The **query string**, aka **params**, is a series of key/value pairs, which
allows the client to pass some information to the server. In many cases, this
information is usually provided by the user by submitting a form. In this
specific case, the **query string** indicates that we have performed an image
search for `cats`.

| key                  | value               |
| -------------------- | ------------------- |
| q (query)            | cats                |
| tbm (type of search) | isch (image search) |

More on **query string parameters**, [here](https://en.wikipedia.org/wiki/Query_string)

### URL Fragments

```no-highlight
                hostname                              fragment
            ┌───────┴──────┐                         ┌───┴───┐
    https://en.wikipedia.org/wiki/Fragment_identifier#Examples
    └─┬─┘                   └───────────┬───────────┘
    scheme                            path
```

Fragments allow us to jump to an `id` within a document. This is useful when
the information you want someone to view is a few pages down within a HTML
document. Try visiting the above URL in your browser.

More on **fragments**, [here](https://en.wikipedia.org/wiki/Fragment_identifier)

## Parsing URLs

Dealing with URLs is such a common occurrence that Node has provided the
[URL class][node-url-class] for working
with URLs.

```javascript
import Url from "url"

const url = Url.parse("https://reddit.com/r/gifs")
console.log(url.protocol)
console.log(url.hostname)
console.log(url.path)
```

**Quick Challenge:** Read the documentation for the
[URL library][node-url-class]
and attempt to parse and extract the URL components of the previous examples.

## The HTTP Request/Response Cycle

![HTTP Request/Response Cycle](https://s3.amazonaws.com/horizon-production/images/http-request-response-cycle.png)

If we type `reddit.com/r/gifs` into a web browser, it takes us to an index page
(a list), for [animated gifs](https://reddit.com/r/gifs/). Behind the scenes,
your browser is issuing a HTTP `GET` **request**, to a host (`reddit.com`), for
the `/r/gifs` resource. The **response** from the host, will include the HTML
representation of that `/r/gifs` resource, plus some extra information.

## The HTTP Request

One interesting thing to note about HTTP is that it is a plain-text protocol.
It is both human _and_ machine readable.

#### Example

```no-highlight
GET /r/gifs HTTP/1.1
Host: reddit.com
```

This is the minimal amount of information we need to provide in order to
retrieve a resource via HTTP. Typically, your browser will add on
[many more headers](https://en.wikipedia.org/wiki/List_of_HTTP_header_fields#Request_fields),
so that the server can know more about the client making the request.

Let's decompose the components of this **HTTP request**

#### Components of a HTTP Request

1. HTTP Verb: `GET`
2. Path: `/r/gifs`
3. Protocol and version: `HTTP/1.1`
4. Headers: `Host: reddit.com`

#### HTTP Verbs and RESTful Web Applications

From a high level view, HTTP allows us to work with data (or a resource) over a
network. Typically, the interactions we would like to perform are the creation
of new data, reading existing data, modifying or updating data, or the deletion
of data. `CRUD` is the acronym we use for manipulating a resource. The mapping
of HTTP verbs to CRUD actions allows us, as web developers, to have a common
language for working with data over a network such as the Internet.

| HTTP Verb | CRUD action |
| --------- | ----------- |
| POST      | Create      |
| GET       | Read        |
| PUT/PATCH | Update      |
| DELETE    | Delete      |

This mapping of HTTP verbs to CRUD actions is a core concept of creating
[RESTful web applications](http://www.restapitutorial.com/lessons/restquicktips.html),
a concept we will explore in depth in the coming lessons.

## The HTTP Response

```no-highlight
HTTP/1.1 200 OK
Content-Type: text/html
Last-Modified: ...

<!DOCTYPE html>
...
```

#### Components of a HTTP Response

1. Protocol and version: `HTTP/1.1`
2. Status code and message: `200 OK`
3. Headers: `Content-Type: text/html`
4. Body: `<!DOCTYPE html>`

If the client issues a valid request, the server should return a **status code**
in the 200-299 range, as well as the resource the client requested in the body
of the response.

#### Status Codes

It isn't necessary to memorize all of the HTTP status codes, but they are
massively useful when troubleshooting. No doubt, you are familiar with the
`404 Not Found` message. This indicates that the client requested a resource
that the server could not find (or doesn't exist).

Here is a general overview of HTTP Status Codes. When you encounter a specific
status code, be sure to [look it up](https://http.cat/).

* **100-199**: _Informative_
* **200-299**: _Successful_
  - 200 OK
    - The typical response for any `GET` requests
  - 201 Created
    - The typical response for any `POST` requests, e.g. submitting a form on a web page
* **300-399**: _Redirect_
  - 301 Moved Permanently
  - 303 See Other
* **400-499**: _Client Error_ - Bad HTTP Request
  - 401 Unauthorized
    - Occurs if you perhaps need to log in to view a page, or need to have special permissions to view
  - 404 Not Found
    - Occurs if you try to visit a page that does not exist
* **500-599**: _Server Error_ - Server Crashed
  - 500 Internal Server Error
    - Occurs if an error is raised within your server (often thought of as a developer error!)
  - 503 Service Unavailable
    - Often occurs if a server is overloaded with requests or undergoing maintenance

### Content Types

The default **Content Type** for a HTTP response is `text/plain`. However, the
HTTP protocol can handle any type of file. We can usually identify a file by its
extension or suffix.

For instance, if I make a request for
`https://s3.amazonaws.com/horizon-production/images/http-request-response-cycle.png`,
I expect to get an image file. The file extension indicates that it is a
[Portable Networks Graphics](https://en.wikipedia.org/wiki/Portable_Network_Graphics)
file, which are very common on the web.

Try out the following in a node script (we've supplied this snippet in the `code.js` file that accompanies this lesson):

```javascript
//request is an npm package, so don't forget to `yarn install`
import request from "request"
import fs from "fs"

request(
  "https://s3.amazonaws.com/horizon-production/images/http-request-response-cycle.png",
  { encoding: null },
  function(error, response, body) {
    console.log(response.statusCode)
    console.log(response.headers["content-type"])
    fs.writeFileSync("image.png", body)
  }
)
```

You just downloaded an image _without_ using a browser!

The "content-type" field in the **header** tells the browser how it should
render the body of the response.

Other content types we can expect to see are [JSON](https://en.wikipedia.org/wiki/JSON)
and [XML](https://en.wikipedia.org/wiki/XML). More on those data formats in
coming lessons.

But how did that happen?  Honestly, we don't have to get too deeply into it, but let's break it down real quick:

 * `request`: a third party npm package that issues an http request to retrieve the image from its online source.
 * `fs.writeFileSync`: Takes the newly acquired image and, in this case, writes it to a certain file.

## In Summary

HTTP is the protocol we use to Create, Read, Update, and Delete resources on the
Internet. This lesson has defined many of the terms and ideas we will use as we
learn more about writing applications for the Web.

So far we've covered the pieces of a URL, the four different HTTP verbs or _methods_ (`GET`, `POST`, `PUT`/`PATCH`, and `DELETE`), and a handful of the different [HTTP responses][status-codes] we might receive. This request-response protocol is fundamental to how HTTP works and how data is typically transferred over the web.

An HTTP request consists of a **method** and a **path** that specifies what action we want to take,  along with any additional headers (e.g. `Host: www.google.com`).

An HTTP response contains a header and (usually) a body. The header includes a **response code** that indicates the status of the request (e.g. **200 OK** if everything went well, **404 Not Found** if the resource doesn't exist, etc.). The body contains the content that was requested which could be HTML, CSS, JavaScript, or any other type of data.

You will likely need to reference this lesson when describing the details of the HTTP request-response cycle. Your understanding of HTTP will improve your comprehension of web technologies such as Express, React, and the Fetch API. It will also prepare you for questions that you will likely receive about web applications during technical interviews or conversations with other developers!

[node-url-class]:https://nodejs.org/api/url.html
