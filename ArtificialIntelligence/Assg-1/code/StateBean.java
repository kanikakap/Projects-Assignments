/**
 *
 * @author kanikakapoor
 */
/*
 Name : Kanika Kapoor
 */

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StateBean 
{
    private int canL;
    private int missL;
    private int boatpos;
    private StateBean parentState;

    public int getCannibal() 
    {
        return canL;
    }

    public void setCannibal(int leftcan) 
    {
        this.canL = leftcan;
    }

    public int getMissionary() 
    {
        return missL;
    }

    public void setMissionary(int leftmiss) 
    {
        this.missL = leftmiss;
    }

    public int getBoat() 
    {
        return boatpos;
    }

    public void setBoat(int boat) 
    {
        boatpos = boatpos;
    }

    public StateBean getParentState() 
    {
        return parentState;
    }

    public void setParentState(StateBean parentState) 
    {
        this.parentState = parentState;
    }

    public StateBean(int missL, int canL, int boatpos) 
    {
        this.canL = canL;
        this.missL = missL;
        this.boatpos = boatpos;
    }

    StateBean(StateBean state) 
    {
        //auto generated
        try {
            throw new Exception("Not supported yet.");
        } catch (Exception ex) {
            Logger.getLogger(StateBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
     public boolean isValidState() 
    {
        if ((getMissionary() >= getCannibal() || getMissionary() == 0)
                && (getCannibal() <= 3) && (getMissionary() <= 3)
                && (getCannibal() >= 0) && (getMissionary() >= 0)
                && (((3 - getMissionary()) >= (3 - getCannibal())) || (3 - getMissionary() == 0)))
        {
            return true;
        }
        return false;
    }
    public boolean ifGoalstate() 
    {
        return canL == 0 && missL == 0;
    }

   
    
     private void goState(List<StateBean> succ, StateBean newState) 
    {
        if (newState.isValidState()) 
        {
            newState.setParentState(this);
            succ.add(newState);
        }
    }

    /*
     @param fringes : means the successors
     */
    public List<StateBean> generateSuccessors() 
    {
        List<StateBean> fringes = new ArrayList<StateBean>();
        if (boatpos == 0)
        {
            /*
             When boat is on Left bank, 5 states are possible
             Boat takes 2 missionaries from L to R
             Boat takes 2 cannibals from L to R
             Boat takes 1 missionary from L to R
             Boat takes 1 cannibal from L to R
             Boat takes 1 missionary & 1 cannibal from L to R
             */
            goState(fringes, new StateBean(missL, canL - 2, 1));
            goState(fringes, new StateBean(missL - 2, canL, 1));
            goState(fringes, new StateBean(missL - 1, canL - 1, 1));
            goState(fringes, new StateBean(missL, canL - 1, 1));
            goState(fringes, new StateBean(missL - 1, canL, 1));

        } 
        else //when boat position is Right
        {
            /*
             When boat is on Right Bank, 5 states are possible
             Boat takes 2 missionaries from R to L
             Boat takes 2 cannibals from R to L
             Boat takes 1 missionary from R to L
             Boat takes 1 cannibal from R to L
             Boat takes 1 missionary & 1 cannibal from R to L
             */
            
            goState(fringes, new StateBean(missL, canL + 2, 0));
            goState(fringes, new StateBean(missL + 2, canL, 0));
            goState(fringes, new StateBean(missL + 1, canL + 1, 0));
            goState(fringes, new StateBean(missL, canL + 1, 0));
            goState(fringes, new StateBean(missL + 1, canL, 0));
        }
        return fringes;
    }

   
    @Override
    public String toString() 
    {
        if (boatpos == 0) //boat on Left
        {
           System.out.println("[" + missL + " | " + canL + " | Boat:Left]");
        } 
        else 
        {
            System.out.println("[" + missL + " | " + canL + " | Boat:Right]");
        }
        return "";
    }

    public static void displayPath(StateBean solution) 
    {
            StateBean answer = solution;
            List<StateBean> pathtraversed = new ArrayList<StateBean>();
            while (null != answer) 
            {
                pathtraversed.add(answer);
                answer = answer.getParentState();
            } //end of while
            for(int k = (pathtraversed.size()-1); k>=0; k--) 
            {
                answer = pathtraversed.get(k);
                System.out.print(answer.toString());
            }  
        
    }// end of displayPath()  

} //end of class StateBean
