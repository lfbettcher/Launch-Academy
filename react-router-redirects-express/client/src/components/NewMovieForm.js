import React, { useState } from "react";
import { Redirect } from "react-router-dom";

const NewMovieForm = (props) => {
  const [shouldRedirect, setShouldRedirect] = useState(false);
  const [movieTitle, setMovieTitle] = useState("");

  const onTitleChange = (event) => {
    setMovieTitle(event.target.value);
  };

  const postNewMovie = async () => {
    event.preventDefault();

    try {
      const response = await fetch("/api/v1/movies", {
        method: "POST",
        credentials: "same-origin",
        headers: new Headers({
          "Content-Type": "application/json",
        }),
        body: JSON.stringify({ title: movieTitle }),
      });
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`;
        const error = new Error(errorMessage);
        throw error;
      }
      console.log("Movie was added successfully!");
      // setMovieTitle("")
      const body = await response.json();
      if (body.errors) {
        // perhaps update a piece of state you've defined for errors,
        // then use that to display the errors on the form
      } else {
        setShouldRedirect(true);
      }
    } catch (err) {
      console.error(`Error in fetch: ${err.message}`);
    }
  };

  if (shouldRedirect) {
    return <Redirect to="/movies" />;
  }

  return (
    <form onSubmit={postNewMovie}>
      <label>
        Movie Title
        <input type="text" name="title" onChange={onTitleChange} value={movieTitle} />
      </label>
      <input type="submit" />
    </form>
  );
};

export default NewMovieForm;
