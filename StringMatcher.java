
public class StringMatcher {
	/**
	 * 
	 * @param text		input text 
	 * @param pattern	input pattern to be found in text
	 * preprocessing time: 0
	 * Matching time: O((n-m+1)*m)
	 */
	public static void naiveStringMatcher(String text, String pattern) {
		int n = text.length();
		int m = pattern.length();
		for(int i=0; i<=(n-m);i++) {
			boolean patternOccured = true;
			int j=0;
			while(j<m) {
				if(pattern.charAt(j)!=text.charAt(j+i)) {
					patternOccured = false;
					break;
				}
				j++;
			}
			if(patternOccured) {
				System.out.println("Pattern occurs with shift\t"+i);
			}
		}
	}

/**
 * 
 * @param text		input text 
 * @param pattern	input pattern to be found in text
 * Preprocessing time: Theta(m)
 * Matching time: Theta(n)
 */
	public static void KMPMatcher(String text, String pattern) {
		int n = text.length();
		int m = pattern.length();
		int[] prefixFunction = computePrefixFunction(pattern);
		int q=0;
		for(int i=0;i<=(n-1);i++) {
			while(q>0 && pattern.charAt(q)!=text.charAt(i)) {
				q = prefixFunction[q-1];
			}
			if(pattern.charAt(q)==text.charAt(i)) {
				q++;
			}
			if(q==m) {
				System.out.print("Pattern occurs with shift\t" + (i-m+1)+"\n");
				q = prefixFunction[q-1];
			}
		}
	}
	
	//helper to stringMatcher
	private static int[] computePrefixFunction(String pattern) {
		int m=pattern.length();
		int[] result = new int[m];
		result[0] =0;
		int k=0;
		for(int q =1; q<=(m-1);q++) {
			while(k>0 && (pattern.charAt(k+1) != pattern.charAt(q)) ) {
				k = result[k];
			}
			if(pattern.charAt(k+1) == pattern.charAt(q)) {
				k++;
			}
			result[q]=k;
		}
		return result;
	}
	
	//there must not be object of this class
	private StringMatcher() {
		throw new IllegalStateException("Utility class");
	}
			
}
