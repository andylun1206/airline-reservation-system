package ca.umanitoba.cs.comp3350.saveonflight.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Shenyun Wang on 2017-06-06.
 */

public class SearchCriteriaListViewEntryTest {
    private SearchCriteriaListViewEntry entry1;
    private SearchCriteriaListViewEntry entry2;
    private SearchCriteriaListViewEntry entry3;
    private final String s1 = "winnipeg";
    private final String s2 = "toronto";

    @Before
    public void setup(){
        entry1 = new SearchCriteriaListViewEntry(1, s1);
        entry2 = new SearchCriteriaListViewEntry(2, s2);
        entry3 = new SearchCriteriaListViewEntry(1,s2);
        assertNotNull(entry1);
        assertNotNull(entry2);
    }

    @After
    public void tearDown(){
        entry1 = null;
        entry2 = null;
    }

    @Test
    public void testGetIcon(){
        assertEquals(entry1.getIcon(),entry3.getIcon());
        assertFalse(entry1.getIcon() == entry2.getIcon());
    }
    @Test
    public void testGetTitle(){
        assertEquals(entry3.getTitle(),entry2.getTitle());
        assertEquals(entry1.getTitle(),s1);
        assertFalse(entry1.getTitle().equals("WINNIPEG"));
    }
    @Test
    public void testClone(){
        SearchCriteriaListViewEntry testEntry = entry1.clone();
        assertNotNull(testEntry);
        assertEquals(testEntry,entry1);
        assertFalse(testEntry.equals(entry2));
    }


}
