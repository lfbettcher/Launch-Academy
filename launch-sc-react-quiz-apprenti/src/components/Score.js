import React, { useState } from "react";

const Score = (props) => {
  const { correctCounter, incorrectCounter, correctIds } = props;
  return (
    <div>
      <p>
        Correct:
        {correctCounter}
      </p>
      <p>
        Incorrect:
        {incorrectCounter}
      </p>
      <p>
        Correct Ids:
        {correctIds.toString()}
      </p>
    </div>
  );
};

export default Score;
