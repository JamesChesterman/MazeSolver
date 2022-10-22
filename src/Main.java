import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	private static String[][] maze;
	
	public static String[][] getSolvedMaze(){
		return maze;
	}
	public Main() {
		// TODO Auto-generated constructor stub
		maze = GUIWindow.getMaze();
		/*
		maze = new String[][]{
			{"w", "w", "w", "w", "s", "w", "w", "w", "w", "w", "w", "w", "w"},
			{"w", " ", " ", " ", " ", " ", " ", " ", "w", " ", " ", " ", "w"},
			{"w", "w", "w", "w", "w", "w", "w", " ", "w", " ", "w", " ", "w"},
			{"w", " ", " ", "w", " ", " ", "w", " ", "w", " ", "w", " ", "w"},
			{"w", " ", "w", "w", "w", " ", "w", " ", "w", " ", "w", " ", "w"},
			{"w", " ", "w", "w", " ", " ", " ", " ", " ", " ", "w", " ", "w"},
			{"w", " ", "w", "w", "w", "w", "w", "w", "w", "w", "w", " ", "w"},
			{"w", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "w"},
			{"w", "w", "w", "w", "w", "w", "w", "w", "w", "w", " ", "w", "w"},
			{"w", " ", " ", " ", " ", " ", " ", " ", "w", " ", " ", " ", "w"},
			{"w", "w", "w", "w", "w", "w", "w", " ", "w", " ", "w", " ", "w"},
			{"w", " ", " ", "w", " ", " ", "w", " ", "w", " ", "w", " ", "w"},
			{"w", " ", "w", "w", "w", " ", "w", " ", "w", " ", "w", " ", "w"},
			{"w", " ", "w", "w", " ", " ", " ", " ", " ", " ", "w", " ", "w"},
			{"w", " ", "w", "w", "w", "w", "w", "w", "w", "w", "w", " ", "w"},
			{"w", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "w"},
			{"w", "w", "w", "w", "w", "w", "w", "w", "w", "w", " ", "w", "w"},
			{"w", " ", " ", " ", " ", " ", " ", " ", "w", " ", " ", " ", "w"},
			{"w", "w", "w", "w", "w", "w", "w", " ", "w", " ", "w", " ", "w"},
			{"w", " ", " ", "w", " ", " ", "w", " ", "w", " ", "w", " ", "w"},
			{"w", " ", "w", "w", "w", " ", "w", " ", "w", " ", "w", " ", "w"},
			{"w", " ", "w", "w", " ", " ", " ", " ", " ", " ", "w", " ", "w"},
			{"w", " ", "w", "w", "w", "w", "w", "w", "w", "w", "w", " ", "w"},
			{"w", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "w"},
			{"w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "e", "w", "w"},
		};
		*/
		String selected;
		boolean left, right, up, down;
		ArrayList<int[]> nodeList = new ArrayList<int[]>();
		for(int i=0; i<maze.length; i++){
			for(int j=0;j<maze[i].length;j++){
				int[] coords = {i, j};
				left = false;
				right = false;
				up = false;
				down = false;
				selected = maze[i][j];
				if(selected == " "){
					for(int k=0;k<4;k++){
						String comparator;
						if(k==0){
							comparator = "s";
						}else if(k==1){
							comparator = "e";
						}else if(k==2){
							comparator = " ";
						}else{
							comparator = "n";
						}
						//Is there an empty left?
						if(maze[i-1][j] == comparator){
							left = true;
						}
						//Is there an empty right?
						if(maze[i+1][j] == comparator){
							right = true;
						}
						//Is there an empty up?
						if(maze[i][j-1] == comparator){
							up = true;
						}
						//Is there an empty down?
						if(maze[i][j+1] == comparator){
							down = true;
						}
					}
					//A node should be made here
					if((up == true || down == true) && (left == true || right == true)){
						nodeList.add(coords);
						maze[i][j] = "n";
					}
				}else if(selected == "s" || selected == "e"){
					nodeList.add(coords);
					maze[i][j] = "n";
				}
			}
		}
		int[][] nodeArray = nodeList.toArray(new int[nodeList.size()][2]);
		//Making adjacency matrix
		int[][] adjMatrix = new int[nodeList.size()][nodeList.size()];
		//Per row
		for(int x=0;x<adjMatrix.length;x++){
			//Per item in row
			for(int y=0;y<adjMatrix.length;y++){
				boolean wallInBetween = false;
				int weight = 1;
				if(nodeArray[x] == nodeArray[y]){
					//Same coordinate
					wallInBetween = true;
				}else if(nodeArray[x][0] == nodeArray[y][0]){
					//Same row
					if(nodeArray[x][1] < nodeArray[y][1]){
						//Add one to first one so that the program doesn't check for the starting node
						//Goes from start node to end node
						for(int i=nodeArray[x][1]+1;i<nodeArray[y][1];i++){
							//Checking to see if any walls in between
							if(maze[nodeArray[x][0]][i] == "w" || maze[nodeArray[x][0]][i] == "n"){
								wallInBetween = true;
							}
							weight++;
						}
					}else{
						for(int i=nodeArray[y][1]+1;i<nodeArray[x][1];i++){
							//Checking to see if any walls in between
							if(maze[nodeArray[x][0]][i] == "w" || maze[nodeArray[x][0]][i] == "n"){
								wallInBetween = true;
							}
							weight++;
						}
					}
				}else if(nodeArray[x][1] == nodeArray[y][1]){
					//Same column
					if(nodeArray[x][0] < nodeArray[y][0]){
						for(int i=nodeArray[x][0]+1;i<nodeArray[y][0];i++){
							if(maze[i][nodeArray[x][1]] == "w" || maze[i][nodeArray[x][1]] == "n"){
								wallInBetween = true;
							}
							weight++;
						}
					}else{
						for(int i=nodeArray[y][0]+1;i<nodeArray[x][0];i++){
							if(maze[i][nodeArray[x][1]] == "w" || maze[i][nodeArray[x][1]] == "n"){
								wallInBetween = true;
							}
							weight++;
						}
					}
					
				}else{
					wallInBetween = true;
				}
				if(wallInBetween == true){
					adjMatrix[x][y] = 0;
				}else{
					adjMatrix[x][y] = weight;
					System.out.println(Arrays.deepToString(adjMatrix));
				}
			}
		}
		System.out.println(Arrays.deepToString(maze));
		System.out.println(Arrays.deepToString(nodeArray));
		System.out.println(Arrays.deepToString(adjMatrix));

		int nodes = nodeArray.length;
		ArrayList<Integer> indexList = new ArrayList<Integer>();
		indexList = DijkstraShortestPath(0, adjMatrix, nodes);
		System.out.println("IndexList" + indexList.toString());
		for(int i=0;i<indexList.size()-1;i++){
			psBetweenNodes(nodeArray[indexList.get(i)], nodeArray[indexList.get(i+1)]);
		}
		for(int i=0;i<maze.length;i++){
			System.out.println(Arrays.toString(maze[i]));
		}
	}
	
	
	private void psBetweenNodes(int[] node1, int[] node2){
		maze[node1[0]][node1[1]] = "p";
		maze[node2[0]][node2[1]] = "p";
		if(node1[0] == node2[0]){
			//Difference in second entries in arrays (coordinates)
			//if node2 to the right of node 1
			if(node2[1] > node1[1]){
				for(int i=node1[1];i<node2[1];i++){
					maze[node1[0]][i] = "p";
				}
			}else{
				//If node2 to the left of node1
				for(int i=node2[1];i<node1[1];i++){
					maze[node1[0]][i] = "p";
				}
			}
			
		}else if(node1[1] == node2[1]){
			//Difference in first entries in arrays
			if(node2[0] > node1[0]){
				for(int i=node1[0];i<node2[0];i++){
					maze[i][node1[1]] = "p";
				}
			}else{
				for(int i=node2[0];i<node1[0];i++){
					maze[i][node1[1]] = "p";
				}
			}
			
		}
	}
	
	//Get vertex with lowest distance not in shortest path tree
	private int GetMinimumIndex(boolean[] shortestPathTree, int[] distances, int nodes){
		int shortestDistance = Integer.MAX_VALUE;
		int shortestIndex = -1;
		for(int x=0;x<nodes;x++){
			if(shortestPathTree[x] == false && shortestDistance>distances[x]){
				System.out.println("SHORTESTINDEX");
				shortestDistance = distances[x];
				shortestIndex = x;
			}
		}
		return shortestIndex;
	}
	private ArrayList<Integer> DijkstraShortestPath(int startIndex, int[][] adjMatrix, int nodes){
		//Make shortest path tree (spt)
		boolean[] spt = new boolean[nodes];
		System.out.println(spt[0]);
		int[] distances = new int[nodes];
		int INFINITY = Integer.MAX_VALUE;
		ArrayList<Integer> indexList = new ArrayList<Integer>();
		
		//Set all distances to infinity for comparison
		for(int x=0;x<nodes;x++){
			distances[x] = INFINITY;
		}
		//Start from index zero
		distances[startIndex] = 0;
		
		//Make shortest path tree
		for(int i=0;i<nodes;i++){
			//Get index with lowest distance
			int shortestIndex = GetMinimumIndex(spt, distances, nodes);
			System.out.println(shortestIndex);
			//Put this index in the spt
			if(shortestIndex>=0){
				spt[shortestIndex] = true;
				//Go through all adjacent indexes of shortest index and change values
				for(int j=0;j<nodes;j++){
					//Check edge between shortest index and j index
					if(adjMatrix[shortestIndex][j] > 0){
						//Check if end vertex already in spt
						if(spt[j] == false && adjMatrix[shortestIndex][j] != INFINITY){
							//If this distance is less than current distance value then update distance
							int newKey = adjMatrix[shortestIndex][j] + distances[shortestIndex];
							if(newKey<distances[j]){
								distances[j] = newKey;
							}
						}
					}
				}
			}
			
		}
		System.out.println(Arrays.toString(distances));
		int running = 0;
		int currentNode = distances.length -1;
		indexList.add(currentNode);
		while(running < distances.length){
			System.out.println("HERE");
			//Backtrack from end node to node that it is adjacent to AND has shortest distance from start. Make a note of these nodes
			int currentShortestDistance = Integer.MAX_VALUE;
			for(int i=0;i<nodes;i++){
				
				if((adjMatrix[currentNode][i] > 0) && (distances[i] < currentShortestDistance) && (indexList.contains(i) == false)){
					currentShortestDistance = distances[i];
					currentNode = i;
					//These are indexes in the node array
					indexList.add(i);
				}
			}
			if(currentNode == 0){
				running = distances.length;
			}
			running ++;
		}
		System.out.println(indexList.toString());
		
		
		System.out.println("Distance from start to end is: " + distances[nodes-1]);
		return indexList;
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainMenuScreen mainMenuScreen = new MainMenuScreen();
	}

}
