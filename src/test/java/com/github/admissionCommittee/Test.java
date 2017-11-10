package com.github.admissionCommittee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Test {

    public static void main(String[] args) throws Exception {
        new Test().createScript();

    }

    private void createScript() throws Exception {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
        Statement statement = conn.createStatement();

        String initDB = "-- noinspection SqlNoDataSourceInspectionForFile\n" +
                "DROP TABLE IF EXISTS meals;\n" +
                "\n" +
                "DROP SEQUENCE IF EXISTS global_seq;\n" +
                "\n" +

                "\n" +
                "CREATE TABLE users\n" +
                "(\n" +
                "  user_id    VARCHAR PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                "  faculty_id VARCHAR NOT NULL,\n" +
                "  userRole       VARCHAR NOT NULL,\n" +
                "  first_name       VARCHAR NOT NULL,\n" +
                "  last_name       VARCHAR NOT NULL,\n" +
                "  patronymic       VARCHAR NOT NULL,\n" +
                "  mail      VARCHAR NOT NULL,\n" +
                "  password   VARCHAR NOT NULL,\n" +
                "  age INTEGER NOT NULL\n" +
                ");\n";
        String populateDB = "DELETE FROM users;\n" +
                "ALTER SEQUENCE global_seq RESTART WITH 100000;\n" +
                "\n" +
                "--password\n" +
                "INSERT INTO users (faculty_id, userRole, first_name, last_name, patronymic, " +
                "mail, password, age)\n" +
                "VALUES\n" +
                "('faculty_1', 'USER', 'Ivan','Ivanov','Ivanovich','testMail1@yandex.ru', " +
                "'cnHFJKcb',23),\n" +
                "('faculty_1', 'USER', 'Ivan','Ivanov','Ivanovich','testMail2@yandex.ru', " +
                "'cnHFJKcb',23),\n" +
                "('faculty_1', 'USER', 'Ivan','Ivanov','Ivanovich','testMail3@yandex.ru', " +
                "'cnHFJKcb',23),\n" +
                "('faculty_1', 'USER', 'Ivan','Ivanov','Ivanovich','testMail4@yandex.ru', " +
                "'cnHFJKcb',23),\n" +
                "('faculty_1', 'USER', 'Ivan','Ivanov','Ivanovich','testMail5@yandex.ru', " +
                "'cnHFJKcb',23),\n" +
                "('faculty_1', 'USER', 'Ivan','Ivanov','Ivanovich','testMail6@yandex.ru', " +
                "'cnHFJKcb',23),\n" +
                "('faculty_1', 'USER', 'Ivan','Ivanov','Ivanovich','testMail7@yandex.ru', " +
                "'cnHFJKcb',23),\n" +
                "('faculty_1', 'USER', 'Ivan','Ivanov','Ivanovich','testMail8@yandex.ru', " +
                "'cnHFJKcb',23),\n" +
                "('faculty_1', 'USER', 'Ivan','Ivanov','Ivanovich','testMail9@yandex.ru', " +
                "'cnHFJKcb',23),\n" +
                "('faculty_1', 'USER', 'Ivan','Ivanov','Ivanovich','testMail10@yandex.ru', " +
                "'cnHFJKcb',23),\n" +
                "('faculty_1', 'USER', 'Ivan','Ivanov','Ivanovich','testMail11@yandex.ru', " +
                "'cnHFJKcb',23),\n" +
                "('faculty_1', 'USER', 'Ivan','Ivanov','Ivanovich','testMail12@yandex.ru', " +
                "'cnHFJKcb',23),\n" +
                "('faculty_1', 'USER', 'Ivan','Ivanov','Ivanovich','testMail13@yandex.ru', " +
                "'cnHFJKcb',23);\n" +
                "\n";

        statement.execute(initDB);
        statement.execute(populateDB);
        statement.execute("SCRIPT TO 'script.sql'");
        conn.close();
    }

    /*void initDb() throws Exception {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
        InputStream in = getClass().getResourceAsStream("script.sql");
        if (in == null) {
            System.out.println("Please add the file script.sql to the
                    classpath, package" + getClass().getPackage().getName());
        } else {
            RunScript.execute(conn, new InputStreamReader(in));
            ResultSet rs = conn.createStatement().executeQuery("SELECT
                    * FROM TEST");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            conn.close();
        }
    }*/
}