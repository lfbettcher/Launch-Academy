import React, { useState, useEffect } from "react";

import ReadingNoteItem from "./ReadingNoteItem";

const ReadingNotesList = (props) => {
  const [notes, setNotes] = useState([]);

  const getNotes = async () => {
    try {
      const response = await fetch(`/api/v1/books/${id}`);
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`;
        const error = new Error(errorMessage);
        throw error;
      }
      const responseBody = await response.json();
      setNotes(responseBody.notes);
      console.log(responseBody.notes)
    } catch (err) {
      console.error(`Error in Fetch: ${err.message}`);
    }
  };

  useEffect(() => {
    getNotes();
  }, []);

  let noteItems = []
 noteItems = notes.map((note) => {
      return <ReadingNoteItem key={note.id} note={note} />;
    });
  

  
  return (
    <div>
      <h3>My Notes</h3>
      <ul className="notes">{noteItems}</ul>
    </div>
  );
};

export default ReadingNotesList;
