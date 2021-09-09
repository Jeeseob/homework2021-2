package homework;

public class Main {
    public static void main(String[] args) {
        WordProcessor wp = new WordProcessor("new doc"); // 띄어쓰기도 파일 명에 포함. new doc.xxx
        wp.setSpellChecker(new EngSpellChecker());
        wp.addDocConverter(new DocxDocConverter());
        wp.addDocConverter(new PdfDocConverter());
        wp.addDocConverter(new OdtDocConverter());
        wp.checkSpelling();
        wp.convertDocTo("odt");
        wp.convertDocTo("pdf");
        wp.convertDocTo("docx");
        wp.convertDocTo("wps");
    }
}
