package homework;

public class DocxDocConverter extends DocConverter {

    public DocxDocConverter() {
        super("docx"); //부모 클래스 생성자 이용
    }
    @Override
    public void save(String fileName) {
        System.out.println(fileName + "." + this.getExtension() + "로 변환해서 저장합니다.");
    }
}
