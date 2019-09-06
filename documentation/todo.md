## Some TODO-notes:

---

### Scenario

#### initConfig()

* read config file
* map list / collection (probably a separate class), where:
  * read map paths from .cfg file to String[] mapFilePaths
  * initialize an array of Grids
  * for each line in mapFilePaths: add to grids
  * or maybe init one grid at a time when the user chooses a map

---

### Grid

#### heuristic()

* octile distance for diagonal movement

#### getNeighbours()

* directions which are specified in coordinate arrays, currently only allows movement in 4 directions (horizontal, vertical).
* no cutting corners for diagonal movement
* refactor inBounds checks

---

### MapCreator

#### handleMapRow()

* if the map height/width do not match the file, it should be scaled to that size
* make sure each row is of same length (as the first row)