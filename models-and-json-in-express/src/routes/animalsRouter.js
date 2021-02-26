import express from "express";

import Animals from "../models/Animal.js";

const animalsRouter = new express.Router();

animalsRouter.get("/", (req, res) => {
  res.render("index", { animals: Animals.findAll() });
});

export default animalsRouter;
