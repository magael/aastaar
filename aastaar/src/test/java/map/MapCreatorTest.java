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
            "height 5",
            "width 3",
            "@@@",
            "T.T",
            "W.W",
            "T..",
            "T@T"
        };
        creator.readMapData(mapData);
    }

    @Test
    public void mapHeightIsSetCorrectly() {
        assertEquals(5, creator.getGrid().length);
    }
    
    @Test
    public void mapWidthIsSetCorrectly() {
        assertEquals(3, creator.getGrid()[0].length);
        assertEquals(3, creator.getGrid()[1].length);
        assertEquals(3, creator.getGrid()[2].length);
        assertEquals(3, creator.getGrid()[3].length);
        assertEquals(3, creator.getGrid()[4].length);
    }
}
