package com.sample.streams.spliterator;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CountWords {
    public static final String SENTENCE= " Nel mezzo del cammin di nostra vita " +
                                        "mi ritrovai in una selva oscura" +
                                        " ché la dritta via era smarrita ";
    public static void main(String args[]){
        CountWords countWords=new CountWords();

        // Word count iteratively using countWordsIteratively
        System.out.println("Found " + countWords.countWordsIteratively(SENTENCE) + " words using countWordsIteratively()");

        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
        System.out.println("Found " + countWords.countWords(stream) + " words using countWords()");
    }

    private int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }
    public int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }
}
