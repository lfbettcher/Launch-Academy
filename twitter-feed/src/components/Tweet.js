import React, { useState } from "react";

const Tweet = (props) => {
  const { text, user, retweet_count, favorite_count, liked, retweeted, timestamp_ms, entities} = props.tweet;
  const [likedBool, setLikedBool] = useState(liked);
  const [likeCount, setLikeCount] = useState(favorite_count);
  const [retweetCount, setRetweetCount] = useState(retweet_count)
  const [retweetedBool, setRetweetedBool] = useState(retweeted);
  const date = new Date(parseInt(timestamp_ms)).toString().split(" ")

  let tweetText = text;
  let tweetImage;
  if (entities.media) {
    const { media_url, display_url } = entities.media[0];
    tweetText = tweetText.replace(display_url, "");
    tweetImage = <img src={media_url} />;
  }

  const handleClick = (event) => {
    const icon = event.target.classList[0]
    alert(icon)
    if (icon === "like") {
      event.target.parentElement.classList.toggle("like-red");
      setLikedBool(!likedBool);
      setLikeCount(!likedBool ? likeCount + 1 : likeCount - 1)
    } else if (icon === "retweet") {
      event.target.parentElement.classList.toggle("retweet-green");
      setRetweetedBool(!retweetedBool);
      setRetweetCount(!retweetedBool ? retweetCount + 1 : retweetCount - 1);
    }
  };

  const showLike = likeCount > 0 ? likeCount : "";
  const showRetweet = retweetCount > 0 ? retweetCount : "";
  const likeClass = likedBool ? " like-red" : "";
  const retweetClass = retweetedBool ? " retweet-green" : "";

  return (
    <div className="tweet grid-container">
      <div className="grid-x">
        <div className="cell small-2">
          <img className="profile-pic" src={user.profile_image_url} alt="profile pic" />
        </div>
        <div className="cell small-10">
          <div className="grid-y">
            <div className="cell small-1">
              <p>
                {user.name} <span className="gray">@{user.screen_name} · {date[1]} {date[2]}</span>
              </p>
            </div>
            <div className="cell small-10">
              <p>{tweetText}</p>
              {tweetImage}
            </div>
            <div className="cell small-1 icons">
              <div className="grid-container">
                <div className="grid-x">
                  <div className="cell small-2">
                    <i className="reply fas fa-reply" onClick={handleClick} />
                  </div>
                  <div className={"cell small-2" + retweetClass}>
                    <i className="retweet fa fa-retweet" onClick={handleClick} />
                    <span className="gray">{showRetweet}</span>
                  </div>
                  <div className={"cell small-2" + likeClass}>
                    <i className="like fa fa-heart" onClick={handleClick} />
                    <span className="gray">{showLike}</span>
                  </div>
                  <div className="cell small-2">
                    <i className="more fas fa-ellipsis-h" onClick={handleClick} />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Tweet;
