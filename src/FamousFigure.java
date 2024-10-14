import components.map.Map;
import components.map.Map2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Holds information about the artist Raphael.
 *
 * @author Nicole Young
 */
public final class FamousFigure {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private FamousFigure() {
    }

    /**
     * A work done by the artist.
     *
     * @param figureInfo
     *            the map containing information about the figure
     * @return a randomly chosen work by the figure
     */
    public static String work(Map<String, String> figureInfo) {
        //initialize workChosen to "no works avaiable" incase there are no works
        String workChosen = "no works avaiable";
        //check if there are works
        if (numberOfWorks(figureInfo) > 0) {
            //change range to between [1, numberOfWorks)
            int range = 1 + (int) (Math.random()
                    * ((numberOfWorks(figureInfo) - 1) + 1));
            //return a random work to workChosen variable
            workChosen = figureInfo.value("Work" + range);
        }

        return workChosen;
    }

    /**
     * The impact of the figure on the world.
     *
     * @param figureInfo
     *            the map containing information about the figure
     * @return the impact of the figure
     */
    public static String impact(Map<String, String> figureInfo) {
        //initialize workChosen to empty string
        String impact = figureInfo.value("Impact");

        //check if there are woks
        if (numberOfWorks(figureInfo) > 0) {
            //add famous works into impact in comma separated list
            impact += " Also known for their famous works: ";
            for (int i = 1; i <= numberOfWorks(figureInfo); i++) {
                if (i == numberOfWorks(figureInfo)) {
                    impact += figureInfo.value("Work" + i) + ". ";
                } else {
                    impact += figureInfo.value("Work" + i) + ", ";
                }
            }
        }

        return impact;
    }

    /**
     * The number of works in the figure infomation map.
     *
     * @param figureInfo
     *            the map containing information about the figure
     * @return the number of works
     */
    public static int numberOfWorks(Map<String, String> figureInfo) {

        //start number of works at 0 incase there are no works
        int count = 0;
        //check if there is a work
        if (figureInfo.hasKey("Work1")) {
            //increase count of works for every work found
            while (figureInfo.hasKey("Work" + (count + 1))) {
                count++;
            }
        }

        return count;
    }

    /**
     * Retrieves information that the user wants to know.
     *
     * @param info
     *            the subject that the user wants to know more about
     * @param figureInfo
     *            the map containing information about the figure
     * @return information the user wants
     */
    public static String getInfo(String info, Map<String, String> figureInfo) {
        //finds the information the user wanted to know
        String fact = figureInfo.value(info);
        return fact;
    }

    /**
     * Reports if the figure is still alive or not.
     *
     * @param figureInfo
     *            the map containing information about the figure
     * @return true if the figure is alive, false if dead
     */
    public static Boolean isAlive(Map<String, String> figureInfo) {
        //assume figure is alive
        boolean alive = true;
        //check if there is a died date, and if there is assume figure has died
        if (figureInfo.hasKey("Died, Date")) {
            alive = false;
        }

        return alive;
    }

    /**
     * The birth country of the figure.
     *
     * @param figureInfo
     *            the map containing information about the figure
     * @return the birth country of the figure
     */
    public static String birthCountry(Map<String, String> figureInfo) {
        //get loction of birth country including city
        String birthLocation = getInfo("Born, Location", figureInfo);
        //initialize birth country to empty string
        String birthCountry = "";
        //initialize indexOfComma outside of index
        int indexOfComma = -1;
        //loop until birth country is added to birthCountry variable
        for (int i = 0; i < birthLocation.length(); i++) {
            //find first letter
            char c = birthLocation.charAt(i);
            /*
             * country is after "city, " so only add character after the city,
             * comma, and space
             */
            if (c == ',') {
                indexOfComma = i;
            }
            if (indexOfComma != -1 && i > (indexOfComma + 1)) {
                birthCountry += c;
            }
        }
        return birthCountry;
    }

    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        //a map of information about the famous figure, in this case Rapheal
        Map<String, String> figureInfo = new Map2<>();
        figureInfo.add("Figure", "Rapheal");
        figureInfo.add("Born, Date", "(MM.DD.YYYY) 03.28.1483 or 04.06.1483");
        figureInfo.add("Born, Location", "Urbino, Italy");
        figureInfo.add("Died, Date", "(MM.DD.YYYY) 04.06.1520");
        figureInfo.add("Died, Location",
                "Rome, Italy (Buried in the Pantheon)");
        figureInfo.add("Work1", "Raphael Rooms-The School of Athens");
        figureInfo.add("Work2", "The Transfiguration");
        figureInfo.add("Work3", "Portrait of Pope Julius II");
        figureInfo.add("Impact",
                "Was incredibly talented at painting, printing, and drawing "
                        + "which inspired many during the time. Is regarded as "
                        + "one of the greatest artists of all time.");

        out.println("number of works: " + numberOfWorks(figureInfo));
        out.println("work chosen: " + work(figureInfo));
        out.println("impact of Raphael: " + impact(figureInfo));
        out.println("Alive or dead? " + isAlive(figureInfo));
        out.println("Country of birth: " + birthCountry(figureInfo));

        //close output
        out.close();
    }
}
