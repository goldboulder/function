
package other;


public class MyMath {
    
    public static final double PHI = 1.61803398874989484820;
    
    public static double sprt(double number)
    {
        double low = (1.0 / Math.E);
        double high = 143.016087935746;

        if (number < Math.exp(-1.0 / Math.E))
            return Double.NaN;

        double ans = (low + high) / 2;

        for (int i = 0; i < 60; i++)
        {
            if (Math.pow(ans,ans) <= number)
                low = ans;
            else
                high = ans;
            ans = (low + high) / 2;
        }
        return ans;
    }
    
    public static long fibb(int number){
        if (number <= 0){
            return 0;
        }
        else if (number < 10){
            return simpleFibb(number);
        }
        else if (number > 92){
            return Long.MAX_VALUE;
        }
        return (long) (Math.round(Math.pow(PHI, number)/Math.sqrt(5)));
    }
    private static int simpleFibb(int number){
        int n1 = 0;
        int n2 = 1;
        int temp;
        for (int i = 0; i < number; i++){
            temp = n1 + n2;
            n1 = n2;
            n2 = temp;
        }
        return n1;
    }
    
    public static double fact(int number){
        if (number < 0){
            return Double.NaN;
        }
        double ans = 1;
        for (int i = 1; i <= number; i++){
            ans *= i;
        }
        return ans;
    }
    
    //approximation
    public static double tetra(double base, double tet){
        double approx = tet % 1;
        if (approx <= 0){
            approx ++;
        }
        
        if (tet > -1 && tet <= 0){
            return approx;
        }
        else if (tet > 0){
            for (double n = tet; n > 0; n--){
                approx = Math.pow(base, approx);
            }
            return approx;
        }
        else if (tet > -2){
            return Math.log(approx)/Math.log(base);
        }
    
        return Double.NaN;
    
    }
    
    //returns random int between min and max (evenly distributed)
    // if max < min, switches them
    public int randomInt(int min, int max){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*
     * http://introcs.cs.princeton.edu/java/91float/Gamma.java.html
     */
    public static double gamma(double x) {
        x++;
        double tmp = (x - 0.5) * Math.log(x + 4.5) - (x + 4.5);
        double ser = 1.0 + 76.18009173    / (x + 0)   - 86.50532033    / (x + 1)
                         + 24.01409822    / (x + 2)   -  1.231739516   / (x + 3)
                         +  0.00120858003 / (x + 4)   -  0.00000536382 / (x + 5);
        return Math.exp(tmp + Math.log(ser * Math.sqrt(2 * Math.PI)));
     }
    
}
