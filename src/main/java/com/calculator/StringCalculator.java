package com.calculator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    String numbers;

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public StringCalculator() {
        this.numbers = "";
    }

    public StringCalculator(String numbers) {
        this.numbers = numbers;
    }

    int add() {
        if(numbers.isBlank()) {
            return 0;
        } else {
            ArrayList<String> delimiterAndContent = findDelimiterAndContent(numbers);
            String delimiter = delimiterAndContent.get(0);
            if(!delimiter.equals(",")) {
                numbers = delimiterAndContent.get(1);
            }
            delimiter = Pattern.quote(delimiter);
            String[] numbersArray = numbers.split(delimiter + "|\n");
            return arraySum(numbersArray);
        }
    }

    ArrayList<String> findDelimiterAndContent(String content) {
        ArrayList<String> delimiterAndContent = new ArrayList<>();
        String delimiter = ",";
        String regex = "//(.*)\n(.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            String tempDelimiter = matcher.group(1);
            if(tempDelimiter.matches("\\[(.+)\\]")) {
                tempDelimiter = tempDelimiter.substring(1, tempDelimiter.length() - 1);
            }
            delimiter = tempDelimiter;
            content = matcher.group(2);
        }
        delimiterAndContent.add(delimiter);
        delimiterAndContent.add(content);
        return delimiterAndContent;
    }

    int arraySum(String[] numbersArray) {
        int sum = 0;
        StringBuilder negativeString = new StringBuilder();
        for(String number : numbersArray) {
            int current = Integer.parseInt(number);
            if(current < 0){
                negativeString.append(number).append(",");
            }
            if(current < 1000) {
                sum += current;
            }
        }
        if(!negativeString.isEmpty()) {
            negativeString.deleteCharAt(negativeString.length() - 1);
            throw new IllegalArgumentException("Negatives not allowed: " + negativeString);
        }
        return sum;
    }
}
