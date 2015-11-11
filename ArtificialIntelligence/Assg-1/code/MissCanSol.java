
/**
 *
 * @author kanikakapoor
 */
/*
 Name : Kanika Kapoor
 */
import java.util.*;

public class MissCanSol 
{
    public static void main(String[] args) 
    {
        System.out.println("Please enter the number of missionaries and cannibals you want to play with");
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the no. of missionaries: ");
        int num1 = scan.nextInt();
        System.out.println("Enter the no. of cannibals: ");
        int num2 = scan.nextInt();
        System.out.println("Calculating the solution..");

        StateBean initialState = new StateBean(num1, num2, 0); //Initially, 3M,3C & Boat are on left. OM & OC on right 
        MissCanSol m = new MissCanSol();
        StateBean solution = m.searchtechBFS(initialState); //calling the BFS Search to find solution
        StateBean.displayPath(solution);
    } //end of main()

    // Reference : http://docs.oracle.com/javase/7/docs/api/java/util/Queue.html
    //create a Queue(FIFO) data structure for implementing BFS Search
    Queue<StateBean> myQueue = new LinkedList<>();

    // Reference : http://docs.oracle.com/javase/7/docs/api/java/util/HashMap.html
    //create Hahmap to store the set of all explored states
    HashMap<StateBean, String> exploredset = new HashMap<>();

    public StateBean searchtechBFS(StateBean initialState) 
    {
        /*
         Before starting BFS Search, we check if the starting node
         is the goal state . If it is equal to goal state, return the goal .
         */
        if (initialState.ifGoalstate()) 
        {
            return initialState;
        }

        //if it is not the goal state, add the initial(first) state to the queue. 
        myQueue.add(initialState);

        while (true) 
        {
            if (myQueue.isEmpty()) 
            {
                return null;
            }

            StateBean state = myQueue.poll();

            //add the state to explored state Hashmap so that we don't look to this state again. or say to avoid duplicacy
            exploredset.put(state, "");

            //generate list of successors(children) of the state that was just explored
            List<StateBean> children = state.generateSuccessors();

            //explore all the Children nodes of the explored state
            for (StateBean child : children) 
            {
                if (!exploredset.containsValue(child)) 
                {
                    if (child.ifGoalstate()) //check if the child is the goal state or not
                    {
                        //if it is goal state, return the child as the solution
                        return child;
                    } //end of inner if

                    //if child is not the goal state, put it in the queue
                    myQueue.add(child);
                }//end of outer if

            }//end of for
        }//end of while loop
    } // end of searchtechBFS() 
} //end of class
