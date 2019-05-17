package pm.data;

/**
 * A chemical with a name and absolute popularity on Wikipedia in the form of a
 * page view quantity.
 * 
 * @author Alex Tyner
 */
public class Chemical implements Comparable<Chemical> {

    /** Chemical name. */
    private String name;

    /**
     * Cumulative Wikipedia page views. An int should be enough to handle all
     * values.
     */
    private int pageViews;

    /**
     * Constructor for the Chemical.
     * 
     * @param name chemical name
     */
    public Chemical(String name) {
        setName(name);
    }

    /**
     * Sets the chemical's name.
     * 
     * @param name the chemical's name
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the chemical's page views.
     * 
     * @param views the number of page views
     */
    public void setViews(int views) {
        this.pageViews = views;
    }

    /**
     * Gets the chemical's name.
     * 
     * @return chemical name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the chemical's number of Wiki page views.
     * 
     * @return number of page views
     */
    public int getViews() {
        return this.pageViews;
    }

    /**
     * Compares this chemical to another by their page views.
     * 
     * @param o other chemical
     * @return negative number if this chemical comes before the other, zero if they
     *         are to be sorted the same, positive number of this chemical comes
     *         after the other
     */
    @Override
    public int compareTo(Chemical o) {
        return this.getViews() - o.getViews();
    }
}
