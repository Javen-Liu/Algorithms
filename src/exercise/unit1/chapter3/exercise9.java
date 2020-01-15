package exercise.unit1.chapter3;

import DataStructure.ImplByLinkedList.StackWithIterator;
import jdk.internal.dynalink.beans.StaticClass;

/**
 * @author 刘建雯
 * 输入  1+2)*3-4)*5-6)))
 * 输出  ((1+2)*((3-4)*(5-6)))
 */
public class exercise9 {
    public static void main(String[] args) {
        String input = "1 + 2 ) * 3 ) - 4 ) * 5 - 6 )";
        String output = CompleteBrackets(input);
        System.out.println(output);
    }

    public static String CompleteBrackets(String input){
        String[] param = input.split(" ");
        String operator = "+-*/";
        StackWithIterator<String> datastack = new StackWithIterator<>();
        StackWithIterator<String> oprstack = new StackWithIterator<>();
        StackWithIterator<String> outputstack = new StackWithIterator<>();
        for(String value:param){
            if(operator.contains(value)){
                oprstack.push(value);
            }else if(")".equals(value)){
                String data1 = datastack.pop();
                String data2 = datastack.pop();
                String operation = oprstack.pop();
                String data = "( " + data2 + " " + operation + " " + data1 + " )";
                datastack.push(data);
            }else{
                datastack.push(value);
            }
        }
        while(oprstack.size() != 0){
            String data1 = datastack.pop();
            String data2 = datastack.pop();
            String operation = oprstack.pop();
            String data = data2 + " " + operation + " " + data1;
            datastack.push(data);
        }
        while(datastack.size() != 0){
            outputstack.push(datastack.pop());
        }
        StringBuilder paramChanged = new StringBuilder();
        for(String value:outputstack){
            paramChanged.append(value);
        }
        String output = paramChanged.toString();
        return output;
    }
}
