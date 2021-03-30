import React from "react"
import _ from "lodash"

const ErrorList = ({ errors }) => {
  const errantFields = Object.keys(errors)
  if (errantFields.length > 0) {
    let index = 0
    const listItems = errantFields.map((field) => {
      index++
      return (
        <li key={index}>
          {_.capitalize(field)} {errors[field]}
        </li>
      )
    })
    return (
      <div className="callout alert">
        <ul>{listItems}</ul>
      </div>
    )
  }
  return ""
}

export default ErrorList