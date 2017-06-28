package ca.umanitoba.cs.comp3350.saveonflight.presentation;

/**
 * SearchCriteriaListViewEntry.java
 * <p>
 * Object representing an entry in the ListView on the search page.
 *
 * @author Andy Lun
 */

public class SearchCriteriaListViewEntry {
    private int icon;
    private String title;

    public SearchCriteriaListViewEntry(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public SearchCriteriaListViewEntry clone() {
        return new SearchCriteriaListViewEntry(this.icon, this.title);
    }
}
