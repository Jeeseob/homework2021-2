public class PdfDocConverter extends DocConverter {
    public PdfDocConverter() {
        super("pdf");
    }

    @Override
    public void save(String fileName) {
        System.out.printf("%s.%s로 변환해서 저장합니다\n",
                fileName, getExtension());
    }
}
