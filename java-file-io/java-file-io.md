# Java File IO

So far, we've been reading and writing from Standard IO. Generally, this means we're outputting data to the end user of a Command Line Interface (CLI), and prompting that same user for input.

Together let's start working with files to broaden the capabilities of our programs.

## Learning Goals

- Read from a file programmatically
- Write to a file programmatically

## Getting Started

```no-highlight
et get java-file-io
cd java-file-io
idea .
```

## Working with Files

Java has an abstraction for files called [File](file-docs). You can create an instance of a file with a name of a file or directory. It allows you to check the existence of the file, get the path of the file, and if you assign it to a directory, get a list of all the files and directories under that directory. It works with the File IO classes. Just add it to the constructor.

```Java
import java.io.File;

public class ExistsDemo {
  public static String BOOK_READ_PATH = "./booklist.txt";

  public static void main(String[] args) {
    File bookList = new File(BOOK_READ_PATH);
    System.out.println(bookList.exists());
    System.out.println(bookList.getAbsolutePath());
  }
}
```

Here we're checking whether the file exists and returning the output to the CLI with a true or false. Then, we print out where the file is located in the filesystem.

Run this program with:

```bash
javac ExistsDemo.java
java ExistsDemo
```

## Reading Data From a File

### Input Streams

Now that we have a way to access the file, let's read its contents. In order to do so, Java provides input streams to read binary data. [InputStream](input-stream-docs) is a class that all the input streaming classes derive from. For example, if you have an array of bytes that holds data you want to parse, you can create a [ByteArrayInputStream](byte-array-input-stream-docs) to read the array. [FileInputStream](file-input-stream-docs) reads data from a file.

Now let's try to read the first character in the `booklist.txt` file using the `InputStreamDemo` class:

```Java
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;

public class InputStreamDemo {
  public static String BOOK_READ_PATH = "./booklist.txt";

  public static void main(String[] args) throws IOException {
    File bookList = new File(BOOK_READ_PATH);
    InputStream iStream = new FileInputStream(bookList);
    int c = iStream.read();
    System.out.println((char)c);
  }

}
```

Notice how we must read in the character as an integer. We can use the `(char)c` to **cast** the `int` instance and transform it into a `char`. We can't necessarily cast any arbitrary type to another, but translating between `int`, `byte`, and `char` in such a way is a normal occurrence when programming with Java.

### Readers

Just like with InputStreams, Java also has a class called [Reader](reader-docs). You can use children of this class to to read text (and only text) files. As with InputStream, there are multiple subclasses that deals with different types of reading. [BufferedReader](buffered-reader-docs) is used to read characters while buffering the input for a more efficient reading of the data. Remember that loading the entire contents of large files into memory is expensive, and can result in scalability issues. We can use `BufferedReader` to read one segment of the file in at a time to solve that issue.

Let's modify our `InputStreamDemo` to use a `BufferedReader`:

```Java
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputStreamDemo {
  public static String BOOK_READ_PATH = "./booklist.txt";

  public static void main(String[] args) throws IOException {
    File bookList = new File(BOOK_READ_PATH);
    InputStream iStream = new FileInputStream(bookList);
    BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
    String line = br.readLine();
    System.out.println(line);
  }
}
```

Notice how we have to instantiate so many objects to facilitate this operation. As we've mentioned, Java is philosophically very object oriented, and the maintainers of Java, want to provide you with a series of options. Let's take a closer look at the objects at play.

- `File` - we use this to represent the concept of the file that exists on our computer. In this case, the file that contains our list of books.
- `FileInputStream` - think of this like the application's connection to the `File`. It allows us to control how we read in the file's contents.
- `InputStreamReader` - the input stream we want the `BufferedReader` to read from, in segments.
- `BufferedReader` - we use this instance to read from the `FileInputStream`, one line at a time.
- `String` - finally, we have a line from the file's contents that we can work with.

Phew! That's a lot of objects at play. Thankfully, there's a simpler way.

### Using a Scanner

Similar to our standard input/output, we can use a `Scanner` to help us simplify this syntax. Let's take a look at the supplied `ScannerDemo` class.

```java
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class ScannerDemo {
  public static String BOOK_READ_PATH = "./booklist.txt";

  public static void main(String[] args) throws IOException {
    File bookList = new File(BOOK_READ_PATH);
    String content = new Scanner(bookList).useDelimiter("\\n").next();
    System.out.println(content);
  }
}
```

Here, we're still using the `File` instance, but the `Scanner` class provides us with an easier means to read in one line at a time. This is the promise of object oriented programming at play. If we want granular control, we can use `FileInputStream` and `BufferedReader`. For most use cases, we can use a scanner like the example above.

### Reading the Entirety of a File

If we know the size of our file will be small, we can read in the entirety of our file using a similar syntax. To accomplish this, we use `\\z` delimiter instead of the new line delimiter `\\n`. The special `\z` character designates the end of the file, so we can use it to read until we reach the end of the file.

```java
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class ScannerDemo {
  public static String BOOK_READ_PATH = "./booklist.txt";

  public static void main(String[] args) throws IOException {
    File bookList = new File(BOOK_READ_PATH);
    String allContent = new Scanner(bookList).useDelimiter("\\z").next();
    System.out.println(allContent);
  }
}
```

## Writing to a File

When writing to a stream, we can work with subclasses of [Writer](writer-docs), which writes characters to a file or data structure. [BufferedWriter](buffered-writer-docs) writes characters to a buffer. [FileWriter](file-writer-docs) writes characters to a file. Let's put `FileWriter` to work.

We've supplied the `WriteToFileDemo` with this article class for our studying.

```Java
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class WriteToFileDemo {
  public static final String GREETING_PATH = "./greeting.txt";
  public static final String GREETING_TO_WRITE = "Hey there!";

  public static void main(String[] args) throws IOException {
    File fileToCreate = new File(GREETING_PATH);
    OutputStream oStream = new FileOutputStream(greetingFile);
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(oStream));
    bw.write(GREETING_TO_WRITE);
    if (fileToCreate.exists()) {
      System.out.println("File created.");
    }
    bw.close();
  }
}
```

If we look at the resulting `greeting.txt` file, we'll see that we've populated it with the contents `"Hey there!"`.

## A Simpler Write

Thankfully, there's an easier way to write that manages much of these internals.

```java
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterSimple {
  public static final String GREETING_PATH = "./greeting.txt";
  public static final String GREETING_TO_WRITE = "Hey there!";

  public static void main(String[] args) throws IOException {
    File fileToCreate = new File(GREETING_PATH);
    FileWriter fileWriter = new FileWriter(fileToCreate);
    fileWriter.write(GREETING_TO_WRITE);
    fileWriter.close();

    if (fileToCreate.exists()) {
      System.out.println("File created.");
    }
  }
}
```

## Why This Matters

Understanding how Java handles something as simple as reading and writing files will help you to understand some of design decisions the creators of Java made. In writing Java applications, we often get lower level access to abstractions than what we may see in programming languages like NodeJS or Ruby (in this case, meaning that we interact with more classes directly).

## In Summary

We can read and write strings from and to files, programmatically. Java provides us with a great deal of granularity in facilitating how we accomplish both operations. For reading, we most often use a `Scanner` instance. For writing, we employ a `BufferedWriter`.

[file-docs]: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/File.html
[input-stream-docs]: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/InputStream.html
[byte-array-input-stream-docs]: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/ByteArrayInputStream.html
[file-input-stream-docs]: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/FileInputStream.html
[reader-docs]: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Reader.html
[buffered-reader-docs]: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/BufferedReader.html
[writer-docs]: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Writer.html
[buffered-writer-docs]: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/BufferedWriter.html
[file-writer-docs]: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/FileWriter.html
