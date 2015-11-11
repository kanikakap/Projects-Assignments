package bigdata.assignment1;

import org.apache.hadoop.io.Text;

public class Rating  {
	public Text businessID;
	  public  float rating;
	  
	public Rating(Text x,float rating){
		this.businessID =  x;
		this.rating = rating;
	}
}
