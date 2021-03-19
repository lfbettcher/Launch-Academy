import express from "express";

import ConcertVenue from "../../../models/ConcertVenue.js";

const concertVenuesRouter = express.Router();

concertVenuesRouter.get("/", async (req, res) => {
  try {
    const concertVenues = await ConcertVenue.findAll();
    res.status(200).json({ concertVenues: concertVenues });
  } catch (error) {
    res.status(500).json({ errors: error });
  }
});

concertVenuesRouter.post("/", async (req, res) => {
  try {
    const formData = req.body;
    const newConcertVenue = new ConcertVenue(formData);
    await newConcertVenue.save();
    res.status(304);
  } catch (error) {
    res.status(500).json({ errors: error });
  }
});

export default concertVenuesRouter;
