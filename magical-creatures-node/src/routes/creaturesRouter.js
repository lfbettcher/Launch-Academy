import express from "express";
import MagicalCreature from "../models/MagicalCreature.js";

const creaturesRouter = new express.Router();

creaturesRouter.get("/", (req, res) => {
  res.render("creatures/index", { creatures: MagicalCreature.findAll() });
});

creaturesRouter.get("/:creatureName", (req, res) => {
  const { creatureName } = req.params;
  res.render("creatures/show", MagicalCreature.find(creatureName));
});

export default creaturesRouter;
