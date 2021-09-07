package homework;

public class EngSpellChecker implements ISpellChecker {
    final String CHECKING_MESSAGE = "Checking English Spelling...";

    @Override
    public void check() {
        System.out.println(CHECKING_MESSAGE); //실제로 spelling을 체크하지는 않음. (과제 요구사항 참고)
    }
}
