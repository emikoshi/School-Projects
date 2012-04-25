import java.util.Stack;
class RPNCalculator{ 
	
	Stack<Integer> MyStack = new Stack<Integer>();
	
    int x1, x2;

    public RPNCalculator() {}
        
    public void push(int number) {
    	MyStack.push(number);
    	
    }
    
    
    public void add() {
    	x2 = (MyStack.pop()).intValue();
    	x1 = (MyStack.pop()).intValue();
    	MyStack.push(new Integer(x1+x2));  
    	
    }
    
    public void subtract() {
    	x2 = (MyStack.pop()).intValue();
    	x1 = (MyStack.pop()).intValue();
    	MyStack.push(new Integer(x1-x2));  
    	
    }
    
    public void multiply() {
    	x2 = (MyStack.pop()).intValue();
    	x1 = (MyStack.pop()).intValue();
    	MyStack.push(new Integer(x1*x2));
    }
    
    public void divide() {
    	x2 = (MyStack.pop()).intValue();
    	x1 = (MyStack.pop()).intValue();
    	MyStack.push(new Integer(x1/x2));
    } 
    
    public int peek() { 
        return MyStack.peek();
    }


    
	
		public static void main(String[] args)
	    {

	        RPNCalculator calc = new RPNCalculator();
	        
	        calc.push(5);
	        System.out.println("This should be five:" +  calc.peek()); 
	        calc.push(4);
	        calc.add();
	        System.out.println("Answer was :" +  calc.peek()); // should be 9
	        calc.push(3);
	        calc.multiply();
	        System.out.println("Answer was :" +  calc.peek()); // should be 27
	        calc.push(-3);
	        calc.subtract();
	        System.out.println("Answer was :" +  calc.peek()); // should be 30
	        calc.push(5);
	        calc.divide();
	        System.out.println("Answer was :" +  calc.peek()); // should be 6
	        calc.push(0);
	        calc.multiply();  // zero out the calculator

	        for (int i = 1; i <= 100; i++) {
	        	calc.push(i);
	        }
	        
	        for (int i = 1; i <= 99; i++) {
	        	calc.add();
	        }
	        System.out.println("Sum from 1 through 100 is: " + calc.peek()); // 5050

	    }
	}
	


