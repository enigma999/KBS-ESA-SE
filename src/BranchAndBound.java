import java.util.ArrayList;

public class BranchAndBound {

    public static ArrayList<Pakket> CalculatePath(ArrayList<Pakket> pakketjesOriginal) {
        ArrayList<Pakket> pakketjes = (ArrayList<Pakket>) pakketjesOriginal.clone();
        int pathLength = pakketjes.size();
        boolean[] passed = new boolean[pathLength];
        ArrayList<RouteWrapper> routes = new ArrayList<RouteWrapper>();

        int[] zeroPointroute = new int[pathLength];
        routes.add(new RouteWrapper(zeroPointroute, 0));

        int[] initalroute = new int[pathLength];
        for (int i = 0; i < pathLength; i++) {
            initalroute[i] = i + 1;
        }
        double upperbound = FindPathLength(pakketjes, initalroute);

        RouteWrapper nextToSearch =determineNextNode(routes,upperbound);
        LookForNextNode(nextToSearch);
        System.out.println(upperbound);
        return pakketjes;
    }

    private static void LookForNextNode(RouteWrapper nextToSearch) {
    }

    private static RouteWrapper determineNextNode(ArrayList<RouteWrapper> routes, double upperbound) {
        for(int i=0;i<routes.size();i++){
            if(routes.get(i).getLength()>upperbound){
                routes.remove(i);
            }else{
                
            }

        }

    }

    private static double FindPathLength(ArrayList<Pakket> pakketjes, int[] route) {
        double length = 0;
        for (int i = 0; i < route.length-1; i++) {
            length = length + Main.FindDistance(pakketjes.get(route[i]-1), pakketjes.get(route[i + 1]-1));

        }


        return length;

    }

}