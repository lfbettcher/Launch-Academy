import React from "react"
import { render } from "react-dom"

import App from "./components/App"
import config from "./config"
import RedBox from "redbox-react"

import data from "./constants/data"

document.addEventListener("DOMContentLoaded", () => {
  let reactElement = document.getElementById("app")

  if (reactElement) {
    if (config.env === "development") {
      try {
        render(<App data={data} />, reactElement)
      } catch (e) {
        render(<RedBox error={e} />, reactElement)
      }
    } else {
      render(<App data={data} />, reactElement)
    }
  }
})
