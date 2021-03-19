import React from "react";
import { Link } from "react-router-dom";

const ReadingNoteItem = (props) => {
  const { title, date, body } = props.note;
  return (
    <li>
      {date} {title} {body}
    </li>
  );
};

export default ReadingNoteItem;
