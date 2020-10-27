package cn.connie.algorithm.graph;

/**
 * DistPar类记录了当前顶点到起始顶点点的距离
 * 和当前顶点的父顶点（这个父节点，说的是有向图中的指向顶点）
 */
class DistPar {
    public int distance;//初始化到此节点的位置
    public int parentVert;//当前节点的上一个节点

    public DistPar(int pv, int d) {
        this.distance = d;
        this.parentVert = pv;
    }
}

class Vertex {
    public char data;//节点的数据
    public boolean wasVisited;//是否被访问过

    public Vertex(char data) {
        this.data = data;
        this.wasVisited = false;
    }
}

class PowerGraph {
    private final int MAX_VERT = 20; // 最大顶点数
    private final int INFINITY = Integer.MAX_VALUE;// 最远距离...表示无法达到
    private Vertex vertexList[]; // 存储顶点的数组
    private int adjMat[][]; // 存储顶点之间的边界
    private int nVerts; // 顶点数量
    private DistPar sPath[]; // 最短路径数组
    private int currentVert; // 当前顶点索引
    private int startToCurrent; //到当前顶点的距离
    private int nTree; // 最小生成树中的顶点数量

    public PowerGraph() {
        this.vertexList = new Vertex[MAX_VERT];
        this.adjMat = new int[MAX_VERT][MAX_VERT];
        this.sPath = new DistPar[MAX_VERT];
        nVerts = 0;

        for (int i = 0; i < MAX_VERT; i++) {
            for (int j = 0; j < MAX_VERT; j++) {
                adjMat[i][j] = INFINITY;
            }
        }
    }


    public void addVertex(char data) {
        vertexList[nVerts++] = new Vertex(data);
    }

    public void addEdge(int start, int end, int weight) {
        adjMat[start][end] = weight;//带有方向的有向图
    }

    public void resetGraph() {
        nTree = 0;
        for (int i = 0; i < nVerts; i++) {
            vertexList[i].wasVisited = false;
        }
    }

    /**
     * 最短路径算法
     */
    public void shortestPath() {
        int startTree = 0; //从vertex 0开始
        vertexList[startTree].wasVisited = true;
        /*
         * path()方法把邻接矩阵的对应行表达的距离复制到sPath[]中，实际总是先从第0行复制
         * 为了简单，假定源点的下标总为0。最开始，所有sPath[]数组中的父节点字段为A，即源点。
         */
        for (int i = 0; i < nVerts; i++) {
            int tempDist = adjMat[startTree][i];
            //sPath中保存的都是到初始顶点的距离，所以父顶点默认都是初始顶点，后面程序中会将其修改
            sPath[i] = new DistPar(startTree, tempDist);
        }

        nTree = 1;//记录顶点
        while (nTree < nVerts) {
            int indexMin = getMinPathIndex(); //获得sPath中的最小路径值索引
            int minDist = sPath[indexMin].distance; //获得最小路径
            if (minDist == INFINITY) {
                System.out.println("There are unreachable vertices");
                break;
            } else {
                //2. 把对应的顶点（这个最小距离所在列的题头）放入树中，这个顶点变成“当前顶点”currentVert
                currentVert = indexMin;
                startToCurrent = sPath[indexMin].distance;
            }
            vertexList[currentVert].wasVisited = true;
            nTree++;
            //根据currentVert的变化，更新所有的sPath[]数组内容
            adjust_sPath();
        }
        displayPaths();
        resetGraph();
    }

    private void displayPaths() {
        for (int j = 0; j < nVerts; j++) {
            System.out.print(vertexList[j].data + "="); // B=
            if (sPath[j].distance == INFINITY)
                System.out.print("inf"); // inf
            else
                System.out.print(sPath[j].distance); // 50
            char parent = vertexList[sPath[j].parentVert].data;
            System.out.print("(" + parent + ") "); // (A)
        }
        System.out.println("");
    }

    /**
     * 调整sPath中存储的对象的值，即顶点到初始顶点的距离，和顶点的父顶点
     * 这是Dijkstra算法的核心
     */
    private void adjust_sPath() {
        int column = 1;
        while (column < nVerts) {
            if (vertexList[column].wasVisited) {
                column++;
                continue;
            }
            int currentToFringe = adjMat[currentVert][column]; //获得当前顶点到其他顶点的距离，其他顶点不满足isInTree
            int startToFringe = startToCurrent + currentToFringe; //计算其他顶点到初始顶点的距离=当前顶点到初始顶点距离+当前顶点到其他顶点的距离
            int sPathDist = sPath[column].distance; //获得column处顶点到起始顶点的距离，如果不与初始顶点相邻，默认值都是无穷大

            if (startToFringe < sPathDist) {
                sPath[column].parentVert = currentVert; //修改其父顶点
                sPath[column].distance = startToFringe; //以及到初始顶点的距离
            }
            column++;
        }
    }

    /**
     * 获取sPath中最小路径的索引
     */
    public int getMinPathIndex() {
        int minDist = INFINITY;
        int indexMin = 0;//获取sPath中最小路径的索引
        for (int i = 1; i < nVerts; i++) {
            if (!vertexList[i].wasVisited && sPath[i].distance < minDist) {
                minDist = sPath[i].distance;
                indexMin = i;
            }
        }
        return indexMin;
    }
}

class Main {
    public static void main(String[] args) {
        PowerGraph theGraph = new PowerGraph();
        theGraph.addVertex('A'); // 0 (start)
        theGraph.addVertex('B'); // 1
        theGraph.addVertex('C'); // 2
        theGraph.addVertex('D'); // 3
        theGraph.addVertex('E'); // 4

        theGraph.addEdge(0, 1, 50); // AB 50
        theGraph.addEdge(0, 3, 80); // AD 80
        theGraph.addEdge(1, 2, 60); // BC 60
        theGraph.addEdge(1, 3, 90); // BD 90
        theGraph.addEdge(2, 4, 40); // CE 40
        theGraph.addEdge(3, 2, 20); // DC 20
        theGraph.addEdge(3, 4, 70); // DE 70
        theGraph.addEdge(4, 1, 50); // EB 50

        System.out.println("从A到其他节点的所有路径：Shortest paths");
        theGraph.shortestPath(); // shortest paths
        System.out.println();
    }
}
