package Other;


public class ComplexNumber {
    private double realPart;
    private double imaginaryPart;
    
    public ComplexNumber(){
        realPart = 0;
        imaginaryPart = 0;
    }
    
    public ComplexNumber(double real){
        realPart = real;
        imaginaryPart = 0;
    }
    
    public ComplexNumber(double real, double imag){
        realPart = real;
        imaginaryPart = imag;
    }


    public double getRealPart() {
        return realPart;
    }


    public void setRealPart(double realPart) {
        this.realPart = realPart;
    }


    public double getImaginaryPart() {
        return imaginaryPart;
    }


    public void setImaginaryPart(double imaginaryPart) {
        this.imaginaryPart = imaginaryPart;
    }
    
    public ComplexNumber add(double num){
        return new ComplexNumber(realPart+num,imaginaryPart);
    }
    
    public ComplexNumber add(ComplexNumber c){
        return new ComplexNumber(realPart+c.getRealPart(),imaginaryPart+c.getImaginaryPart());
    }
    
    public void addToSelf(double num){
        realPart += num;
    }
    
    public void addToSelf(ComplexNumber c){
        realPart += c.getRealPart();
        imaginaryPart += c.getImaginaryPart();
    }
    
    public ComplexNumber subtract(double num){
        return new ComplexNumber(realPart-num,imaginaryPart);
    }
    
    public ComplexNumber subtract(ComplexNumber c){
        return new ComplexNumber(realPart-c.getRealPart(),imaginaryPart-c.getImaginaryPart());
    }
    
    public void subtractToSelf(double num){
        realPart -= num;
    }
    
    public void subtractToSelf(ComplexNumber c){
        realPart -= c.getRealPart();
        imaginaryPart -= c.getImaginaryPart();
    }
    
    public ComplexNumber multiply(double num){
        return new ComplexNumber(realPart*num,imaginaryPart*num);
    }
    
    public ComplexNumber multiply(ComplexNumber c){
        return new ComplexNumber(realPart*c.realPart - imaginaryPart*c.imaginaryPart,imaginaryPart*c.realPart + realPart*c.imaginaryPart);
    }
    
    public void multiplyToSelf(double num){
        realPart *= num;
        imaginaryPart *= num;
    }
    
    public void multiplyToSelf(ComplexNumber c){
        double realTemp = realPart;
        double imaginaryTemp = imaginaryPart;
        
        realPart = realTemp*c.realPart - imaginaryTemp*c.imaginaryPart;
        imaginaryPart = imaginaryTemp*c.realPart + realTemp*c.imaginaryPart;
    }
    
    public ComplexNumber divide(double num){
        return new ComplexNumber(realPart/num,imaginaryPart/num);
    }
    
    public ComplexNumber divide(ComplexNumber c){
        double realNum = realPart*c.realPart + imaginaryPart*c.imaginaryPart;
        double imagNum = imaginaryPart*c.realPart - realPart*c.imaginaryPart;
        double dem = c.realPart*c.realPart + c.imaginaryPart*c.imaginaryPart;
        
        return new ComplexNumber(realNum/dem,imagNum/dem);
    }
    
    public void divideToSelf(double num){
        realPart /= num;
        imaginaryPart /= num;
    }
    
    public void divideToSelf(ComplexNumber c){
        double realNum = realPart*c.realPart + imaginaryPart*c.imaginaryPart;
        double imagNum = imaginaryPart*c.realPart - realPart*c.imaginaryPart;
        double dem = c.realPart*c.realPart + c.imaginaryPart*c.imaginaryPart;
        
        realPart = realNum/dem;
        imaginaryPart = imagNum/dem;
    }
    
    public double absoluteValue(){
        return Math.hypot(realPart, imaginaryPart);
    }
    
    public double angle(){
        return Math.atan2(imaginaryPart, realPart);
    }
    
    public ComplexNumber conjucate(){
        return new ComplexNumber(realPart,-imaginaryPart);
    }
    
    public boolean inMandelbrotSet(int iterations){
        ComplexNumber z = getCopy();
        ComplexNumber c = getCopy();
        for (int i = 0; i < iterations; i++){
            if (clearlyDiverges(z)){
                return false;
            }
            else{
                z = MandelbrotIteration(z,c);
            }
        }
        return true;
    }
    
    private boolean clearlyDiverges(ComplexNumber c){
        //return c.absoluteValue() > 2;
        return c.realPart < -2 || c.realPart > 1 || c.imaginaryPart < -1 || c.imaginaryPart > 1;
    }
    
    private ComplexNumber MandelbrotIteration(ComplexNumber previous, ComplexNumber c){
        ComplexNumber square = previous.multiply(previous);
        return square.add(c);
    }
    
    private ComplexNumber getCopy(){
        return new ComplexNumber(realPart,imaginaryPart);
    }
    
    //static methods?
    
}
