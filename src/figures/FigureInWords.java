package figures;

/**
 * Created by law on 9/20/15.
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FigureInWords {
    static int[] values = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 30, 40, 50, 60, 70, 80, 90, 100, 1000, 1000000, 1000000000};
    static String[] valueWords = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety", "hundred", "thousand", "million", "billion"};
    static List<Integer> valuesList = new ArrayList<>();
    static boolean isNegative = false;
    static int originalInput;
    static DecimalFormat decimalFormat;

    public static void main(String[] args){
        Scanner x = new Scanner(System.in);

        for(int i =  0; i < values.length; i++) valuesList.add(values[i]);

        System.out.println(valuesList);

        while(true){
            System.out.print("Enter a number to convert to words (or done to quit) : ");
            String userInput = x.nextLine();
            String valueInWords = "";
            int value = 0;
            if(userInput.equalsIgnoreCase("done")){
                System.out.println("\nThank you for using our service.");
                x.close();
                System.exit(0);
            }else{
                try{
                    value =  Integer.parseInt(userInput);
                    originalInput = value;
                }catch(NumberFormatException ex){
                    originalInput = 0;
                    valueInWords += "You entered a wrong value.";
                }
            }

            if(value < 0){
                isNegative = true;
                value *= -1;
            }

            if(value > 2147483647){
                System.out.println("Sorry, the largest number you can enter is;");
                value = 2147483647;
                originalInput = value;
            }
            if(value <= 2147483647 && value >= 1000000000){
                valueInWords += lessThanOneHundred(Integer.parseInt((value+"").substring(0, 1))) + " billion ";
                if(value > 2000000000) value -= 2000000000;
                else value -= 1000000000;
            }
            if(value < 1000000000 && value >= 1000000){
                int newValue = (value / 1000000);
                if(newValue < 1000 && newValue >= 100){
                    valueInWords += valueWords[valuesList.indexOf(newValue / 100)] +" hundred";
                    if ((newValue %= 100) > 0) valueInWords += " and ";
                }
                if(newValue < 100){
                    valueInWords += lessThanOneHundred(newValue);
                }
                valueInWords += " million ";
                value = Integer.parseInt((value+"").substring((value+"").length() - 6));
                if(value < 100) valueInWords += "and ";
            }
            if(value < 1000000 && value >= 1000){
                if(value > 100000){
                    int newValue = (value / 1000);
                    if(newValue < 1000 && newValue >= 100){
                        valueInWords += valueWords[valuesList.indexOf(newValue / 100)] +" hundred";
                        if ((newValue %= 100) > 0) valueInWords += " and ";
                    }
                    if(newValue < 100){
                        valueInWords += lessThanOneHundred(newValue);
                    }
                    valueInWords += " thousand ";
                }
                else if(value > 1000){
                    valueInWords += lessThanOneHundred(value / 1000) + " thousand ";
                    if(Integer.parseInt(((value - 1000)+"").substring(((value - 1000)+"").length() - 3)) < 100 && Integer.parseInt(((value - 1000)+"").substring(((value - 1000)+"").length() - 3)) > 0){
                        valueInWords += "and ";
                    }
                }
                else valueInWords += lessThanOneHundred(value / 1000) + " thousand ";

                value %= 1000;
            }
            if(value < 1000 && value >= 100){
                valueInWords += valueWords[valuesList.indexOf(value / 100)] +" hundred";
                if ((value %= 100) > 0) valueInWords += " and ";
            }
            if(value < 100){
                valueInWords += lessThanOneHundred(value);
            }

            if(isNegative) valueInWords = "negative " + valueInWords;
            if(valueInWords.length() == 0){
                valueInWords += " zero";
                decimalFormat = new DecimalFormat("###,###,###,###");
                System.out.println("\n" + decimalFormat.format(originalInput) + " => " + valueInWords + "\n");
            }else if(originalInput == 0){
                System.out.println("\n=> " + valueInWords + "\n");
            }else{
                decimalFormat = new DecimalFormat("###,###,###,###");
                System.out.println("\n" + decimalFormat.format(originalInput) + " => " + valueInWords + "\n");
            }
        }
    }

    public static String lessThanOneHundred(int value){
        String valueInWords = "";
        if(valuesList.contains(value)){
            valueInWords += valueWords[valuesList.indexOf(value)];
        }else if(value >= 20 && value < 30){
            valueInWords += valueWords[valuesList.indexOf(20)] +"-"+ valueWords[valuesList.indexOf(value - 20)];
        }else if(value >= 30 && value < 40){
            valueInWords += valueWords[valuesList.indexOf(30)] +"-"+ valueWords[valuesList.indexOf(value - 30)];
        }else if(value >= 40 && value < 50){
            valueInWords += valueWords[valuesList.indexOf(40)] +"-"+ valueWords[valuesList.indexOf(value - 40)];
        }else if(value >= 50 && value < 60){
            valueInWords += valueWords[valuesList.indexOf(50)] +"-"+ valueWords[valuesList.indexOf(value - 50)];
        }else if(value >= 60 && value < 70){
            valueInWords += valueWords[valuesList.indexOf(60)] +"-"+ valueWords[valuesList.indexOf(value - 60)];
        }else if(value >= 70 && value < 80){
            valueInWords += valueWords[valuesList.indexOf(70)] +"-"+ valueWords[valuesList.indexOf(value - 70)];
        }else if(value >= 80 && value < 90){
            valueInWords += valueWords[valuesList.indexOf(80)] +"-"+ valueWords[valuesList.indexOf(value - 80)];
        }else if(value >= 90 && value < 100){
            valueInWords += valueWords[valuesList.indexOf(90)] +"-"+ valueWords[valuesList.indexOf(value - 90)];
        }
        return valueInWords;
    }
}
