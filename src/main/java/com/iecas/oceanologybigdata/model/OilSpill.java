package com.iecas.oceanologybigdata.model;

public class OilSpill {
    int ncols;
    int nrows;
    double xllcorner;
    double yllcorner;
    double cellsize;
    String NODATA;
    String[][] data;

    public int getNcols() {
        return ncols;
    }

    public void setNcols(int ncols) {
        this.ncols = ncols;
    }

    public int getNrows() {
        return nrows;
    }

    public void setNrows(int nrows) {
        this.nrows = nrows;
    }

    public double getXllcorner() {
        return xllcorner;
    }

    public void setXllcorner(double xllcorner) {
        this.xllcorner = xllcorner;
    }

    public double getYllcorner() {
        return yllcorner;
    }

    public void setYllcorner(double yllcorner) {
        this.yllcorner = yllcorner;
    }

    public double getCellsize() {
        return cellsize;
    }

    public void setCellsize(double cellsize) {
        this.cellsize = cellsize;
    }

    public String getNODATA() {
        return NODATA;
    }

    public void setNODATA(String NODATA) {
        this.NODATA = NODATA;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }
}
