public class DocxDocConverter extends DocConverter {
    public DocxDocConverter() {
        super("docx");
    }

    @Override
    public void save(String fileName) {
        System.out.printf("%s.%s로 변환해서 저장합니다\n",
                          fileName, getExtension());
    }
}
