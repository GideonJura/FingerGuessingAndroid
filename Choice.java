package org.opencv.rps;


public enum Choice{
		
    ROCK(0, "Rock", R.drawable.rock2, R.drawable.rock),
    PAPER(1, "Paper", R.drawable.paper2, R.drawable.paper),
    SCISSORS(2, "Scissors", R.drawable.scissors2, R.drawable.scissors),
    UNKNOWN(3, "Unknown", R.drawable.unknown, R.drawable.unknown);

    private final int value;
    private final String text;
    private final int userDrawble;
    private final int computerDrawble;
    private Choice(int value, String text, int userDrawble, int computerDrawable) {
        this.value = value;
        this.text = text;
        this.userDrawble = userDrawble;
        this.computerDrawble = computerDrawable;
    }

    public int getValue() {
        return value;
    }
    
    public String getText() {
        return text;
    }
    
    public int getUserDrawable() {
        return userDrawble;
    }
    
    public int getComputerDrawable() {
        return computerDrawble;
    }
}
