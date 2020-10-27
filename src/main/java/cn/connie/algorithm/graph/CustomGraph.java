package cn.connie.algorithm.graph;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class CustomGraph {

    private static final int MAX_VERTEX_COUNT = 20;//最大顶点个数
    private Stack<Integer> mStack;//存储顶点的在数组中的索引位置
    private Queue<Integer> mQueue;//存储顶点的在数组中的索引位置
    private Vertex[] vertexList;//顶点数组
    private int[][] adjMat;//顶点和顶点之间的边，是一个矩阵(0代表不连通，1代表连通,即有边)
    private int vertexCount;//顶点个数


    public CustomGraph() {
        this.mStack = new Stack<>();
        this.mQueue = new ArrayDeque<>();
        this.vertexList = new Vertex[MAX_VERTEX_COUNT];
        this.adjMat = new int[MAX_VERTEX_COUNT][MAX_VERTEX_COUNT];
        this.vertexCount = 0;
        for (int i = 0; i < MAX_VERTEX_COUNT; i++) {
            for (int j = 0; j < MAX_VERTEX_COUNT; j++) {
                adjMat[i][j] = 0;//初始化顶点和顶点之间的边
            }
        }
    }

    /**
     * 添加顶点
     */
    public void addVertex(Object data) {
        this.vertexList[vertexCount++] = new Vertex(data);
    }

    /**
     * 添加边,用邻接矩阵表示边，是对称的，两部分都要赋值
     */
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    // 打印某个顶点表示的值
    public void displayVertex(int v) {
        System.out.print(vertexList[v].data);
    }

    /**
     * 深度优先搜索（DFS）:
     * 1、用peek()方法检查栈顶的顶点
     * 2、用getAdjUnvisitedVertex()方法找到当前栈顶点邻接且未被访问的顶点
     * 3、第二步方法返回值不等于-1则找到下一个未访问的邻接顶点，
     * 访问这个顶点，并入栈 如果第二步方法返回值等于 -1，则没有找到，出栈
     */
    public void deepFirstSearch() {
        vertexList[0].wasVisited = true;
        displayVertex(0);
        mStack.push(0);
        while (!mStack.isEmpty()) {
            Integer vertexIndex = mStack.peek();//取出但不删除栈顶的元素
            int unvisitedVertex = getAdjUnvisitedVertex(vertexIndex);
            if (unvisitedVertex == -1) {
                mStack.pop();//如果没有边了，则进行出栈
            } else {
                vertexList[unvisitedVertex].wasVisited = true;//标记访问顶点
                displayVertex(unvisitedVertex);//打印顶点
                mStack.push(unvisitedVertex);//如果有边则继续加入栈中
            }
        }
        restWasVisited();
    }

    /**
     * 广度优先搜索(BFS)：
     * 1、用remove()方法检查栈顶的顶点
     * 2、试图找到这个顶点还未访问的邻节点
     * 3、如果没有找到，该顶点出列
     * 4、如果找到这样的顶点，访问这个顶点，并把它放入队列中
     */
    public void breadthFirstSearch() {
        vertexList[0].wasVisited = true;
        displayVertex(0);
        mQueue.add(0);//添加到队尾
        int unvisitedVertex;
        while (!mQueue.isEmpty()) {
            Integer vertexIndex = mQueue.remove();//取出队首
            //根据当前的节点循环找到邻接顶点,加入到队列中
            while ((unvisitedVertex = getAdjUnvisitedVertex(vertexIndex)) != -1) {
                vertexList[unvisitedVertex].wasVisited = true;//标记访问顶点
                displayVertex(unvisitedVertex);//打印顶点
                mQueue.add(unvisitedVertex);//如果有边则继续加入到队尾
            }

        }
        restWasVisited();
    }

    /**
     * 找到与某一顶点邻接且未被访问的顶点
     *
     * @param v 代表元素在数组索引位置
     */
    public int getAdjUnvisitedVertex(int v) {
        for (int i = 0; i < vertexCount; i++) {
            // v顶点与i顶点相邻（邻接矩阵值为1）且未被访问 wasVisited为false
            if (!vertexList[i].wasVisited && adjMat[v][i] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 栈访问完毕，重置所有标记位wasVisited为false
     */
    private void restWasVisited() {
        for (int i = 0; i < vertexCount; i++) {
            vertexList[i].wasVisited = false;
        }
    }

    public class Vertex {
        public Object data;
        public boolean wasVisited;

        public Vertex(Object data) {
            this.data = data;
            this.wasVisited = false;
        }
    }


    /*** 构建的图的结构************
     - B - C
     A
     - D - E
     */
    public static void main(String[] args) {
        CustomGraph graph = new CustomGraph();
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');

        graph.addEdge(0, 1);// AB
        graph.addEdge(1, 2);// BC
        graph.addEdge(0, 3);// AD
        graph.addEdge(3, 4);// DE

        System.out.print("深度优先搜索算法 :");
        graph.deepFirstSearch();
        System.out.println();
        System.out.print("广度优先搜索算法 :");
        graph.breadthFirstSearch();
        System.out.println();
    }

}
