import React from "react";
import Tweet from "./Tweet";

const TwitterFeed = (props) => {
  const tweetList = props.data.map((tweet) => {
    return <Tweet key={tweet.id_str} tweet={tweet} />;
  });

  return (
    <div className="grid-container">
      <div className="grid-x">
        <div className="cell small-1" />
        <div className="cell auto">{tweetList}</div>
        <div className="cell small-1" />
      </div>
    </div>
  );
};

export default TwitterFeed;
