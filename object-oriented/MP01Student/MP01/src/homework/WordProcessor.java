package homework;

import java.util.Map;
import java.util.HashMap;

public class WordProcessor {

    private ISpellChecker spellChecker;

    private Map<String, DocConverter> docConverters;

    private String fileName;

    public WordProcessor(String fileName) {
        this.fileName = fileName;
        docConverters = new HashMap<String, DocConverter>();
    }
    public void addDocConverter(DocConverter converter) {
        docConverters.put(converter.getExtension() , converter);
    }

    public void convertDocTo(String ext) {
        if (docConverters.containsKey(ext)) {
            docConverters.get(ext).save(fileName);
        }
    }

    public void setSpellChecker(ISpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }
    public void checkSpelling() {
        spellChecker.check();
    }

}
