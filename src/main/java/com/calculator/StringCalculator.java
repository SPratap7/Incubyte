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
            delimiter = createDelimiterRegex(tempDelimiter);
            content = matcher.group(2);
        }
        delimiterAndContent.add(delimiter);
        delimiterAndContent.add(content);
        return delimiterAndContent;
    }

    String createDelimiterRegex(String delimiter) {
        String[] delimiterParts = delimiter.split("]\\[");
        for (int i = 0; i < delimiterParts.length; i++) {
            delimiterParts[i] = delimiterParts[i].replace("[", "").replace("]", "");
        }
        StringBuilder regexBuilder = new StringBuilder();
        for (String part : delimiterParts) {
            if (!regexBuilder.isEmpty()) {
                regexBuilder.append("|");
            }
            regexBuilder.append(Pattern.quote(part));
        }
        return regexBuilder.toString();
    }

    int arraySum(String[] numbersArray) {
        int sum = 0;
        StringBuilder negativeString = new StringBuilder();
        for(String number : numbersArray) {
            int current = Integer.parseInt(number);
            if(current < 0){
                if(negativeString.isEmpty()){
                    negativeString.append(number);
                } else{
                    negativeString.append(",").append(number);
                }
            }
            if(current < 1000) {
                sum += current;
            }
        }
        if(!negativeString.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negativeString);
        }
        return sum;
    }
}
