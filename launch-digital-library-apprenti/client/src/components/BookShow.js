import React, { useState, useEffect } from "react";
import ReadingNoteForm from "./ReadingNoteForm";
import ReadingNotesList from "./ReadingNotesList";

const BookShow = (props) => {
  const [book, setBook] = useState({});

  const getBook = async () => {
    try {
      const bookId = props.match.params.id;
      const response = await fetch(`/api/v1/books/${bookId}`);
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`;
        const error = new Error(errorMessage);
        throw error;
      }
      const responseBody = await response.json();
      setBook(responseBody.book);
    } catch (err) {
      console.error(`Error in Fetch: ${err.message}`);
    }
  };

  useEffect(() => {
    getBook();
  }, []);

  const addNewNote = async (noteRecord) => {
    try {
      const response = await fetch(`/api/v1/books/${props.match.params.id}/reading-notes`, {
        method: "POST",
        headers: new Headers({
          "Content-Type": "application/json",
        }),
        body: JSON.stringify(noteRecord),
      });

      if (!response.ok) {
        if (response.status === 422) {
          const body = await response.json();
          return setErrors(body.errors);
        }
        const errorMessage = `${response.status} (${response.statusText})`;
        const error = new Error(errorMessage);
        throw error;
      } else {
        const body = await response.json();
        console.log("Posted successfully!", body);
      }
    } catch (err) {
      console.error(`Error in fetch: ${err.message}`);
    }
  };

  let descriptionSection;
  if (book.description) {
    descriptionSection = <p>{book.description}</p>;
  }

  let fictionSection;
  if (book.fiction == true) {
    fictionSection = (
      <p>
        <em>This book is fictional.</em>
      </p>
    );
  }

  return (
    <>
      <h1>{book.title}</h1>
      <h2>{book.author}</h2>
      <h3>{book.pageCount} pages</h3>
      {descriptionSection}
      {fictionSection}
      <ReadingNotesList />
      <ReadingNoteForm addNewNote={addNewNote} />
    </>
  );
};

export default BookShow;
