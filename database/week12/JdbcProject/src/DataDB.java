public class DataDB {
    private String id;
    private String title;
    private String company;
    private String releaseDate;
    private String contry;
    private String totalScreen;
    private String profit;
    private String totalNum;
    private String grade;

    public DataDB(String id, String title, String company, String releaseDate, String contry, String totalScreen, String profit, String totalNum, String grade) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.releaseDate = releaseDate;
        this.contry = contry;
        this.totalScreen = totalScreen;
        this.profit = profit;
        this.totalNum = totalNum;
        this.grade = grade;
    }

    public String getId() {
        return "'"+id+"'";
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return "'"+title+"'";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return "'"+company+"'";
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getReleaseDate() {
        return "'"+releaseDate+"'";
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getContry() {
        return "'"+contry+"'";
    }

    public void setContry(String contry) {
        this.contry = contry;
    }

    public int getTotalScreen() {
        return Integer.parseInt(totalScreen);
    }

    public void setTotalScreen(String totalScreen) {
        this.totalScreen = totalScreen;
    }

    public double getProfit() {
        return Double.parseDouble(profit);
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public int getTotalNum() {
        return Integer.parseInt(totalNum);
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getGrade() {
        return "'"+grade+"'";
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
