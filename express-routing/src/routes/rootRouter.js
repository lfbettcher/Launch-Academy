import express from "express";
import messagesRouter from "./messagesRouter.js";

const rootRouter = new express.Router();

rootRouter.use("/messages", messagesRouter);

export default rootRouter;
