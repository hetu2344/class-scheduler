import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class SetupDatabase {

    private static final String regex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

    public static void main(String[] args) {
        setupDB();
    }

    public static void setupDB() {
        try {
            // createTable();

            formatCSVFiles();
            // tempMethod();

        } catch (PersistenceException pe) {
            pe.printStackTrace();
        }
    }

    private static void tempMethod() throws PersistenceException{
        try (Scanner in = new Scanner(new File("db2.sql"))) {
            // in.nextLine();
            BufferedWriter writer = new BufferedWriter(new FileWriter("db.sql", true));
            while (in.hasNextLine()) {
                String input = in.nextLine();
                writer.write(input + ";\n");
            }
            writer.close();
        } catch (FileNotFoundException e) {
            throw new PersistenceException("data files not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void formatCSVFiles() throws PersistenceException {
        String[] outputArr = new String[8];

        // try (Scanner in = new Scanner(new File("csv-files/fall-2024.csv"))) {
        //     in.nextLine();
        //     while (in.hasNextLine()) {
        //         outputArr = getOutputArray(in.nextLine());
        //         writeToSQLFile(convertToString(outputArr));
        //     }
        // } catch (FileNotFoundException e) {
        //     throw new PersistenceException("data files not found");
        // }

        try (Scanner in = new Scanner(new File("winter-2025(1).csv"))) {
            in.nextLine();
            while (in.hasNextLine()) {
                outputArr = getOutputArray(in.nextLine());
                writeToSQLFile(convertToString(outputArr));
            }
        } catch (FileNotFoundException e) {
            throw new PersistenceException("data files not found");
        }
    }


    private static void writeToSQLFile(String dataValues) {
        final String sql = "INSERT INTO Winter2025 (crn, section, title, course_code, instructor, start_time, end_time, days) VALUES ";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("db3.sql", true))) {
            
            writer.write(sql + "(" + dataValues + ");\n");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] getOutputArray(String data) {
        String tempStr, startTime, endTime;
        String[] lineArr, tempArr;
        String[] outputArr = new String[8];
        boolean checkDays = true;
        lineArr = data.split(regex);

        // CRN and section
        tempArr = lineArr[1].split(" ");
        String crn = tempArr[1].trim();
        crn = crn.substring(1, crn.length() - 1);
        outputArr[0] = crn;
        outputArr[1] = "\"" + tempArr[0].trim() + "\"";

        // Title
        if(lineArr[2].trim().charAt(0) == '\"'){
            outputArr[2] = lineArr[2].trim();
        } else {
            outputArr[2] = "\"" +lineArr[2].trim() + "\"";
        }

        // Course code
        outputArr[3] = "\"" + lineArr[0].trim() + "\"";

        // Instructor
        outputArr[4] = "\"" + lineArr[5].trim() + "\"";

        // Start Time and End Time
        tempArr = lineArr[4].split(",");
        if (tempArr.length == 2) {
            tempArr = tempArr[1].trim().split(" ");
            tempArr = tempArr[0].split("-");
            if (tempArr.length == 2) {
                startTime = tempArr[0].trim();
                startTime = startTime.substring(0, 2) + startTime.substring(3, 5);
                endTime = tempArr[1].trim();
                endTime = endTime.substring(0, 2) + endTime.substring(3, 5);

            } else {
                startTime = "NULL";
                endTime = "NULL";
                checkDays = false;
            }
        } else {
            startTime = "NULL";
            endTime = "NULL";
            checkDays = false;
        }

        outputArr[5] = startTime;
        outputArr[6] = endTime;

        // Days
        tempArr = lineArr[4].split(",");
        if (checkDays && tempArr.length == 2) {
            tempArr = tempArr[1].trim().split(" ");
            tempStr = tempArr[1].substring(1, tempArr[1].length() - 2).trim();
            if(tempStr.trim().isEmpty()){
                outputArr[7] = "NULL";
            } else {
                outputArr[7] = "\"" + tempStr + "\"";
            }
        } else {
            outputArr[7] = "NULL";
            checkDays = true;
        }
        return outputArr;
    }

    private static String convertToString(String[] arr) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                sb.append(arr[i] + ", ");
            } else {
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }

    // private static Connection getConnection() throws PersistenceException{
    // Connection c;
    // try{
    // c = DriverManager.getConnection(Services.getDbURL(), Services.getDbUser(),
    // Services.getUserPassword());
    // } catch(SQLException sqle){
    // throw new PersistenceException("Error occured while establishing
    // connection");
    // }
    // return c;
    // }

    // private static void createTable() throws PersistenceException{

    // dropTables();
    // Connection c = getConnection();

    // try{
    // String sql = "CREATE TABLE FallTerm (crn INTEGER PRIMARY KEY, section
    // VARCHAR(255), title TEXT, course_code VARCHAR(255), instructor VARCHAR(255),
    // start_time TIME, end_time TIME, days VARCHAR(255))";
    // Statement stmt = c.createStatement();
    // stmt.executeUpdate(sql);
    // } catch(SQLException sqle){
    // throw new PersistenceException("Error occured while creating table");
    // }
    // }

    // private static void dropTables() throws PersistenceException{

    // Connection c = getConnection();

    // try{
    // String sql = "DROP TABLE IF EXISTS FallTerm";
    // Statement stmt = c.createStatement();
    // stmt.executeUpdate(sql);
    // } catch(SQLException sqle){
    // throw new PersistenceException("Error occured while droping table");
    // }
    // }

    // private static void insertData(String[] values) throws PersistenceException{
    // Connection c = getConnection();

    // try{

    // String sql = "INSERT INTO VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    // PreparedStatement pstmt = c.prepareStatement(sql);

    // pstmt.setInt(1, Integer.parseInt(values[0]));
    // pstmt.setString(2, values[1]);
    // pstmt.setString(3, values[2]);
    // pstmt.setString(4, values[3]);
    // pstmt.setString(5, values[4]);
    // pstmt.setTime(6, getSQLTime(values[5]));
    // pstmt.setTime(7, getSQLTime(values[6]));
    // pstmt.setString(8, values[7]);

    // pstmt.executeUpdate();

    // } catch(SQLException sqle){
    // throw new PersistenceException("Error occured while inserting into table");
    // } catch(PersistenceException pe){
    // throw pe;
    // }
    // }

    // private static Time getSQLTime(String timeStr) throws PersistenceException{
    // Time sqlTime;
    // try {
    // // Define the time formatter
    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    // // Parse the time string to a LocalTime object
    // LocalTime localTime = LocalTime.parse(timeStr, formatter);

    // // Convert LocalTime to java.sql.Time
    // sqlTime = Time.valueOf(localTime);

    // // Print the java.sql.Time object
    // System.out.println("Converted java.sql.Time: " + sqlTime);

    // } catch (DateTimeParseException e) {
    // throw new PersistenceException("Invalid time format: " + e.getMessage());
    // }

    // return sqlTime;
    // }

}
