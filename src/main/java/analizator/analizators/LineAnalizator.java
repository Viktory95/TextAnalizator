package analizator.analizators;

import analizator.entities.Word;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Vi on 22.11.2016.
 */
public class LineAnalizator {

    private List<String> signsList = Arrays.asList(".", ",", ":", ";", "!", "?", "-");
    private String exValues = "—-";
    private Map<String, Word> wordsMap;
    boolean containFlag = false;
    DataAnalizator dataAnalizator;

    public LineAnalizator() {
        wordsMap = new LinkedHashMap<>();
    }

    public void parseWords(String str) {

        Stream.of(str.split(" ")).filter(v -> !exValues.contains(v))
                .forEach(v -> {
                    if (!wordsMap.containsKey(getWithoutSign(v))) {
                        wordsMap.put(getWithoutSign(v), Word.newWordBuilder()
                                .setValue(getWithoutSign(v))
                                .setSign(getSign(v))
                                .setKol(1)
                                .build());
                    } else {
                        wordsMap.get(getWithoutSign(v)).getWordBuilder().incrKol().setSign(getSign(v)).build();
                    }
                });

       /* String[] words = str.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (!exValues.contains(words[i])) {
                String value = getWithoutSign(words[i]);
                containFlag = false;
                for (Word wordFromMap : wordsList) {
                    if (wordFromMap.getValue().equals(value)) {
                        wordFromMap.incrKol();
                        wordFromMap.setSign(wordFromMap.getSign() + getSign(words[i]));
                        containFlag = true;
                        break;
                    }
                }
                if (!containFlag) {
                    wordsList.add(new Word().newWordBuilder()
                            .setValue(value)
                            .setSign(getSign(words[i]))
                            .setKol(1)
                            .build());
                }
            }
        }*/
    }

    public String calculateMaxWord() {
        if (dataAnalizator == null) {
            dataAnalizator = new DataAnalizator();

            List<Word> wordsList = wordsMap.values().stream().sorted(Comparator.comparing(Word::getValue)).collect(Collectors.toList());

            int miMaxWordsCount = wordsList.size() < 10 ? wordsList.size() / 2 - 1 : 10;

            dataAnalizator.setMaxWord(wordsList.stream().limit(miMaxWordsCount).collect(Collectors.toList()));

            dataAnalizator.setMinWord(wordsList.stream().skip(wordsList.size() - miMaxWordsCount).limit(miMaxWordsCount).collect(Collectors.toList()));

            dataAnalizator.setMostLongWord(wordsList.stream()
                    .max((v1, v2) -> v1.getValue().length() > v2.getValue().length() ? 1 : 0)
                    .orElse(Word.newWordBuilder()
                            .setValue("").build()));

            wordsList.stream().forEach(v -> {
                if (v.getSign().contains("?")) {
                    dataAnalizator.setQuestionsCol(1);
                } else if (v.getSign().contains("!")) {
                    dataAnalizator.setExtraCol(1);
                }
            });

            dataAnalizator.setWordsColWithoutProbs(wordsList.size());
            dataAnalizator.setWordsColWithProbs((wordsList.size() * 2 - 1));
        }
        return dataAnalizator.toString();
    }

    private String getSign(String str) {
        String sign1 = "";
        for (String sign : signsList) {
            if (str.contains(sign)) {
                sign1 = sign;
                break;
            }
        }
        return sign1;
    }

    private String getWithoutSign(String str) {
        String wtSign = str;
        for (String sign : signsList) {
            if (wtSign.contains(sign)) {
                wtSign = wtSign.substring(0, wtSign.length() - 1);
                break;
            }
        }
        return wtSign;
    }

    public List<Word> getWordsList() {
        return new ArrayList(wordsMap.values());
    }
}
