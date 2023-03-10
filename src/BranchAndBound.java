import java.util.ArrayList;
import java.util.Arrays;

public class BranchAndBound {

    public static ArrayList<Pakket> CalculatePath(ArrayList<Pakket> pakketjesOriginal) {
        ArrayList<Pakket> pakketjes = (ArrayList<Pakket>) pakketjesOriginal.clone();
        int pathLength = pakketjes.size();
        ArrayList<RouteWrapper> routes = new ArrayList<RouteWrapper>();

        for (int i = 1; i <= pathLength; i++) {
            int[] initialRoute = new int[pathLength];
            initialRoute[0] = i;

            routes.add(new RouteWrapper(initialRoute, pakketjes));
        }
        int[] testpath = new int[pathLength];

        for (int i = 0; i < pathLength; i++) {
            testpath[i] = i + 1;
        }
        routes.add(new RouteWrapper(testpath, pakketjes));



        double upperbound=Double.POSITIVE_INFINITY;
        while (MoreRoutesToSearch(routes,upperbound) == true) {
            RouteWrapper routeToCheck = determineNextNode(routes,upperbound);
            if(routeToCheck!=null) {
                routes.addAll(LookForNextNodes(routeToCheck, pakketjes));

//                System.out.println(Arrays.toString(routeToCheck.getLocations()));
            }
            routes.remove(routeToCheck);
            upperbound = FindNewUpperbound(routes);
//            System.out.println(upperbound);
//            if(FindShortestCompleteRoute(routes)!=null) {
//                System.out.println(FindShortestCompleteRoute(routes).getLength());
//            }

        }
        RouteWrapper bestRoute = FindShortestCompleteRoute(routes);
        pakketjes= RearrangePakketjes(pakketjes, bestRoute);
        System.out.println(bestRoute.getLength());
        return pakketjes;
    }

    private static ArrayList<Pakket> RearrangePakketjes(ArrayList<Pakket> pakketjes, RouteWrapper bestRoute) {
        int[] locations = bestRoute.getLocations();
        ArrayList<Pakket> sortedPakketjes = new ArrayList<Pakket>();
        for (int location : locations) {
            if (location > 0 && location <= pakketjes.size()) {
                sortedPakketjes.add(pakketjes.get(location - 1));
            }
        }
        return sortedPakketjes;
    }



    private static boolean MoreRoutesToSearch(ArrayList<RouteWrapper> routes,double upperbound) {
        for (RouteWrapper route : routes) {
            int[] locations = route.getLocations();
            for (int i = 0; i < locations.length; i++) {
                if (locations[i] == 0) {
                    if(route.getLength()<=upperbound)
                    return true;
                }
            }
        }
        return false;
    }


    private static double FindNewUpperbound(ArrayList<RouteWrapper> routes) {
        double minLength = Double.POSITIVE_INFINITY;
        for (RouteWrapper route : routes) {
            int[] locations = route.getLocations();
            boolean isComplete = true;
            for (int location : locations) {
                if (location == 0) {
                    isComplete = false;
                    break;
                }
            }
            if (isComplete && route.getLength() < minLength) {
                minLength = route.getLength();
            }
        }
        return minLength;
    }

    private static RouteWrapper FindShortestCompleteRoute(ArrayList<RouteWrapper> routes) {
        RouteWrapper shortestCompleteRoute = null;
        double minLength = Double.POSITIVE_INFINITY;
        for (RouteWrapper route : routes) {
            int[] locations = route.getLocations();
            boolean isComplete = true;
            for (int location : locations) {
                if (location == 0) {
                    isComplete = false;
                    break;
                }
            }
            if (isComplete && route.getLength() < minLength) {
                shortestCompleteRoute = route;
                minLength = route.getLength();
            }
        }
        return shortestCompleteRoute;
    }


    /**
     * Returns the next best RouteWrapper based on the length of its route.
     * Only RouteWrappers with a length less than or equal to the given upperbound are considered.
     *
     * @param routes     The list of RouteWrapper objects to search through.
     * @param upperbound The upperbound for the length of the routes to consider.
     * @return The RouteWrapper with the shortest length among the routes that meet the condition.
     */
    private static RouteWrapper determineNextNode(ArrayList<RouteWrapper> routes,double upperbound) {
        double bestDistanceSoFar = upperbound;
        RouteWrapper routeToCheck = null;
        ArrayList<RouteWrapper> eligibleRoutes = new ArrayList<>();

        // Create a new list of eligible routes
        for (RouteWrapper route : routes) {
            if (route.getLength() <= bestDistanceSoFar) {
                eligibleRoutes.add(route);
            }
        }

        // Find the shortest eligible route
        for (RouteWrapper route : eligibleRoutes) {
            if (route.getLength() <= bestDistanceSoFar&&contains( route.getLocations(),0)) {
                bestDistanceSoFar = route.getLength();
                routeToCheck = route;
            }

        }

        return routeToCheck;
    }

    private static ArrayList<RouteWrapper> LookForNextNodes(RouteWrapper nextToSearch, ArrayList<Pakket> pakketjes) {
        ArrayList<RouteWrapper> nextNodes = new ArrayList<>();

        int[] locations = nextToSearch.getLocations();
        int indexToReplace = -1;
        for (int i = 0; i < locations.length; i++) {
            if (locations[i] == 0) {
                indexToReplace = i;
                break;
            }
        }

        if (indexToReplace == -1) {
            return nextNodes; // no zeros found
        }

        for (int i = 1; i <= pakketjes.size(); i++) {
            if (!contains(locations, i)) {
                int[] newLocations = locations.clone();
                newLocations[indexToReplace] = i;

                RouteWrapper newRoute = new RouteWrapper(newLocations, pakketjes);
                double newLength = newRoute.getLength();
                nextNodes.add(newRoute);
            }
        }

        return nextNodes;
    }

    private static boolean contains(int[] arr, int val) {
        for (int i : arr) {
            if (i == val) {
                return true;
            }
        }
        return false;
    }


}