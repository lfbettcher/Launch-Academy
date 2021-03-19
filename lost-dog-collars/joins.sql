-- 1. Which collars have a known owner?
-- Display only collars with known owners and those owners' names.
SELECT c.dog_name, o.name owner_name
FROM dog_owners o
  JOIN lost_dog_collars c ON o.dog_name = c.dog_name;

-- 2. For which collars is there no known owner?
-- Display only collars without known owners.
SELECT c.dog_name
FROM lost_dog_collars c
  LEFT JOIN dog_owners o ON c.dog_name = o.dog_name
WHERE o.dog_name IS NULL;

-- 3. What collars are in our possession?
-- Display all collars in our possession.
-- If an owner exists for a given collar, display that also.
SELECT c.dog_name, o.name owner_name
FROM lost_dog_collars c
  LEFT JOIN dog_owners o ON c.dog_name = o.dog_name;

-- 4. What owners do we know about?
-- Display all owners known to us.
-- If a collar matches that owner, display the collar also.
SELECT o.name owner_name, c.dog_name
FROM dog_owners o
  LEFT JOIN lost_dog_collars c ON o.dog_name = c.dog_name;