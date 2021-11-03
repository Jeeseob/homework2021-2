public class Main {

    public static void main(String[] args) {
        PasswordInfo passwordInfo;
        DAO<PasswordInfo, String> passwordDAO = new PasswordDAOImpl("password");

        System.out.println("--- inserting...");
        passwordInfo = new PasswordInfo("https://www.sum.ac.kr", "smu", "abcde");
        passwordDAO.insert(passwordInfo);
        passwordInfo = new PasswordInfo("https://www.smu2.ac.kr", "smu2", "abcde");
        passwordDAO.insert(passwordInfo);

        System.out.println("--- finding all...");
        for (PasswordInfo pi : passwordDAO.findAll()) {
            System.out.println("reading... " + pi);
        }

        System.out.println("--- updating...");
        passwordInfo = passwordDAO.findAll().get(0);
        passwordInfo.setId("smu1");
        passwordDAO.update(passwordInfo.getKey(),passwordInfo);

        System.out.println("--- see if updated...");
        for (PasswordInfo pi : passwordDAO.findAll()) {
            System.out.println("reading... " + pi);
        }

        System.out.println("--- deleting...");
        passwordDAO.deleteByKey("https://www.smu2.ac.kr");  // or personDao.delete(p);

        System.out.println("--- finding all after deleting...");
        for (PasswordInfo pi : passwordDAO.findAll()) {
            System.out.println("reading... " + pi);
        }
    }
}
