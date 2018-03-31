
//Pwr class  
// Pwr function
// Logarithmic recursive function to compute the power y^n

class pwr {  

 	int repetitions = 0;

 	public double recursive (double y, long n )     
		 {    
		 double temp;   
		 if(n==0)  
				 {
				 if(y==0) temp = 0;
				 else temp = 1;
				 }
		 else
				{
				repetitions++;
				temp=recursive(y,n/2);
				if(n%2 != 0)    
						temp = y*temp*temp;

				else
						temp=temp*temp;
				}
				return temp;
		}

	public int counter()
		{
   		return repetitions;
 		}
}


//power class
//Power function
//Linear non-recursive function to computer power y^n

class power {

public  double non_recursive( double y, long n )
		{    
		double  result;
		long     i;

		result = 1;

		for(i=1;i<=n;i++)
				result = result*y;
		
		return result;
		}
}