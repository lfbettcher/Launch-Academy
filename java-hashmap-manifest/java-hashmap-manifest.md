Now that we have the foundations of some data structures, let's make our manifest a bit more sophisticated.

## Getting Started

```java
et get java-hashmap-manifest
cd java-hashmap-manifest
idea .
```

The crew manifest provided in this lesson is a comma-delimited list, meaning the traveler's rank and name are separated by a `,`. Let's use our knowledge of `Scanner`, `ArrayList`, and `HashMap` to build a more capable manifest.

## User Stories

```no-highlight
As a ship captain
I want to know the title of each traveler
So that I can organize my team.
```

Acceptance Criteria:

- Read the file a line at a time and organize the traveler's name and role in a `HashMap`.
- Use this `HashMap` to output the crew manifest in a new and specialized way:

```no-highlight
Malcolm Reynolds (Captain)
Zoë Washburne (Lieutenant)
Hobert 'Wash' Washburne (Pilot)
Kaylee Fry (Engineer)
Jayne Cobb (Muscle)
Simon Tam (Doctor)
River Tam (Super-Cargo)
Inara Serra (Ambassador)
Derrial Book (Shepard)
```
