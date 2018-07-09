# CSC207 Project

## Team

- Brian (Brian Tat Quach)
- Loic (Loic Nassif-Lachapelle)
- Dan (Dhanraj Ashwinkumar Patel)
- Danya (Danya Canivet Lette)

## Events file (`events.txt`)

The file `events.txt` is comprised of a sequence of commands that can be issued to
the transit fare manager.
There are two types of commands: Admin, and Customer.

### Admin commands



### Customer commands

## Map configuration file (`map.txt`)

The map configuration file is in `map.txt`.

There are three types of commands in this file:
- commands starting with "Route:",
- commands starting with "Station:", and
- commands starting with "Hub:"
The commands must appear in that order, i.e. all "Route" commands must appear before all "Station" commands;
all "Station" commands must appear before all "Hub" commands.

### Creating Routes

The commands are structured as follows: `Route: <route type>, <route mame>`,
where route type must be either `Subway` or `Bus`.

In order to create a subway line or a bus route in this map, add a command to the "Route" block of `map.txt`.
A command starting with "Route, Subway" will create a new subway line.
A command starting with "Route, Bus" will create a new bus line.

#### Example

To create a subway line called "University", followed by a bus line called "College", write
```
Route, Subway, University
Route, Bus, College
```

### Creating Stations

The commands are structured as follows: `Station: <route type>, <route name>, <station mame>`.

In order to create a station station or a bus stop on this map, add a command to the "Station" block of `map.txt`.
Recall that all "Station" commands must appear _after_ all "Route" commands.

### Creating Hubs

It is possible to make a set of stations be adjacent to one another.
In practice, one station being adjacent to another just means that it is permissible to transfer between those two stations on one trip.
Recall that all "Hub" commands must appear _after_ all "Station" commands.

The commands are structured as follows: `Hub: <route type>, <route name>, <station name> | ... | <route type>, <route name>, <station name>`
A hub may have any number of stations.