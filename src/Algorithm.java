import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Algorithm {

	// Time delay between steps in milliseconds
	private final int searchTime = 200;

	/**
	 * Breadth-First Search algorithm to find the shortest path from start to end.
	 * @param start The starting node.
	 * @param end The ending node.
	 * @param graphSize The size of the graph.
	 */
	public void bfs(Node start, Node end, int graphSize) {
		// Create a queue to perform BFS
		Queue<Node> queue = new LinkedList<>();
		// 2D array to store the previous nodes in the path
		Node[][] prev = new Node[graphSize][graphSize];

		// Initialize the queue with the start node
		queue.add(start);

		// BFS algorithm
		while (!queue.isEmpty()) {
			// Dequeue a node from the front of the queue
			Node curNode = queue.poll();

			// If the current node is the end node, mark it and break out of the loop
			if (curNode.isEnd()) {
				curNode.setColor(Color.MAGENTA); // Mark the end node as magenta
				break;
			}

			// If the current node has not been searched, mark it as orange and search its neighbors
			if (!curNode.isSearched()) {
				curNode.setColor(Color.ORANGE); // Mark the current node as orange (being searched)
				try {
					// Introduce a delay to visualize the search process
					TimeUnit.MILLISECONDS.sleep(searchTime);
				} catch (Exception e) {
					e.printStackTrace();
				}

				// Mark the current node as searched (blue) and enqueue its neighbors
				curNode.setColor(Color.BLUE);
				for (Node adjacent : curNode.getNeighbours()) {
					queue.add(adjacent); // Enqueue the neighboring nodes
					prev[adjacent.getX()][adjacent.getY()] = curNode;
				}
			}
		}

		// Reconstruct and visualize the shortest path
		shortestPath(prev, end);
	}

	/**
	 * Reconstruct and visualize the shortest path from the end to the start.
	 * @param prev 2D array representing the previous nodes in the path.
	 * @param end The end node.
	 */
	private void shortestPath(Node[][] prev, Node end) {
		Node pathConstructor = end;
		while (pathConstructor != null) {
			// Backtrack from the end to the start along the shortest path
			pathConstructor = prev[pathConstructor.getX()][pathConstructor.getY()];

			if (pathConstructor != null) {
				// Mark nodes in the path as orange
				pathConstructor.setColor(Color.ORANGE);
			}

			try {
				// Introduce a delay to visualize the path reconstruction
				TimeUnit.MILLISECONDS.sleep(searchTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
