package baking.alexshr.com.jokesource;

import java.util.Random;

public class JokesProvider {
    private static final String jokes[] = {
            "Hard work never killed anybody, but why take a chance?",
            "Why did the physics teacher break up with the biology teacher? There was no chemistry.",
            "I changed my password to \"incorrect\". So whenever I forget what it is the computer will say \"Your password is incorrect\"",
            "A naked women robbed a bank. Nobody could remember her face.", "I’m selling my talking parrot. Why? Because yesterday, the bastard tried to sell me."
    };

    public static String provideJoke() {
        int size = jokes.length;
        int index = new Random().nextInt(size);
        return jokes[index];
    }
}
