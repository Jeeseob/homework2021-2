package homework;

public abstract class DocConverter {
    private String ext; // 파일 포맷 확장자

    public DocConverter(String extension) {
        ext = extension;
    }

    public String getExtension() {
        return ext;
    }

    public abstract void save(String fileName);
}
