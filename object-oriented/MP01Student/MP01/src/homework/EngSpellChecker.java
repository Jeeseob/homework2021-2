package homework;

public class EngSpellChecker implements ISpellChecker {
    final String CHECKING_MESSAGE = "Checking English Spelling...";

    @Override
    public void check() {
        System.out.println(CHECKING_MESSAGE);
    }
}
