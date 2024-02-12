package com.vaquitamu.model.graph;

/**
 * This class models a road as
 * a Graph edge. It contains some attributes
 * that can be used to weight an edge. These attributes
 * are distance, tollbars and condition
 */
public class Road {
    private String id;
    private String name;
    private Double distance;
    private Integer tollbars;
    private RoadCondition condition;

    /**
     * Instances a complete Road Object
     * @param id
     * @param name
     * @param distance
     * @param tollbars
     * @param condition
     */
    public Road(String id, String name, Double distance, Integer tollbars, RoadCondition condition) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.tollbars = tollbars;
        this.condition = condition;
    }

    /**
     *
     * @return the Road Id
     */
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getTollbars() {
        return tollbars;
    }

    public void setTollbars(Integer tollbars) {
        this.tollbars = tollbars;
    }

    public RoadCondition getCondition() {
        return condition;
    }

    public void setCondition(RoadCondition condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Road{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", distance=" + distance +
                ", tollbars=" + tollbars +
                ", condition=" + condition +
                '}';
    }
}
