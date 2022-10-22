# MazeSolver
Program that solves a maze that the user makes. Made in Summer 2020.

Uses Dijkstra's Shortest Path algorithm to make the shortest path through a maze that the
user makes by selecting / de-selecting tiles. The program works by going through the
maze, and at every point where a change in direction is needed or possible wanted
a node is placed there. These nodes make up a graph, with the weights of the edges
between these nodes being the distance between each node in the maze. Dijkstra's
Shortest Path algorithm is then used to determine the shortest path between the
start node and end node, which is then displayed as a path through the maze.
