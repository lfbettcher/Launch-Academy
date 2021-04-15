The ship owners decided to implement the crew manifest in JSON. They want you to read in multiple JSON files.

## Getting Started

```no-highlight
et get java-space-inventory
cd java-space-inventory
idea .
```

## User Stories

### Crew Manifest is JSON Based

```no-highlight
As an owner
I want JSON based manifests
So that data maintenance is easier
```

Acceptance Criteria:

- Read a file named `crewManifest.json` to get all the crew data on the ship
- Output the manifest like you have in previous exercises (sample output provided below)

### List Inventory

```no-highlight
As an owner
I want to keep better track of inventory
So that things don't get lost
```

Acceptance Criteria:

- Read in `inventory.json` and list out the items in cargo

### Remove Inventory

```no-highlight
As an owner
I want to document inventory removal
So that things don't get lost
```

Acceptance Criteria:

- Read a file named `removeInventory.json`.
- Remove the items listed from from the inventory that is outputted
- Items that no longer have inventory should not be listed

Your output should something look like:

```no-highlight
Crew:
-----------
Rank: Pilot, Name: Hobert 'Wash' Washburne
Rank: Engineer, Name: Kaylee Fry
Rank: Shepard, Name: Derrial Book
Rank: Lieutenant, Name: Zoë Washburne
Rank: Super-Cargo, Name: River Tam
Rank: Ambassador, Name: Inara Serra
Rank: Muscle, Name: Jayne Cobb
Rank: Doctor, Name: Simon Tam
Rank: Captain, Name: Malcolm Reynolds

INVENTORY
--------
Item: Saddles: Count: 15
Item: Food Stuffs: Count: 300
Item: Shotgun: Count: 3

Process finished with exit code 0

```
