/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShortestPath;

/**
 *
 * @author HP Pavilion
 */
import java.util.*;

class ShortestPath {

    static final int MAXN = 100;
    static int INF = (int) 1e7;

    static int[][] distance = new int[MAXN][MAXN];
    static int[][] Next = new int[MAXN][MAXN];

    static void initialize(int numberOfNodes,
            int[][] graph) {
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                distance[i][j] = graph[i][j]; 
                if (graph[i][j] == INF) {
                    Next[i][j] = -1;
                } else {
                    Next[i][j] = j;
                }
            }
        }
    }

    static Vector<Integer> constructPath(int startNode, int finishNode) {
        if (Next[startNode][finishNode] == -1) {
            return null;
        }

        Vector<Integer> path = new Vector<Integer>();
        path.add(startNode);

        while (startNode != finishNode) {
            startNode = Next[startNode][finishNode];
            path.add(startNode);
        }
        return path;
    }
  
    static void floydWarshall(int numberOfNodes) {
        for (int k = 0; k < numberOfNodes; k++) {
            for (int i = 0; i < numberOfNodes; i++) {
                for (int j = 0; j < numberOfNodes; j++) { 
                    if (distance[k][j] == INF
                            || distance[j][i] == INF) {
                        continue;
                    }

                    if (distance[k][i] > distance[k][j]
                            + distance[j][i]) {
                        distance[k][i] = distance[k][j]
                                + distance[j][i];
                        Next[k][i] = Next[k][j];
                    }
                }
            }
        }
    }

    static void printPath(Vector<Integer> path) {
        int pathSize = path.size();
        for (int i = 0; i < pathSize - 1; i++) {
            System.out.print(path.get(i) + " -> ");
        }
        System.out.print(path.get(pathSize - 1) + "\n");
    }

    public static void main(String[] args) {
        int numberOfNodes = 7;
        int[][] graph = {
            {0, 1, 1, INF, INF, INF, INF},
            {1, 0, INF, INF, INF, 1, INF},
            {1, INF, 0, 1, INF, 1, INF},
            {INF, INF, 1, 0, 1, INF, INF},
            {INF, INF, INF, 1, 0, INF, 1},
            {INF, 1, 1, INF, INF, 0, INF},
            {INF, INF, INF, INF, 1, 1, 0}};
  
        initialize(numberOfNodes, graph);

        floydWarshall(numberOfNodes);
        Vector<Integer> path; 
        
        System.out.print("Shortest path: ");
        path = constructPath(1, 3);
        printPath(path);
    }
}
