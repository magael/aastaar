package map;

import mj.aastaar.map.MapCreator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MJ
 */
public class MapCreatorTest {

    MapCreator creator;

    @Before
    public void setUp() {
        creator = new MapCreator();
        String[] mapData = {
            "type octile",
            "height 4",
            "width 3",
            "map",
            "T.T",
            "W.W",
            "T..",
            "T@T"
        };
        creator.readMapData(mapData);
    }

    @Test
    public void mapHeightIsSetCorrectly() {
        assertEquals('T', creator.getGrid()[0][0]);
        assertEquals('T', creator.getGrid()[3][2]);
        assertEquals(4, creator.getGrid().length);
    }
    
    @Test
    public void mapWidthIsSetCorrectly() {
        assertEquals(3, creator.getGrid()[0].length);
        assertEquals(3, creator.getGrid()[1].length);
        assertEquals(3, creator.getGrid()[2].length);
        assertEquals(3, creator.getGrid()[3].length);
    }
    
    @Test
    public void mapCreationFailsSafelyWithBadFilePath() {
        MapCreator newCreator = new MapCreator();
        String noSuchPath = "wrong/no_file.bad";
        newCreator.createMapFromFile(noSuchPath);
        char[][] emptyGrid = new char[0][0];
        assertArrayEquals(emptyGrid, newCreator.getGrid());
    }
    
    @Test
    public void mapCreationSucceedsWithCorrectFilePath() {
        MapCreator newCreator = new MapCreator();
        String testMap = "testmaps/test.map";
        newCreator.createMapFromFile(testMap);
        char[][] grid = newCreator.getGrid();
        char c = grid[0][0];
        assertEquals('T', c);
//        c = grid[2][1];
//        assertEquals('W', c);
        assertEquals(4, grid.length);
    }
}
