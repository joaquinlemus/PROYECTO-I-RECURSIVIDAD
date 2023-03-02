import java.util.LinkedList;

public class Solver{

    private final char north = 'N';
    private final char south = 'S';
    private final char east = 'E';
    private final char west = 'W';
    private final char up = 'U';
    private final char down = 'D';
    
    private boolean finished = false;

    private Node actualNode;
    private Node nextNode;

    private LinkedList<Character> solution;

    public Solver(){
        solution = new LinkedList<>();
    }
    
    public String toConvert(LinkedList<Character> ls) {
        String s = "[";
        for (int i = 0; i < ls.size(); i++) {
            if (i == ls.size()-1) {
                s+=ls.get(i);
                break;
            }
            s += ls.get(i)+",";
        }
        s += "]";
        return s;
    }
    
    public LinkedList<Character> solutionToMaze(Maze maze, Node actualNode, LinkedList<Character> path) {
        if (!path.isEmpty()){
            if(path.getLast() == north){
                actualNode.south = false;
            } else if(path.getLast() == south){
                actualNode.north = false;
            } else if(path.getLast() == west){
                actualNode.east = false;
            } else if(path.getLast() == east){
                actualNode.west = false;
            } else if(path.getLast() == up){
                actualNode.down = false;
            } else if(path.getLast() == down){
                actualNode.up = false;
            }
        }
        if (actualNode.isExit) {
            finished = true;
            return path;
        } else {
                if (actualNode.north) {
                    nextNode = maze.moveNorth(actualNode);
                    if (!nextNode.danger && nextNode != maze.getStartingSpace()) {
                        actualNode.north = false;
                        path.add(north);
                        solutionToMaze(maze, nextNode, path);
                        if (finished) {
                            return path;
                        } else {
                            path.removeLast();
                        }
                    }
                }
                if (actualNode.west) {
                    nextNode = maze.moveWest(actualNode);
                    if (!nextNode.danger && nextNode != maze.getStartingSpace()) {
                        actualNode.west = false;
                        path.add(west);
                        solutionToMaze(maze, nextNode, path);
                        if (finished) {
                            return path;
                        } else {
                            path.removeLast();
                        }
                    } 
                }
                if (actualNode.south) {
                    nextNode = maze.moveSouth(actualNode);
                    if (!nextNode.danger && nextNode != maze.getStartingSpace()) {
                        actualNode.south = false;
                        path.add(south);
                        solutionToMaze(maze, nextNode, path);
                        if (finished) {
                            return path;
                        } else {
                            path.removeLast();
                        }
                    } 
                }
                if (actualNode.east) {
                    nextNode = maze.moveEast(actualNode);
                    if (!nextNode.danger && nextNode != maze.getStartingSpace()) {
                        actualNode.east = false;
                        path.add(east);
                        solutionToMaze(maze, nextNode, path);
                        if (finished) {
                            return path;
                        } else {
                            path.removeLast();
                        }
                    } 
                }
                if (actualNode.down) {
                    nextNode = maze.moveDown(actualNode);
                    if (!nextNode.danger && nextNode != maze.getStartingSpace()) {
                        actualNode.down = false;
                        path.add(down);
                        solutionToMaze(maze, nextNode, path);
                        if (finished) {
                            return path;
                        } else {
                            path.removeLast();
                        }
                    }
                }
                if (actualNode.up) {
                    nextNode = maze.moveUp(actualNode);
                    if (!nextNode.danger && nextNode != maze.getStartingSpace()) {
                        actualNode.up = false;
                        path.add(up);
                        solutionToMaze(maze, nextNode, path);
                        if (finished) {
                            return path;
                        } else {
                            path.removeLast();
                        }
                    }
                }
                if (finished) {
                    return path;
                }
            }
        return path;
    }
    
    public String solve(Maze maze){
        actualNode = maze.getStartingSpace();
        solution = new LinkedList<>();
        finished = false;
        return toConvert(solutionToMaze(maze, actualNode, solution));
    }
}