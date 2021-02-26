import express from "express";
import magicalCreaturesRouter from "./magicalCreaturesRouter.js";
import creaturesRouter from "./creaturesRouter.js";

const rootRouter = new express.Router();

rootRouter.get("/", (req, res) => {
  res.render("index");
});

rootRouter.use("/magical-creatures", magicalCreaturesRouter);
rootRouter.use("/creatures", creaturesRouter);

export default rootRouter;
