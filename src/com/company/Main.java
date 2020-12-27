package com.company;


public class Main {

    public static void main(String[] args) {
        String text = "Я иду по парку, иду один и вижу как по воде пробегает луч солнца";

        MyMap<String, Integer> map = getWordsCount(text);
        System.out.println(map);
    }

    static MyMap<String, Integer> getWordsCount(String text) {
        MyMap<String, Integer> map = new MyMap<>();

        String[] words = text.split("(,*?\\s,*?)");

        for (String w : words
        ) {
            w = w.toLowerCase();
            if (!map.containsKey(w)) {
                map.put(w, 1);
            } else {
                int count = map.get(w);
                map.put(w, count + 1);
            }
        }

        return map;
    }

}
