public class RouteWrapper {
    private int[] locations;
    private double length;

    public RouteWrapper(int[] locations, int length) {
        this.locations = locations;
        this.length = length;
    }

    public void setLocations(int[] locations) {
        this.locations = locations;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public int[] getLocations() {
        return locations;
    }

    public double getLength() {
        return length;
    }
}
