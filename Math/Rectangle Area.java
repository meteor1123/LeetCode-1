/*
	Rectangle Area

	Find the total area covered by two rectilinear rectangles in a 2D plane.
	Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

							  |
 						______|________(C,D) : (3, 4)
						|	  |       |
						|     |       |
						|	  |_______|_____________ (G, H) : (9, 2)
						|	  |		  |			   |
			____________|_____|_______|____________|_________
		       (A, B):(-3, 0) |	O:(0,0)  		   |
							  |____________________|
							  |(E, F) :(0, -1)
							  |
							  |
							  |
							  |

	Assume that the total area is never beyond the maximum possible value of int.
*/

public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int areaA = Math.abs(C - A) * Math.abs(D - B);
        int areaB = Math.abs(G - E) * Math.abs(H - F);
     
        if(A >= G || B >= H || C <= E || D <= F){
            return areaA + areaB;
        } 
        int length = Math.min(C, G) - Math.max(A, E);
        int height = Math.min(D, H) - Math.max(B, F);
        
        int areaC = length * height;
        return areaA + areaB - areaC ;
    }
}