package analizator.entities;

/**
 * Created by Vi on 19.11.2016.
 */
public class Word implements Comparable<Word> {
    private String value;
    private int kol;
    private String sign = "";
    private static Builder builder;

    private Word(String value, int size, int kol){
        this.value = value;
        this.kol=kol;
    }

    private Word() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getKol() {
        return kol;
    }

    public void setKol(int kol) {
        this.kol = kol;
    }

    public void incrKol(){
        this.kol++;
    }

    @Override
    public String toString(){
        return "[Значение = " + value + ", размер = " + value.length() + ", количество = " + kol + ", знак = "+ sign +"]\n";
    }

    public boolean equals(Word word){
        if(this.value.equals(word.getValue())){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean equals(String str){
        return this.getValue().equals(str);
    }

    @Override
    public int compareTo(Word word){
        return this.getKol() > word.getKol() ? -1 : word.getKol() == this.getKol() ? 0 : 1;
    }

    public static Builder newWordBuilder() {
        builder = new Word().new Builder();
        return builder;
    }

    public static Builder getWordBuilder() { return builder;   }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setValue(String value) {
            Word.this.value = value;
            return this;
        }

        public Builder setKol(int kol) {
            Word.this.kol = kol;
            return this;
        }

        public Builder incrKol(){
            Word.this.kol++;
            return this;
        }

        public Builder setSign(String sign) {
            Word.this.sign += sign;
            return this;
        }

        public Word build() {
            return Word.this;
        }

    }
}
