package homework;

import java.util.Map;
import java.util.HashMap;

public class WordProcessor {

    private ISpellChecker spellChecker;

    private Map<String, DocConverter> docConverters;

    private String fileName;

    public WordProcessor(String fileName) {
        this.fileName = fileName;
        docConverters = new HashMap<String, DocConverter>(); // class 생성시 HashMap 생성
    }
    public void addDocConverter(DocConverter converter) {
        docConverters.put(converter.getExtension() , converter); // converter의 ext를 key값으로 사용
    }

    public void convertDocTo(String ext) {
        if (docConverters.containsKey(ext)) {
            docConverters.get(ext).save(fileName);
            // get함수로 value값을 가져올때 바로 value값(object)으로 적용됨. 바로 함수 사용가능.
        }
    }

    public void setSpellChecker(ISpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }
    public void checkSpelling() {
        spellChecker.check(); // 단순 메세지 출력
    }

}
