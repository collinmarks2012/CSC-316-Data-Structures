public class execTime
{
		public static void main (String[] args)
		{
		double recursiveResult;
		double non_recursiveResult;
		long recursive_time=0;
		long non_recursive_time = 0;
		long start_time, end_time;
		long total_time;
		double y;
		long n=2147483647; // maximum long
		long num_rep;
		int i;

		y=1.00000001; //Initialize y , 1.[7 zeros]1

		{
		recursive_time = 0;  // Initialize timers
		non_recursive_time = 0;

		{
		pwr rec = new pwr(); //Create object pwr
		power nonrec = new power(); //Create object power

		System.out.println("Recursive version executing...");
		start_time=System.currentTimeMillis();
		recursiveResult = rec.recursive(y,n);
		num_rep = rec.counter();
		end_time=System.currentTimeMillis();
		total_time = end_time-start_time;
		System.out.println("y = "+y+"\t n = "+n);
		System.out.println("y^n = "+recursiveResult );
		System.out.println("repetitions = "+num_rep );
		System.out.println("Total time = "+total_time);

		System.out.println("Nonrecursive version executing...");
		start_time=System.currentTimeMillis();
		non_recursiveResult = nonrec.non_recursive(y,n);
		end_time=System.currentTimeMillis();
		total_time = end_time-start_time;
		System.out.println("y = "+y+"\t n = "+n);
		System.out.println("y^n = "+recursiveResult );
		System.out.println("Total time = "+total_time);

		}
		}
}
}