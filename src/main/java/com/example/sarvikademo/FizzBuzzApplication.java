package com.example.sarvikademo;

public class FizzBuzzApplication {

    public static void main(String[] args){
        for(int i=1; i<=100 ; i++){
            String value = (i % 3 == 0 ? (i % 5 == 0 ? "FizzBuzz" : "Fizz") : (i % 5 == 0 ? "Buzz" : String.valueOf(i)));
            System.out.println(value);
        }
    }
}
