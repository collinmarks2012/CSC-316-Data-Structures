
public class BCount {

	public static void main(String[] args) {
		System.out.println(count(1));
		System.out.println(count(2));
		System.out.println(count(3));
		System.out.println(count(4));
		System.out.println(count(5));
		
	}
	
	static int count(int N) {

		  if (N <=1) { 
		    return(1); 
		  } 
		  else { 

		    // Iterate through all the values that could be the root... 
		    int sum = 0; 
		    int left, right, root;

		    for (root=1; root<=N; root++) { 
		      left = count(root - 1); 
		      right = count(N - root);

		      // number of possible trees with this root == left*right 
		      sum += left*right; 
		    }

		    return(sum); 
		  } 
		} 

}
