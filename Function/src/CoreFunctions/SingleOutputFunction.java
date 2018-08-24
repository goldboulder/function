
package CoreFunctions;

import Other.Boundry1D;
import Other.MathPoint;
import Other.MathVector;
import java.util.Stack;
import java.util.LinkedList;
import java.util.LinkedHashSet;
import java.util.Set;


public class SingleOutputFunction implements Expression{
    private Set<Character> arguments;
    private Expression function;
    
    public SingleOutputFunction(String expression){
        arguments = new LinkedHashSet();
        LinkedList<String> postfix = ExprConverter.completeConverter(expression);
        
        Stack<Expression> stack = new Stack<>();
        //System.out.println("now in function constructor");
        Expression constFunction = null;
        Expression tempFunction1;
        Expression tempFunction2;
        
        for (int i = 0; i < postfix.size(); i++){
            String currentToken = (String) postfix.get(i);
            if (ExprConverter.isVariable(currentToken)){
                constFunction = new Variable(currentToken.charAt(0));
                stack.add(constFunction);
                if (!arguments.contains((Character)currentToken.charAt(0))){
                    arguments.add(currentToken.charAt(0));
                }
                //System.out.println("Variable detected:" + currentToken);
            }
            else if (ExprConverter.isNumber(currentToken)){
                constFunction = new Constant(Double.parseDouble(currentToken));
                stack.add(constFunction);
                //System.out.println("Number detected:" + currentToken);
            }
            else if(ExprConverter.isPrimaryOperation(currentToken)){
                tempFunction2 = stack.pop();
                tempFunction1 = stack.pop();
                switch (currentToken) {
                    case "+": constFunction = new Addition(tempFunction1,tempFunction2); break;
                    case "-": constFunction = new Subtraction(tempFunction1,tempFunction2); break;
                    case "*": constFunction = new Multiplication(tempFunction1,tempFunction2); break;
                    case "/": constFunction = new Division(tempFunction1,tempFunction2); break;
                    case "%": constFunction = new Modular(tempFunction1,tempFunction2); break;
                    case "^": constFunction = new Exponentiation(tempFunction1,tempFunction2); break;
                    case "`": constFunction = new Tetration(tempFunction1,tempFunction2); break;                
                    
                    default:
                        break;
                }
                stack.add(constFunction);
                //System.out.println("Primary Function detected:" + currentToken);
            }
            else if(ExprConverter.isSecondaryOperation(currentToken)){
                tempFunction1 = stack.pop();
                switch (currentToken) {
                    case "sin": constFunction = new Sine(tempFunction1); break;
                    case "cos": constFunction = new Cosine(tempFunction1); break;
                    case "tan": constFunction = new Tangent(tempFunction1); break;
                    case "sqrt": constFunction = new SquareRoot(tempFunction1); break;
                    case "ln": constFunction = new NaturalLog(tempFunction1); break;
                    case "abs": constFunction = new AbsoluteValue(tempFunction1); break;
                    case "log": constFunction = new CommonLog(tempFunction1); break;
                    case "sprt": constFunction = new SuperRoot(tempFunction1); break;
                    case "asin": constFunction = new ArcSine(tempFunction1); break;
                    case "acos": constFunction = new ArcCosine(tempFunction1); break;
                    case "atan": constFunction = new ArcTangent(tempFunction1); break;
                    case "erf": constFunction = new ErrorFunction(tempFunction1); break;
                    case "gamma": constFunction = new GammaFunction(tempFunction1); break;
                    case "csc": constFunction = new Cosecant(tempFunction1); break;
                    case "sec": constFunction = new Secant(tempFunction1); break;
                    case "cot": constFunction = new Cotangent(tempFunction1); break;
                    case "acsc": constFunction = new ArcCosecant(tempFunction1); break;
                    case "asec": constFunction = new ArcSecant(tempFunction1); break;
                    case "acot": constFunction = new ArcCotangent(tempFunction1); break;
                    case "sinh": constFunction = new HyperbolicSine(tempFunction1); break;
                    case "cosh": constFunction = new HyperbolicCosine(tempFunction1); break;
                    case "tanh": constFunction = new HyperbolicTangent(tempFunction1); break;
                    case "sgn": constFunction = new Sign(tempFunction1); break;
                    case "floor": constFunction = new Floor(tempFunction1); break;
                    case "cei": constFunction = new Ceiling(tempFunction1); break;
                    case "round": constFunction = new Round(tempFunction1); break;
                    case "rand": constFunction = new RandomFunction(tempFunction1); break;
                    case "exp": constFunction = new Exponential(tempFunction1); break;
                    default:
                        break;
                }
                stack.add(constFunction);
                //System.out.println("Secondary Function detected:" + currentToken);
            }
        }
        
        
        
        constFunction = stack.pop();
        this.function = constFunction;
        
    }
    
    public SingleOutputFunction(Expression function){
        //this.arguments = arguments;
        this.function = function;
        this.arguments = function.getVariables();
    }
    
    public Expression getExpression(){
        return function;
    }
    
    @Override
    public Set<Character> getVariables() {
        return function.getVariables();
    }
    
    public int numArguments(){
        return arguments.size();
    }
    
    public int dimensions(){
        return numArguments() + 1;
    }
    
    @Override
    public Double value(MathPoint p){
        //checkVariables(p);
        return function.value(p);
    }
    
    @Override
    public Double derivative(MathVector vector, MathPoint p){
        //checkVariables(p);
        return function.derivative(vector, p);
    }
    
    
    //checks to make sure there are no variables in a point that are not in the function
    /*
    public void checkVariables(MathPoint p){
        if (arguments.size() == 0){
            return;
        }
        
        char[] pointVariables = p.getDimensionNames();
        for (int i = 0; i < pointVariables.length; i++){
            if (!arguments.contains(pointVariables[i])){
                throw new RuntimeException("Unknown variable");
            }
        }
    }
*/
    
    @Override
    public String toString(){
        return function.toString();
    }

    @Override
    public Expression integral() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasVariable() {
        return function.hasVariable();
    }

    @Override
    public SingleOutputFunction derivative(char var) {
        if (arguments.contains(var) || arguments.isEmpty()){
            return function.derivative(var);
        }
        else{
            //return 0?
            throw new RuntimeException("Unknown variable");
        }
    }


    
    
    
}
