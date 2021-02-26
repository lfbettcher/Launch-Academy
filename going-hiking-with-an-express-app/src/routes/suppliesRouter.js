import express from "express";

import Supply from "../models/Supply.js";

const suppliesRouter = new express.Router();

suppliesRouter.get("/", (req, res) => {
  res.render("index", { supplies: Supply.findAll() });
});

suppliesRouter.post("/", (req, res) => {
  const supplyItem = req.body.item;
  const checkItem = supplyItem.replace(/\s/g, "");
  if (checkItem.length === 0) {
    res.render("index", { supplies: Supply.findAll(), error: "Invalid input!" });
    return;
  }
  const newSupply = new Supply({ name: supplyItem });
  newSupply.save();
  res.redirect("/supplies");
});

export default suppliesRouter;
