package com.example.server;

import java.util.List;

/**
 * x,y,z orientation values
 */
public class OrientationData {

    private int x;
    private int y;
    private int z;

    public OrientationData() {
    }

    public OrientationData(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Orientation {" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
    
    public static OrientationData average(List<OrientationData> data) {
    	final OrientationData result = new OrientationData();
    	data.stream().forEach(od -> {result.x+=od.x; result.y+=od.y; result.z+=od.z;});
    	result.x = result.x / data.size();
    	result.y = result.y / data.size();
    	result.z = result.z / data.size();
    	return result;
    }

}
