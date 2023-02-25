import java.util.Stack;

/*
    Esta es su clase principal. El unico metodo que debe implementar es
    public String[] solve(Maze maze)
    pero es libre de crear otros metodos y clases en este u otro archivo que desee.
*/
public class Solver{

    private Node actualNode;
    private int lastVisitedNodeID; // Representa el ID de la ultima camara visitada.
    // Indica si es posible moverse en una direccion
    private Node lastVisitedNode;
    private final char north = 'N';
    private final char south = 'S';
    private final char east = 'E';
    private final char west = 'W';
    private final char up = 'U';
    private final char down = 'D';
    private Stack<Character> solution; 

    public Solver(Maze maze){
        //Sientase libre de implementar el contructor de la forma que usted lo desee
        solution = new Stack<>();
        actualNode = maze.getStartingSpace();  // seteamos (0,0,0)
        lastVisitedNode = actualNode; // (0,0,0)

    }


    public String solve(Maze maze, Node actualNode){
        //Implemente su metodo aqui. Sientase libre de implementar métodos adicionales
        // N, O, S, E, U, D
        if (actualNode.isExit) {
            return turnIntoString(solution);
        } else {
            if (!actualNode.danger) {
                int possibilities = 6;
                // NORTE
                if (maze.moveNorth(actualNode) != actualNode) {
                    lastVisitedNode = actualNode;
                    actualNode = maze.moveNorth(actualNode);
                    solution.push(north);
                    solve(maze, actualNode);
                } else {
                    possibilities--;
                }
                // OESTE
                if (maze.moveWest(actualNode) != actualNode) {
                    lastVisitedNode = actualNode;
                    actualNode = maze.moveWest(actualNode);
                    solution.push(west);
                    solve(maze, actualNode);
                } else {
                    possibilities--;
                }            
                // SUR
                if (maze.moveSouth(actualNode) != actualNode) {
                    lastVisitedNode = actualNode;
                    actualNode = maze.moveSouth(actualNode);
                    solution.push(south);
                    solve(maze, actualNode);
                } else {
                    possibilities--;
                }

                // ESTE
                if (maze.moveEast(actualNode) != actualNode) {
                    lastVisitedNode = actualNode;
                    actualNode = maze.moveEast(actualNode);
                    solution.push(east);
                    solve(maze, actualNode);
                } else {
                    possibilities--;
                }

                // ARRIBA
                if (maze.moveUp(actualNode) != actualNode) {
                    lastVisitedNode = actualNode;
                    actualNode = maze.moveUp(actualNode);
                    solution.push(up);
                    solve(maze, actualNode);
                } else {
                    possibilities--;
                }

                // ABAJO
                if (maze.moveDown(actualNode) != actualNode) {
                    lastVisitedNode = actualNode;
                    actualNode = maze.moveDown(actualNode);
                    solution.push(down);
                    solve(maze, actualNode);
                } else {
                    possibilities--;
                }
                /* 
                    N -> solution.push(north)
                                |
                                V
                    Norte no es una solucion.
                    solution.pop();
                */
            } else {
                char erase = solution.peek();
                if (erase == north) {
                   lastVisitedNode.north = false; 
                } else if (erase == south) {
                    lastVisitedNode.south = false;
                } else if (erase == east) {
                    lastVisitedNode.east = false;
                } else if (erase == west) {
                    lastVisitedNode.west = false;
                } else if (erase == up) {
                    lastVisitedNode.up = false;
                } else if (erase == down) {
                    lastVisitedNode.down = false;
                }
                solution.pop();
                actualNode = lastVisitedNode;
                solve(maze, actualNode);
            }
        }
        return "[-1]";
    }

    private String turnIntoString(Stack<Character> toConvert){
        return null;
    }

    // private boolean isDanger(Node node) {
    //     return node.danger ? true : false;
    // }
}