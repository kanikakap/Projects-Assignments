package Project_AI;

class Node{
 
        public final String value;
        public double g_scores;
        public final double h_scores;
        public double f_scores = 0;
        public Edge[] adjacencies;
        public Node parent;
 
        public Node(String val, double hVal){
                value = val;
                h_scores = hVal;
        }
 
        public String toString(){
                return value;
        }
 
}
 

