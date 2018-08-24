
package CoreFunctions;

import java.util.LinkedList;
import java.util.Stack;

//add conversion for multipleParamFunctions! ( in constructor?)@@@@@@@@@@@@@@@@@@
public class ExprConverter
{

    private static void printLinkedList(LinkedList l){
        for (int i = 0; i < l.size(); i++){
            System.out.print((String)l.get(i) + "_");
        }
        System.out.println();
    }
    
    protected static LinkedList completeConverter(String s){
        masterChecker(s);
        
        s = removeSpaces(s);
        //System.out.println("After spaces:" + s);
        s = addSpecialConstants(s);
        //System.out.println("After addSpecialConstants:" + s);
        s = handleNegatives(s);
        //System.out.println("After handleNegatives:" + s);
        
        LinkedList tokens = toExprTokens(s);// use for pre prossessing too?
        //printLinkedList(tokens);
        if (concurrentOperationError(tokens)){
            throw new RuntimeException("Error: syntax");
        }
        
        tokens = insertTimes(tokens);
        tokens = secOpsToEnd(tokens);
        tokens = infixToPostfix(tokens);
        
        return tokens;
    }
    
    private static String removeSpaces(String s){
        String str = s;
        int symbolLocation = 0;
            boolean flag = false;
            while (!flag){
                flag = true;
                symbolLocation = str.indexOf(" ");
                if (symbolLocation >= 0){
                    str = new String(str.substring(0,symbolLocation) + str.substring(symbolLocation + 1));
                    flag = false;
                }
            }
            return str;
    }
    
    private static LinkedList toExprTokens(String s){
        LinkedList<String>  list = new LinkedList<>();
        for (int i = 0; i < s.length(); i++){
            if (Character.isLowerCase(s.charAt(i))){
                if(i + 1 == s.length() || !Character.isLowerCase(s.charAt(i+1))){
                    list.add(s.substring(i, i+1));
                    
                }
                else{
                    int j = i+1;
                    while(j + 1 != s.length() && Character.isLowerCase(s.charAt(j+1))){
                        j ++;
                    }
                    list.add(s.substring(i, j+1));
                    i = j;
                }
            }
            
            else if (isNumberChar(s.charAt(i))){
                if(i + 1 == s.length() || !isNumberChar(s.charAt(i+1))){
                    list.add(s.substring(i, i+1));
                    
                }
                else{
                    int j = i+1;
                    while(j + 1 != s.length() && isNumberChar(s.charAt(j+1))){
                        j ++;
                    }
                    //System.out.println(" a     " + s.substring(i, j+1));
                    list.add(s.substring(i, j+1));
                    i = j;
                }
            }
            
            
            
            else{
                list.add(s.substring(i, i+1));
            }

        }
        
        return list;
    }
    
    // swaps sin(...) to ...sin
    private static LinkedList secOpsToEnd(LinkedList l){
        LinkedList<String> s = (LinkedList<String>)l.clone();
        boolean flag = false;
        int endOfSecOp = 0;
        while(!flag){
            flag = true;
            for (int i = 0; i < s.size() - 1; i++){
                String token1 = (String) s.get(i);
                String token2 = (String) s.get(i+1);
                if (isSecondaryOperation(token1) && token2.equals("(")){
                    flag = false;
                    
                    endOfSecOp = getEndOfOp(s,i+1);
                    
                    
                    s.add(endOfSecOp+1, token1);
                    
                    s.remove(i);
                    

                    break;
                }
            }
        }
        return s;
    }
    
    private static int getEndOfOp(LinkedList s, int start){
        int pCounter = 1;
        start ++;
        while (pCounter > 0){
            String token = (String)s.get(start);
            if (token.equals("(")){
                pCounter ++;
            }
            
            if (token.equals(")")){
                pCounter --;
            }
            
            
            
            start ++;
        }
        return start - 1;
        
    }
    
    private static String addSpecialConstants(String s){
        String str = s;
            int symbolLocation = 0;
            boolean flag = false;
            while (!flag){
                flag = true;
                symbolLocation = str.indexOf("pi");
                if (symbolLocation >= 0){
                    str = new String(str.substring(0,symbolLocation) + Double.toString(Math.PI) + str.substring(symbolLocation + 2));
                    flag = false;
                }
                symbolLocation = str.indexOf("eu");
                if (symbolLocation >= 0){
                    str = new String(str.substring(0,symbolLocation) + Double.toString(Math.E) + str.substring(symbolLocation + 2));
                    flag = false;
                }
                
            }
            return str;
    }
    
    private static String handleNegatives(String s){
            String str = s;
            
            char currentChar = 0;
            
            for (int i = 0; i < str.length(); i++){
                currentChar = str.charAt(i);
                if (currentChar == '-'){
                    if (i == 0){
                        str = new String("0" + str);
                    }
                    else if (str.charAt(i - 1) == '('){
                        str = new String(str.substring(0,i) + "0" + str.substring(i));
                    }
                }
            }
            
            return str;
        }
    
    
    
    private static boolean isNumberChar(char c){
        return (Character.isDigit(c) || c == '.' || c == 'E');
    }
    

    
    private static LinkedList insertTimes(LinkedList l){
        
        LinkedList<String> s = (LinkedList<String>)l.clone();
        for (int i = 0; i < s.size() - 1; i++){
            String first = (String) s.get(i);
            String second = (String) s.get(i+1);
            if ((isNumber(first) && (isSecondaryOperation((second)) || isVariable(second) || second.equals("(")))
                    || (first.equals(")") && second.equals("("))
                    || (isVariable(first) && isVariable(second))){
                s.add(i+1, "*");
            }
        }
        return s;
    }
    
    protected static boolean isVariable(String s){
        return (s.length() == 1 && Character.isLetter(s.charAt(0)));
    }
    
    protected static boolean isNumber(String s){
        try{
            double d = Double.parseDouble(s);
        }
        catch(NumberFormatException e){
            return false;
        }
        return true;
    }
    
    protected static boolean isOperation(String s){
        return isPrimaryOperation(s) || isSecondaryOperation(s);
    }
    
    protected static boolean isPrimaryOperation(String s){
        if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("%") || s.equals("^") || s.equals("`"))
            return true;
        return false;
    }
    
    protected static boolean isSecondaryOperation(String s){
        switch(s){
            case "asin":
            case "acos":
            case "atan":
            case "acsc":
            case "asec":
            case "acot":
            case "sin":
            case "cos":
            case "tan":
            case "csc":
            case "sec":
            case "cot":
            case "sinh":
            case "cosh":
            case "tanh":
            case "sqrt":
            case "ln":
            case "abs":
            case "log":
            case "sprt":
            case "erf":
            case "gamma":
            case "sgn":
            case "floor":
            case "cei":
            case "round":
            case "rand":
            case "exp":
                return true;
            default:
                return false;
        }
    }
    
    
    private static LinkedList infixToPostfix (LinkedList input){
        LinkedList<String> ans = new LinkedList<>();
        
        //handle null case
        if (input.equals("")){
            return ans;
        }
            
        Stack<String> stack = new Stack<>();
        String current = "";
        
        // go through each letter of the input, following the algorithm given in the assignment description
        for (int i = 0; i < input.size(); i++){
            current = (String)input.get(i);
            //handle if input is a letter
            if ((isVariable(current) || isNumber(current) || isSecondaryOperation(current))){
                ans.add(current);
                continue;
            }

            // handle if input is a '('.
            if (current.equals("(")){
                stack.push(current);
                continue;
            }

            // handle if top of stack is null
            if (stack.isEmpty()){
                if(isOperation(current)){
                    stack.push(current);
                }
                else if(current.equals(")")){
                    throw new RuntimeException("error: unbalanced expresstion");
                }
                else{
                    throw new RuntimeException("illegal character");
                }
                continue;
            }
            //handle ')'
            if (current.equals(")")){
		boolean control = true;
		while (control){
                    if (stack.isEmpty()){
                        throw new RuntimeException("error: unbalanced expresstion");
                    }
                    else if (stack.peek().equals("(")){
                        stack.pop();
                        control = false;
                    }
                    else{
                        ans.add(stack.pop());
                    }
                }
                continue;
            }

            //handle '+' and '-'
            if (current.equals("+") || current.equals("-")){
                if (stack.peek().equals("(")){
                    stack.push(current);
                }
                else if (isOperation(stack.peek())){
                    while (!stack.isEmpty() && !stack.peek().equals("(")){
                        ans.add(stack.pop());
                    }
                    stack.push(current);
                }
                else{
                    throw new RuntimeException("error");
                }                            
                continue;
            }

            //handle '*' , '/' , and '%'
            if (current.equals("*") || current.equals("/") || current.equals("%")){
                if (stack.peek().equals("(") || stack.peek().equals("+") || stack.peek().equals("-")){
                    stack.push(current);
                }
                else if(stack.peek().equals("*") || stack.peek().equals("/") || stack.peek().equals("^") || stack.peek().equals("%") || stack.peek().equals("`") || isSecondaryOperation(stack.peek())){
                    while(!stack.isEmpty() && !stack.peek().equals("(") && !stack.peek().equals("+") && !stack.peek().equals("-")){
                        ans.add(stack.pop());
                    }
                    stack.push(current);
                }
                else{
                    throw new RuntimeException("error");
                }
                continue;
            }
            
            //handle '^'
            if (current.equals("^")){
                if(stack.peek().equals("(") || stack.peek().equals("+") || stack.peek().equals("-") || stack.peek().equals("*") || stack.peek().equals("/") || stack.peek().equals("%")){
                    stack.push(current);
                }
                else if(stack.peek().equals("^") || stack.peek().equals("`") || isSecondaryOperation(stack.peek())){
                    while(!stack.isEmpty() && !stack.peek().equals("(") && !stack.peek().equals("+") && !stack.peek().equals("-") && !stack.peek().equals("*") && !stack.peek().equals("/") && !stack.peek().equals("%")){
                        ans.add(stack.pop());
                    }
                    stack.push(current);
                }
                else{
                    throw new RuntimeException("error");
                }
                continue;
            }
                        
            //handle '`'
            if (current.equals("`")){
                if (stack.peek().equals("(") || stack.peek().equals("+") || stack.peek().equals("-") || stack.peek().equals("*") || stack.peek().equals("/") || stack.peek().equals("%") || stack.peek().equals("^")){
                    stack.push(current);
                }
                else if (stack.peek().equals("`") || isSecondaryOperation(stack.peek())){
                    while(!stack.isEmpty() && !stack.peek().equals("(") && !stack.peek().equals("+") && !stack.peek().equals("-") && !stack.peek().equals("*") && !stack.peek().equals("/") && !stack.peek().equals("%") && !stack.peek().equals("^")){
                        ans.add(stack.pop());
                    }
                    stack.push(current);
                }
                else{
                    throw new RuntimeException("error");
                }
                continue;
            }
                        
            //handle secondary operators
            if (isSecondaryOperation(current)){
                if (stack.peek().equals("(") || isPrimaryOperation(stack.peek())){
                    stack.push(current);
                }
                else if(isSecondaryOperation(stack.peek())){
                    while(!stack.isEmpty() && !stack.peek().equals("(") && !stack.peek().equals("+") && !stack.peek().equals("-") && !stack.peek().equals("*") && !stack.peek().equals("/") && !stack.peek().equals("%") && !stack.peek().equals("^") && !stack.peek().equals("`")){
                        ans.add(stack.pop());
                    }
                    stack.push(current);
                }
                else{
                    throw new RuntimeException("error");
                }
		continue;
            }


	}
        //end of for loop. Input is finished being scanned

        //empty the stack of operators
        while (!stack.isEmpty()){
            if (!stack.peek().equals("(")){
                ans.add(stack.pop());
            }
            else
                throw new RuntimeException("error: unbalanced expression: no ) at the end");
        }
        return ans;

    }

    //helper methods


    // tests if there are any characters in the code that shouldn't be there
    // goes through input in order and tests each character;
    private static boolean illegalCharacters(String input){
        char current;
        for (int i = 0; i < input.length(); i++){
            current = input.charAt(i);
            //check if it's a letter
            if ((current >= 'A' && current <= 'Z') || (current >= 'a' && current <= 'z') || isNumberChar(current)){
                continue;
            }
            else{
                switch (input.charAt(i)){
                //check if it's an operator
                case '+': case '-': case '*': case '/': case '(': case ')': case '^': case '`': case '%':
                    continue;
                default:
                    //System.out.println(current);
                    return true;
                }
            }
        }
        return false;
    }

    //tests to make sure the () are balanced
    private static boolean unbalancedParentheses(String input){
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++){
            if (input.charAt(i) == '('){
                stack.push("(");
                continue;
            }
            //if there is a ')', there should have been a '(' previously
            if (input.charAt(i) == ')' && !stack.isEmpty()){
                stack.pop();
                continue;
            }
            if (input.charAt(i) == ')' && stack.isEmpty())
                return true;
        }
        return (!stack.isEmpty());
    }

    //tests if there are two letters or two operators right next to eachother
    private static boolean concurrentOperationError(LinkedList<String> input)
    {
        for(int i = 0; i < input.size() - 1; i++){
            //System.out.println(input.get(i));
            if (isPrimaryOperation(input.get(i)) && isPrimaryOperation(input.get(i+1)))
                return true;
	}
        return false;
    }

    //tests to make sure the last and first non '()' character is not an operator
    private static boolean weirdEndOperators(String input)
    {
        //last character
        for (int i = input.length() - 1; i >= 0; i--){
            if (Character.isLetter(input.charAt(i)) || isNumberChar(input.charAt(i))){
                break;
            }
            
            if (isOperation(input.substring(i, i+1))){
                //System.out.println("a");
                return true;
            }
        }
                
        //first character
        for (int i = 0; i < input.length(); i++){
            if (Character.isLetter(input.charAt(i)) || isNumberChar(input.charAt(i))){
                break;
            }
            if (isOperation(input.substring(i, i+1))){
                //System.out.println("b");
                return true;
            }
        }
        
        //check ')a' case
        for (int i = 0; i < input.length() - 1; i++){
            if (input.charAt(i) == ')' && Character.isLetter(input.charAt(i + 1))){
                //System.out.println("c");
                return true;
            }
        }
        return false;
    }

    // does all checks at once
    private static void masterChecker(String input){
        //check for illegal characters
        if (illegalCharacters(input))
            throw new RuntimeException("Error: illegal character");
            
        // check for unbananced ()
        if (unbalancedParentheses(input))
            throw new RuntimeException("Error: unbalanced brackets");
            
        if (weirdEndOperators(input))
            throw new RuntimeException("Error: syntax");
    }

        
}

