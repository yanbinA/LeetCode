package skyline;

import java.util.*;

public class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        int[] buildPoint = buildings[0];
        SkylinePoint skylinePoint = new SkylinePoint();
        List<List<Integer>> skyline = new ArrayList<>();
        for (int i = 1; i < buildings.length; i++) {
            int[] building = buildings[i];
            if (buildPoint[1] >= building[0]) {
                if (building[0] == buildPoint[0]) {
                    if (buildPoint[2] < building[2]) {
                        skylinePoint.putNode(new Node(buildPoint));
                        buildPoint = building;
                        continue;
                    }
                }
                if (!isCover(buildPoint, building)) {
                    skylinePoint.putNode(new Node(building));
                }
            } else {
                skyline.add(Arrays.asList(buildPoint[0], buildPoint[2]));
                if (skylinePoint.first == null) {
                    skyline.add(Arrays.asList(buildPoint[1], 0));
                    buildPoint = building;
                } else {
                    int[] next = skylinePoint.removeFirst().value;
                    if (next[2] > buildPoint[2]) {
                        int Ri = buildPoint[1];
                        buildPoint = building;
                        buildPoint[0] = Ri;
                    } else {
                        if (next[1] < buildPoint[1]) {
                            buildPoint[0] = next[1];
                            skylinePoint.putNode(new Node(buildPoint));
                        }
                        buildPoint = building;
                    }
                    i--;
                }
            }
        }
        return skyline;
    }
    
    class Node{
        int[] value;
        Node next;
        Node provide;
        public Node(int[] value) {
            this.value = value;
        }
    }

    boolean isCover(int[] building0, int[] building) {
        //boolean Lb = building[0] >= building0[0];
        boolean Rb = building[1] <= building0[1];
        boolean Hb = building[2] <= building0[2];
        return Rb && Hb;
    }
    
    class SkylinePoint{
        Node first;
        SkylinePoint() {
        }

        SkylinePoint(int[] value) {
            this.first = new Node(value);
        }
        
        void putNode(Node building) {
            if (first == null) {
                first = building;
                return;
            }
            Node currentNode = first;
            while (currentNode.value[2] > building.value[2]) {
                if (currentNode.next == null) {
                    currentNode.next = building;
                    building.provide = currentNode;
                    return;
                } else {
                    currentNode = currentNode.next;
                }
            }
            building.provide = currentNode.provide;
            building.next = currentNode;
            currentNode.provide = building;
            if (first.provide != null) {
                first = first.provide;
            }
        }

        Node removeFirst() {
            Node removeNode = first;
            first = first.next;
            first.provide = null;
            return removeNode;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        System.out.println(solution.getSkyline(buildings));
    }
}


