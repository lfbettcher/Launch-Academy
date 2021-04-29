We need to get an application server to host our Java servlets and JSPs. Tomcat is a free server which supports Java Servlet, JavaServer Pages, Java Expression Language and Java WebSocket technologies. We will be using Tomcat 9.

## Learning Goals

- Download and install Tomcat
- Configure Tomcat
- Run Tomcat and check the home page

## Why This Matters

Tomcat is a very popular application for hosting up many production systems. If you are going to get into Java, Spring, and JSPs, you will end up using it. Like Express in NodeJS, it is a mechanism by which we can handle HTTP requests and serve HTTP responses.

## Is Tomcat an Application Server or Web Server?

Interestingly, there's a lot of discussion about this distinction. Mainly, this is due to the existence of a more robust Java EE (Enterprise Edition) specification, which defines what a Java application server should do.

If we look at it from more of a technically agnostic perspective, Tomcat facilitates requests and serves dynamic responses, often integrating with other back-end infrastructure. Under that definition, it is an application server.

If we look at it from the Java community perspective, it does not fully implement the EE specification, and therefore is known as a servlet container.

## Tomcat for Windows

Download Tomcat's [service installer][windows-installer]. Follow the instructions to install Tomcat as a Windows service. (If there is any issue following the link please make sure you install version 9.4.45 any later version will not work with the curriculum).

## Installing Tomcat for the Mac

```no-highlight
brew install tomcat
brew link tomcat
brew services start tomcat
```

## Checking to see that Tomcat is Installed

Then, in your browser, go to `http://localhost:8080` and you should see the Tomcat startup page.

Once done, be sure to shut down your Tomcat service. You can do this on Mac as follows:

```no-highlight
brew services stop tomcat
```

On Windows, you'll want to find and kill the process running on port 8080. To do so, first find the process ID by running the following in your terminal:

```no-highlight
netstat -aon | findstr 8080
```

Then, take the process ID (PID) and run the below:

```no-highlight
taskkill /PID PIDNUMBERHERE /F
```

[windows-installer]: https://apache.osuosl.org/tomcat/tomcat-9/v9.0.45/bin/apache-tomcat-9.0.45.exe
