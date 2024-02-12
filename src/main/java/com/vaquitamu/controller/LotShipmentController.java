package com.vaquitamu.controller;

import com.vaquitamu.model.business.CheeseLot;
import com.vaquitamu.model.business.DairyProductLot;
import com.vaquitamu.model.business.MilkLot;
import com.vaquitamu.model.business.YogourtLot;
import com.vaquitamu.model.graph.Road;
import com.vaquitamu.model.graph.RoadCondition;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.WeightedMultigraph;

import java.util.*;


/**
 * This is the most important class of the project.
 * It performs all the actions to get the shortest
 * routes. Also allows to conect the user input with
 * the model and the graph.
 */
public class LotShipmentController {
    private WeightedMultigraph<String, Road> routes;
    private Map<DairyProductLot, String> shipments;
    private DairyProductLot actualLot;
    private DairyProductType type;
    private List<String> towns;
    private int GRAPH_SIZE;


    /**
     * Instances the class
     * and intances the auxiliar collections.
     */
    public LotShipmentController() {
        this.routes = new WeightedMultigraph<>(Road.class);
        this.shipments = new HashMap<>();
        this.towns = new ArrayList<>();
        this.loadRouteMap();
    }

    /**
     * This method load the graph
     * from the Json source.
     */
    private void loadRouteMap(){
       GraphLoader loader = new GraphLoader("./src/main/java/com/vaquitamu/persistence/graph.json");
       this.routes = loader.getGraph();
       towns.addAll(this.routes.vertexSet());
       this.GRAPH_SIZE = towns.size();
    }

    /**
     * This method creates a DairyProduct Lot
     * @param id identifier
     * @param name name of the lot
     * @param expirationDate Date of expiration
     * @param units units in the lot
     * @param special this value is a unique attribute in each DairyProduct Children ( protein, fats, sugar )
     * @param content content or weight of each unit in the lot
     * @param type type of Dairy Product
     * @return true if the lot didn't create before and it was possible to create a new one else false.
     */
    public boolean addLot(String id, String name, Date expirationDate, int units, double special, double content, DairyProductType type ){
        for ( DairyProductLot productLot : this.shipments.keySet() ){
            if( productLot.getId().compareTo(id) == 0) return false;
        }
        this.type = type;
        switch ( this.type ){
            case CHEESE -> actualLot = new CheeseLot(id, name, expirationDate, units, special, content);
            case MILK -> actualLot = new MilkLot(id, name, expirationDate, units, special, content);
            case YOUGOURT -> actualLot = new YogourtLot(id, name,expirationDate, units,special, content);
        }
        if ( actualLot == null ) return false;
        this.shipments.put( actualLot, "");
        return true;
    }


    /**
     * This method calculates the shortest path between two cities
     * using Dijkstra algorithm created in JGraphT. Also, the method
     * weights each edge, it depends on the type of Dairy Product
     * @param begin index of the beginning city
     * @param end index of the ending city
     * @return a String with the shortest route and some information.
     */

    public String calcRoute( int begin, int end){
        if ( this.actualLot == null ) return "";
        List<String> towns = new ArrayList<>(this.routes.vertexSet());
        String townBegin = towns.get(begin);
        String townEnd = towns.get(end);
        String routeType = "";
        double weight = 0;
        switch ( this.type ){
            case MILK -> {
                routeType = "[Ruta menos accidentada]";
                for( Road road :  this.routes.edgeSet() ){
                    weight = road.getCondition() == RoadCondition.GOOD ? 1 : road.getCondition() == RoadCondition.REGULAR ? 2 : 3;
                    this.routes.setEdgeWeight( road , weight);
                }
            }
            case CHEESE -> {
                routeType = "[Ruta más económica]";
                for ( Road road : this.routes.edgeSet() ){
                    weight = road.getTollbars();
                    this.routes.setEdgeWeight(road, weight);
                }
            }
            case YOUGOURT -> {
                routeType = "[Ruta más rápida]";
                for ( Road road : this.routes.edgeSet() ){
                    weight =  road.getDistance();
                    this.routes.setEdgeWeight(road, weight);
                }
            }
        }
        String result = "";
        DijkstraShortestPath dijkstraPath = new DijkstraShortestPath<>(this.routes);
        GraphPath<String, Road> shortPath = dijkstraPath.getPath( townBegin, townEnd);
        for ( Road road : shortPath.getEdgeList()){
            result += " , " + road.getName() ;
        }
        result = result.replaceFirst( " , ", "");
        result = "Desde: " + this.towns.get(begin) + " a " + this.towns.get(end) +"\n" + result + "\n" + routeType;
        this.shipments.put( actualLot, result);
        return result;
    }

    /**
     * Used to comunicate to the view what are the cities in the graph.
     * @return a style String with all the cities in the graph
     */
    public String showTowns(){
        String result = "";
        for( int i = 0; i < this.towns.size(); i++ ){
            result += (i+1) + ". " + towns.get(i) + "\n";
        }
        return result;
    }

    /**
     *
     * @return the number of the cities in the graph
     */
    public int getGRAPH_SIZE() {
        return GRAPH_SIZE;
    }

    /**
     * This method show all the performed shipments.
     * @return A string with the past shipments, with no order.
     */
    public String showPastShipments(){
        StringBuilder result= new StringBuilder("");
       for( DairyProductLot lot : this.shipments.keySet() ){
           result.append("------------------] ENVIO [ ------------------\n");
           result.append( lot.show() + "\nRuta: " + this.shipments.get(lot ) + "\n");
       }
       return result.toString();
    }



}
