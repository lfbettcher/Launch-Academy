import React from "react";

import MessageComponent from "./MessageComponent";

const MessageList = (props) => (
  <div>
    <MessageComponent message="Hello from main.js" />
    <MessageComponent message="Do you perchance have any brussels sprouts?" />
    <MessageComponent message="The lack of cruciferous vegetables displeases me." />
  </div>
);

export default MessageList;
