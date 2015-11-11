package bigdata.assignment1;

import java.util.Comparator;

public class RatingComparator implements Comparator<Rating>
{
	@Override
	public int compare(Rating arg0, Rating arg1) {
		// TODO Auto-generated method stub
		if(arg0.rating<arg1.rating)
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}
}