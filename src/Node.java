import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics2D;

public class Node {
	private int xPos;
	private int yPos;
	private Color nodeColor = Color.LIGHT_GRAY;
	private final int WIDTH = 35;
	private final int HEIGHT = 35;
	private Node  right, down;

	public Node(int x, int y) {
		xPos = x;
		yPos = y;
	}

	public void render(Graphics2D g) {
		// Render the node as a colored rectangle with a black border
		g.setColor(Color.BLACK);
		g.drawRect(xPos, yPos, WIDTH, HEIGHT);
		g.setColor(nodeColor);
		g.fillRect(xPos + 1, yPos + 1, WIDTH - 1, HEIGHT - 1);
	}

	public void Clicked(int buttonCode) {
		// Handle mouse clicks on the node
		switch (buttonCode) {
			case 1 -> nodeColor = Color.BLACK;  // Left click sets the node as a wall (black)
			case 4 -> clearNode();  // Scroll click clears the node
		}
	}

	public void setColor(Color c) {
		// Set the color of the node
		nodeColor = c;
	}

	public List<Node> getNeighbours() {
		// Get neighboring nodes that are paths
		List<Node> neighbours = new ArrayList<>();
		if (down != null && down.isPath())
			neighbours.add(down);
		if (right != null && right.isPath())
			neighbours.add(right);


		return neighbours;
	}

	public void setDirections(Node r,  Node d) {
		// Set neighboring nodes
		right = r;
		down = d;
	}

	public void clearNode() {
		// Reset the color of the node to LIGHT_GRAY
		nodeColor = Color.LIGHT_GRAY;
	}

	public int getX() {
		// Get the x-coordinate of the node in terms of grid position
		return (xPos - 15) / WIDTH;
	}

	public int getY() {
		// Get the y-coordinate of the node in terms of grid position
		return (yPos - 15) / HEIGHT;
	}

	public Node setX(int x) {
		// Set the x-coordinate of the node
		xPos = x;
		return this;
	}

	public Node setY(int y) {
		// Set the y-coordinate of the node
		yPos = y;
		return this;
	}

	public boolean isWall() {
		// Check if the node is a wall (black)
		return (nodeColor == Color.BLACK);
	}

	public boolean isEnd() {
		// Check if the node is the target/end node (red)
		return (nodeColor == Color.RED);
	}

	public boolean isPath() {
		// Check if the node is a path (LIGHT_GRAY or RED)
		return (nodeColor == Color.LIGHT_GRAY || nodeColor == Color.RED);
	}

	public boolean isSearched() {
		// Check if the node has been searched (BLUE or ORANGE)
		return (nodeColor == Color.BLUE || nodeColor == Color.ORANGE);
	}
}
