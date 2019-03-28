package com.astar.search;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class AStarAlgorithm {

    private Node[][] searchSpace;
    private Node startNode;
    private Node finalNode;
    private List<Node> closedSet;
    private Queue<Node> openSet;

    AStarAlgorithm() {
        this.searchSpace = new Node[Constant.NUM_ROWS][Constant.NUM_COLS];
        this.openSet = new PriorityQueue<>(new NodeComparator());
        this.closedSet = new ArrayList<>();
        initializeSearchSpace();
    }

    private void initializeSearchSpace() {

        for (int rowIndex = 0; rowIndex<Constant.NUM_ROWS; rowIndex ++){
            for (int colIndex = 0; colIndex<Constant.NUM_COLS; colIndex++){
                Node node = new Node(rowIndex, colIndex);
                this.searchSpace[rowIndex][colIndex] = node;
            }
        }

        // set obstacles or blocks
        this.searchSpace[1][7].setBlock(true);
        this.searchSpace[2][3].setBlock(true);
        this.searchSpace[2][4].setBlock(true);
        this.searchSpace[2][5].setBlock(true);
        this.searchSpace[2][6].setBlock(true);
        this.searchSpace[2][7].setBlock(true);

        this.startNode = this.searchSpace[3][3];
        this.finalNode = this.searchSpace[1][6];

    }

    void search() {
        startNode.setH(manhatttanHeuristic(startNode, finalNode));
        openSet.add(startNode);

        while (!openSet.isEmpty()){
            Node currentNode = openSet.poll();
            System.out.println(currentNode+" Predecessor is:" + currentNode.getPredecessor());

            if (currentNode.equals(finalNode)) return;

            openSet.remove(currentNode);
            closedSet.add(currentNode);

            for (Node neighbour: getAllNeighbour(currentNode)){
                if (closedSet.contains(neighbour)) continue;
                if (!openSet.contains(neighbour)) openSet.add(neighbour);
                neighbour.setPredecessor(currentNode);
            }
        }
    }

    private List<Node> getAllNeighbour(Node node) {
        List<Node> neighbours = new ArrayList<>();
        int row = node.getRowIndex();
        int col = node.getColIndex();

        if (row-1>=0 && !this.searchSpace[row-1][col].isBlock()){
            searchSpace[row-1][col].setG(node.getG()+Constant.HORIZONTAL_VERTICAL_COST);
            searchSpace[row-1][col].setH(manhatttanHeuristic(searchSpace[row-1][col], finalNode));
            neighbours.add(this.searchSpace[row-1][col]);
        }

        if(row+1<Constant.NUM_ROWS && !this.searchSpace[row-1][col].isBlock()){
            searchSpace[row+1][col].setH(manhatttanHeuristic(searchSpace[row+1][col], finalNode));
            searchSpace[row+1][col].setG(node.getG()+Constant.HORIZONTAL_VERTICAL_COST);
            neighbours.add(this.searchSpace[row][col+1]);
        }

        if (col-1>=0 && !this.searchSpace[row][col-1].isBlock()){
            searchSpace[row][col-1].setG(node.getG()+Constant.HORIZONTAL_VERTICAL_COST);
            searchSpace[row][col-1].setH(manhatttanHeuristic(searchSpace[row][col-1], finalNode));
            neighbours.add(this.searchSpace[row][col-1]);
        }

        if(col<Constant.NUM_ROWS && !this.searchSpace[row][col+1].isBlock()){
            searchSpace[row][col+1].setH(manhatttanHeuristic(searchSpace[row][col+1], finalNode));
            searchSpace[row][col+1].setG(node.getG()+Constant.HORIZONTAL_VERTICAL_COST);
            neighbours.add(this.searchSpace[row][col+1]);
        }


        return neighbours;
    }

    private int manhatttanHeuristic(Node node1, Node node2) {
        return (Math.abs(node1.getRowIndex()-node2.getRowIndex()) + Math.abs(node1.getColIndex()-node2.getColIndex()))*10;
    }

    void showPath() {
        System.out.println("SHORTEST PATH WITH A* SEARCH:");
        Node node = this.finalNode;

        while (node!=null) {
            System.out.println(node);
            node = node.getPredecessor();
        }
    }

}