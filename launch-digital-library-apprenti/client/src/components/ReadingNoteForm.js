import React, { useState } from "react";
import _ from "lodash";

import ErrorList from "./ErrorList";

const ReadingNoteForm = (props) => {
  const [noteRecord, setNoteRecord] = useState({ title: "", body: "" });
  const [errors, setErrors] = useState([]);

  const validFormSubmission = () => {
    let submitErrors = {};
    const requiredFields = ["title", "body"];
    requiredFields.forEach((field) => {
      if (!noteRecord[field] || noteRecord[field].trim() === "") {
        submitErrors = { ...submitErrors, [field]: "is blank" };
      }
    });
    setErrors(submitErrors);
    return _.isEmpty(submitErrors);
  };

  const handleChange = (event) => {
    const { name, value } = event.currentTarget;
    setNoteRecord({ ...noteRecord, [name]: value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (validFormSubmission()) {
      props.addNewNote(noteRecord);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h1>Add a New Note</h1>
      <ErrorList errors={errors} />
      <label htmlFor="title">
        Title
        <input
          id="title"
          type="text"
          name="title"
          onChange={handleChange}
          value={noteRecord.title}
        />
      </label>

      <label htmlFor="body">
        Body
        <input id="body" type="text" name="body" onChange={handleChange} value={noteRecord.body} />
      </label>

      <input type="submit" value="Add this Note" />
    </form>
  );
};

export default ReadingNoteForm;
