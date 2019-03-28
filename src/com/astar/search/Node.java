package com.astar.search;

public class Node {

    private int g;
    private int h;
    private int rowIndex;
    private int colIndex;
    private Node predecessor;

    private boolean isBlock;

    public Node(int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean block) {
        isBlock = block;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public int getF() {
        return g + h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

    public Node getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }

    @Override
    public boolean equals(Object node2) {
         Node otherNode = (Node) node2;
         return this.rowIndex == otherNode.getRowIndex() && this.colIndex == otherNode.getColIndex();
    }

    @Override
    public String toString() {
        return "Node (" +this.rowIndex + ";" + this.colIndex + ") h:" + this.h + " - g:" + this.g + " - f:" + this.getF();
    }
}
