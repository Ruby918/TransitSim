# CSC207 Project

## Team

- Brian (Brian Tat Quach)
- Loic (Loic Nassif-Lachapelle)
- Dan (Dhanraj Ashwinkumar Patel)
- Danya (Danya Canivet Lette)

## Map configuration file

The map configuration file is in `map.txt`.

There are three types of commands in this file:
- commands starting with "Route",
- commands starting with "Station", and
- commands starting with "Hub"
The commands must appear in that order, i.e. all "Route" commands must appear before all "Station" commands;
all "Station" commands must appear before all "Hub" commands.

### Creating Routes

The commands are structured as follows: `Route, <route type>, <route ID>, <route mame>`,
where route type must be either `Subway` or `Bus`.

In order to create a subway line or a bus route in this map, add a command to the "Route" block of `map.txt`.
A command starting with "Route, Subway" will create a new subway line.
A command starting with "Route, Bus" will create a new bus line.

Routes have ID numbers. These are effectively just list indexes: they are numbers starting at 0, and incrementing by one for each subsequent route. These should appear in order.
Following the ID number is the route's name. This can be any string.

#### Example

To create a subway line called "University", followed by a bus line called "College", write
```
Route, Subway, 0, University
Route, Bus, 1, College
```

### Creating Stations

The commands are structured as follows: `Station, <route ID>, <station ID>, <station mame>`.

In order to create a station station or a bus stop on this map, add a command to the "Station" block of `map.txt`.
Recall that all "Station" commands must appear _after_ all "Route" commands.

Each station is on one route.
Stations have ID numbers. These are effectively just list indexes: they are numbers starting at 0, and incrementing by one for each subsequent station on the same route.
Each route's stations should have indexes starting at 0.
Stations also have names.

#### Example

To create two stations on the first route (named "My Station<i> on Route0"), and three stations on the second route (named "My Station<i> on Route1"), write
```
Station, 0, 0, My Station0 on Route0
Station, 0, 1, My Station1 on Route0
Station, 1, 0, My Station0 on Route1
Station, 1, 1, My Station1 on Route1
Station, 1, 2, My Station1 on Route1
```

### Creating Hubs

It is possible to make a set of stations be adjacent to one another.
In practice, one station being adjacent to another just means that it is permissible to transfer between those two stations on one trip.
Recall that all "Hub" commands must appear _after_ all "Station" commands.

The commands are structured as follows: `Hub, <route ID> <station ID> | <route ID> <station ID> | ... | <route ID> <station ID>`
A hub may have any number of stations.

