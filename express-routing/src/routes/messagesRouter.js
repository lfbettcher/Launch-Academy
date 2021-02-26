import { RSA_NO_PADDING } from "constants";
import express from "express";
import fs from "fs";

const messagesRouter = new express.Router();

const messagePath = "messages.txt"

const getMessages = () => {
  return fs
   .readFileSync(messagePath)
   .toString()
   .split("\n")
}

messagesRouter.get("/", (req, res) => {
  res.contentType("text/html").send(
    getMessages()
      .map(message => `<br/>${message}`)
      .join("\n")
  )
})

export default messagesRouter;
