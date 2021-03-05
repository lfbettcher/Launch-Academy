import React from "react"

const Message = props => {
  const clickCount = 0

  return (
    <div>
      <h1>Message: {props.message}</h1>
      <h1>Click Count: {clickCount}</h1>
    </div>
  )
}

export default Message
