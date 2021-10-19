public class OdtDocConverter extends DocConverter {
    public OdtDocConverter() {
        super("odt");
    }

    @Override
    public void save(String fileName) {
        System.out.printf("%s.%s로 변환해서 저장합니다\n",
                fileName, getExtension());
    }
}

