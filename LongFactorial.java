import java.util.Arrays;

class LongFactorial{
    public static void main(String[] args){
         
        System.out.println(Factorial(15));
        
    }
    public static String Factorial(int n) {
        String result = "1";
        if(n<0){
            return null;
        }
        if(n==0){
            return result;
        }
        for(int i=1;i<=n;i++){
            result=bigMultiplication(result,i+"");
        }
        return result;
    }
    public static String bigMultiplication(String a,String b){
    
        String []summands= new String[Integer.parseInt(b)];
        Arrays.fill(summands,a);

        return bigSum(summands);
    }

    public static String bigSum(String[] sumands){
        if(sumands.length==0){
            return "0";
        }
        int carry=0;
        String result="";
        Integer subSum=0;
        int largestArray=getLargestArray(sumands);
        String[] reversedSumands=getReversedSummands(sumands);
        
        //making a sum
        for(int i=0;i<largestArray;i++){
            //column sum
            subSum=carry;
            for(String sumand:reversedSumands){
                subSum=isInBounds(i,sumand)?
                    subSum+ Integer.parseInt(sumand.charAt(i)+"") :subSum+0;
            }
            carry=getCarry(subSum);
            //ya es la ultima sub suma?
            result=i+1==largestArray?
                subSum.toString()+result :
                getPartialResult(subSum)+result;
            subSum=0;
        }   

        return result.length()==0?"0":result;
    }

    public static boolean isInBounds(int index,String  array){
        return (index >= 0) && (index < array.length());
    }

    public static int getLargestArray(String[] array){
        int max = 0;
        for(int i=0;i<array.length;i++){
            if(array[i].length()>max){
                max = array[i].length();
            }
        }
        return max;

    }
    public static String reverseString(String s){
        StringBuilder result= new StringBuilder();
        result.append(s);
        result.reverse();

        return result.toString();
    }

    public static int getPartialResult(int subSum){
        String partial=subSum+"";
        String partialResult=partial.substring(partial.length()-1);
        return Integer.parseInt(partialResult);
    }
    public static int getCarry(int subSum){
        
        String carry=subSum+"";
        if(carry.length()<2){
            return 0;
        }
        return Integer.parseInt(carry.substring(0,carry.length()-1));
    }
    public static String[] getReversedSummands(String[] sumands){
        String[] reversedSumands=new String[sumands.length];
        for(int i=0;i<sumands.length;i++){
            reversedSumands[i]=reverseString(sumands[i]);
        }
        return reversedSumands;
    }
}