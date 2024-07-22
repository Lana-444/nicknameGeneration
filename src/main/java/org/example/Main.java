package org.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static AtomicInteger beautifulWord3 = new AtomicInteger();
    public static AtomicInteger beautifulWord4 = new AtomicInteger();
    public static AtomicInteger beautifulWord5 = new AtomicInteger();

    public static void main(String[] args) {


        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Thread palindromeChecker = new Thread(() -> palindromeCheck(texts));
        Thread sameLetterChecker = new Thread(() -> sameLetterCheck(texts));
        Thread increasingOrderChecker = new Thread(() -> increasingOrderCheck(texts));

        palindromeChecker.start();
        sameLetterChecker.start();
        increasingOrderChecker.start();

        try {
            palindromeChecker.join();
            sameLetterChecker.join();
            increasingOrderChecker.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Красивых слов с длиной 3: " + beautifulWord3.get() + " шт");
        System.out.println("Красивых слов с длиной 4: " + beautifulWord4.get() + " шт");
        System.out.println("Красивых слов с длиной 5: " + beautifulWord5.get() + " шт");
    }

    private static void increasingOrderCheck(String[] texts) {
        for (String text : texts) {
            if (text.length() == 3 && isIncreasing(text)) {
                beautifulWord3.incrementAndGet();
            } else if (text.length() == 4 && isIncreasing(text)) {
                beautifulWord4.incrementAndGet();
            } else if (text.length() == 5 && isIncreasing(text)) {

            }
        }
    }

    private static boolean isIncreasing(String text) {
        char[] chars = text.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] < chars[i - 1]) {
                return false;
            }
        }
        return true;
    }

    private static void sameLetterCheck(String[] texts) {
        for (String text : texts) {
            if (text.length() == 3 && isSameLetters(text)) {
                beautifulWord3.incrementAndGet();
            } else if (text.length() == 4 && isSameLetters(text)) {
                beautifulWord4.incrementAndGet();
            } else if (text.length() == 5 && isSameLetters(text)) {

            }
        }
    }

    private static boolean isSameLetters(String text) {
        char firstChar = text.charAt(0);
        for (char c : text.toCharArray()) {
            if (c != firstChar) {
                return false;
            }
        }
        return true;
    }

    private static void palindromeCheck(String[] texts) {
        for (String text : texts) {
            if (text.length() == 3 && isPalindrome(text)) {
                beautifulWord3.incrementAndGet();
            } else if (text.length() == 4 && isPalindrome(text)) {
                beautifulWord4.incrementAndGet();
            } else if (text.length() == 5 && isPalindrome(text)) {
                beautifulWord5.incrementAndGet();
            }
        }
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    private static boolean isPalindrome(String text) {
        int lengthText = text.length();
        for (int i = 0; i < lengthText / 2; i++) {
            if (text.charAt(i) != text.charAt(lengthText - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}



