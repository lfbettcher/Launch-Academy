We're going to create the next social media phenomenon: dogbook. It's the best of social media that we all care about - pictures of pets.

## Getting Started

```no-highlight
createdb dogbook_development
et get java-dogbook
cd java-dogbook
idea .
```

## Meets Expectations Requirements

### Post a Profile

```no-highlight
As a dog lover
I want to post my pet's profile
So that I can share them with the world
```

Acceptance Criteria:

- When I navigate to `/dogs/new`, I'm presented with a form to create a new profile
- I am prompted for the dog's first name, last name (family name), a photo url, a dog breed, and the sex
- After successfully entering a profile, I am redirected to the pet listing
- Dog profiles are saved in the database via a POST request to `/dogs` and using a Data Access Object (DAO)

### List Pets

```no-highlight
As a dog lover
I want to view other dogs on dogbook
So I can find friends for my dog
```

Acceptance Criteria:

- Dogs are listed in ascending order by last name and first name
- For each dog, I can see their first name, last name, and their photo
- I query the database using a Data Access Object (DAO)

## Exceeds Expectations

### Suggested Friends for Your Pet

```no-highlight
As a dog lover
I want a customized browsing experience
So that I am encouraged to return
```

Acceptance Criteria:

- After entering a profile, when I navigate to `/dogs`, the headline reads "Find Friends for (Pet's First Name Here)"
- This headline should persist whenever I navigate to `/dogs`

### Normalize the Dog Breeds

```no-highlight
As a site admin
I want the dog breed to be normalized
So that I can organize data on the basis of dog breed
```

Acceptance Criteria:

- The dog's breed must be normalized to a separate table, and referenced through an association
- The user can still enter the breed as text, but your persisting routine should either find or create the dog's breed in a related table

### List Dogs of a Specific Breed

```no-highlight
As a golden retriever owner
I want to find other golden retrievers
So that the dogs can hang out
```

Acceptance Criteria:

- When I navigate to `/dogs?breed=Golden+Retriever`, I should only see dogs with the breed "Golden Retriever"
