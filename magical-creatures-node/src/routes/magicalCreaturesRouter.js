import express from "express";

// `creatures` variable should return to you the creature objects that you need!
import creatures from "../creatures.js";

const fields = ["title", "url", "description"];

const magicalCreaturesRouter = new express.Router();

magicalCreaturesRouter.get("/", (req, res) => {
  res.render("creatures/index", { creatures: creatures || [] });
});

magicalCreaturesRouter.get("/:creatureName", (req, res) => {
  const { creatureName } = req.params;
  const creatureData = creatures.find(creature => creature.name === creatureName);
  res.render("creatures/show", creatureData);
});

export default magicalCreaturesRouter;
