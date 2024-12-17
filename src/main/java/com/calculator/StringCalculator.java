package com.calculator;

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
            String[] numbersArray = numbers.split(",");
            int sum = 0;
            for(String number : numbersArray) {
                int current = Integer.parseInt(number);
                sum += current;
            }
            return sum;
        }
    }
}
