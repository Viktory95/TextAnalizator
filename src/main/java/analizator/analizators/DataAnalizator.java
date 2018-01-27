package analizator.analizators;

import analizator.entities.Word;

import java.util.List;

/**
 * Created by Vi on 22.11.2016.
 */
public class DataAnalizator {
    private String maxWord = "";
    private int minColWord;
    private String minWord = "";
    private int questionsCol = 0;
    private int extraCol = 0;
    private int wordsColWithoutProbs;
    private int wordsColWithProbs;
    private String mostLongWord;
    private String analizyingData;

    @Override
    public String toString() {
        if (analizyingData == null) {
            analizyingData = "Общая информация:"
                    + "\nСамое часто встречающиеся слова: " + maxWord
                    + "\nСамое редко употребляемые слова: " + minWord
                    + "\nСамое длинное слово - " + mostLongWord
                    + "\nКоличество слов с пробелами - " + wordsColWithProbs
                    + "\nКоличество слов без пробелов - " + wordsColWithoutProbs
                    + "\n! - " + extraCol
                    + "\n? - " + questionsCol;
        }
        return analizyingData;
    }

    public String getMaxWord() {
        return maxWord;
    }

    public void setMaxWord(String maxWord) {
        this.maxWord += maxWord;
    }

    public void setMaxWord(List<Word> maxWord) {
        maxWord.stream().forEach(str -> this.maxWord += str.getValue() + " - " + str.getKol() + ",\n");
    }

    public int getMinColWord() {
        return minColWord;
    }

    public void setMinColWord(int minColWord) {
        this.minColWord = minColWord;
    }

    public String getMinWord() {
        return minWord;
    }

    public void setMinWord(String minWord) {
        this.minWord += minWord;
    }

    public void setMinWord(List<Word> minWord) {
        minWord.stream().forEach(str -> this.minWord += str.getValue() + " - " + str.getKol() + ",\n");
    }

    public int getQuestionsCol() {
        return questionsCol;
    }

    public void setQuestionsCol(int questionsCol) {
        this.questionsCol += questionsCol;
    }

    public int getExtraCol() {
        return extraCol;
    }

    public void setExtraCol(int extraCol) {
        this.extraCol += extraCol;
    }

    public int getWordsColWithoutProbs() {
        return wordsColWithoutProbs;
    }

    public void setWordsColWithoutProbs(int wordsColWithoutProbs) {
        this.wordsColWithoutProbs = wordsColWithoutProbs;
    }

    public int getWordsColWithProbs() {
        return wordsColWithProbs;
    }

    public void setWordsColWithProbs(int wordsColWithProbs) {
        this.wordsColWithProbs = wordsColWithProbs;
    }

    public String getMostLongWord() {
        return mostLongWord;
    }

    public void setMostLongWord(String mostLongWord) {
        this.mostLongWord = mostLongWord;
    }

    public void setMostLongWord(Word word) {
        this.mostLongWord = word.getValue();
    }
}
