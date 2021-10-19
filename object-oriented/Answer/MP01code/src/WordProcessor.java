import java.util.HashMap;
import java.util.Map;

public class WordProcessor {
    private ISpellChecker spellChecker;
    private Map<String, DocConverter> converters;
    private String fileName;

    public WordProcessor(String fileName) {
        this.fileName = fileName;
        converters = new HashMap<String, DocConverter>();
    }

    public void addDocConverter(DocConverter converter) {
        converters.put(converter.getExtension(), converter);
    }

    public void convertDocTo(String ext) {
        if (converters.containsKey(ext)) {
            DocConverter c = converters.get(ext);
            c.save(fileName);
        }
    }

    public void setSpellChecker(ISpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    public void checkSpelling() {
        if (spellChecker != null) {
            spellChecker.check();
        }
    }
}
