package com.example.server;

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
}
