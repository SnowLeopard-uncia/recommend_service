package pojo;

import lombok.NoArgsConstructor;


public class Word implements Comparable<Word> {
    String word;
    Double tf;
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public Double getTf() {
        return tf;
    }
    public void setTf(Double tf) {
        this.tf = tf;
    }
    public Word(String name, Double tf) {
        super();
        this.word = name;
        this.tf = tf;
    }
    public Word() {
        super();
    }
    @Override
    public String toString() {
        return "Word [name=" + word + ", tf=" + tf + "]";
    }
    public int compareTo(Word o) {    //重写排序方法，后面方便降序排序
        if (this.tf > o.tf)
            return -1;
        else if (this.tf < o.tf)
            return 2;
        return 0;
    }
}

