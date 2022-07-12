package program.components;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class RandomNumber {
    private int random;

    public RandomNumber() {
    }

    public int getRandom() {
        return random;
    }

    public void setRandom(){
        this.random = random;
    }
}
