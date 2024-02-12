package com.vaquitamu.controller;
import com.vaquitamu.model.graph.Road;
import com.vaquitamu.model.graph.RoadCondition;
import org.jgrapht.graph.WeightedMultigraph;
import org.jgrapht.nio.ImportException;
import org.jgrapht.nio.json.JSONImporter;

import java.io.FileReader;


/**
 * This class allows to load a Graph from a file
 * in that case it imports from a Json file. This
 * class contains an WeightedMultiGraph that it will
 * load and then in other class it could be used by
 * the get method.
 */
public class GraphLoader {
    private WeightedMultigraph<String, Road> graph;

    /**
     * Instance the graph and load it.
     * @param pathGraph path to json source file
     */
    public GraphLoader(String pathGraph ) {
        this.graph = new WeightedMultigraph<>(Road.class);
        loadGraph( pathGraph);
    }

    /**
     * This method used JGraphT I/O package to parse Json
     * to a graph. In this case a weighedMultiGraph.
     * @param path path to json source file
     */
    private void loadGraph( String path ){
        try {

            JSONImporter<String, Road> importer = new JSONImporter<>();
            importer.setVertexFactory(id -> id);
            importer.setEdgeWithAttributesFactory( (attrinutes) ->{
                RoadCondition condition = null;
                switch ( attrinutes.get("condition").getValue()){
                    case "1" -> condition = RoadCondition.GOOD;
                    case "2" -> condition = RoadCondition.REGULAR;
                    default -> condition = RoadCondition.BAD;
                }
                Road r = new Road( attrinutes.get("id").getValue(), attrinutes.get("name").getValue(), Double.parseDouble(attrinutes.get("distance").getValue()), Integer.parseInt(attrinutes.get("tollbars").getValue()),condition);
                return r;
            } );
            importer.importGraph(this.graph, new FileReader(path));
        } catch (ImportException | java.io.IOException  | NumberFormatException e) {}
    }


    /**
     * This metod is used to get the Graph after loaded it.
     * @return a loaded graph from json
     */
    public WeightedMultigraph<String, Road> getGraph() {
        return graph;
    }
}
