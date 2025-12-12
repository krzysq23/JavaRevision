package revision.jdbc;

import revision.MyProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLExample {

    /*
        CRUD - create, read, update, delete
        DBMS - system zarządzania bazą dancyh (np. PostgreSQL, MySQL)
        JDBC - Java Database Structured
        JPA - Java Persistence API
        RDBMS - Relational Database Management System
        Data Integrity:
            - entity integrity - w jednej tabeli nie moga powtarzac sie takie same klucze
            - domain integrity - tylko jeden typ dla kolumny
            - referential integrity - nie mozna usuwać wpisu powiązanego z innym
            - user-defined integrity - integracja ustawień danych na kolumnach
        Constraints - ograniczenia na kolumnach
            - NOT NULL
            - UNIQUE
            - DEFAULT
            - CHECK - sprawdzenie formatu (np.JSON)
            - PRIMARY KEY
            - FOREIGN KEY
        DDL - Data Definition Language - definicje do tworzenia tabel
     */

    public static void main(String[] args) {

        String address = MyProperties.getProperty("spring.datasource.url");
        String username = MyProperties.getProperty("spring.datasource.username");
        String password = MyProperties.getProperty("spring.datasource.password");
        try(
                Connection connection = DriverManager.getConnection(address, username, password);
                Statement statement = connection.createStatement();
        ) {
            String query1 = "INSERT INTO PRODUCER (ID, PRODUCER_NAME, ADDRESS) " +
                    "VALUES (21, 'Test Group', 'Testowa 15, Warszawa');";
            String query2 = "UPDATE PRODUCER SET ADDRESS = 'Nowy adres naszej siedziby' WHERE ID = 21;";
            String query3 = "DELETE FROM PRODUCER WHERE ID = 21;";
            String query4 = "SELECT * FROM CUSTOMER WHERE NAME LIKE ?;";
            String query5 = "DELETE FROM PRODUCER WHERE ID = ?;";

            //executeUpdate
            Optional.of(statement.executeUpdate(query1))
                    .ifPresent(result -> System.out.printf("Inserted %s row(s)%n", result));
            Optional.of(statement.executeUpdate(query2))
                    .ifPresent(result -> System.out.printf("Updated %s row(s)%n", result));
            Optional.of(statement.executeUpdate(query3))
                    .ifPresent(result -> System.out.printf("Deleted %s row(s)%n", result));

            // preparedStatement
            PreparedStatement ps = connection.prepareStatement(query5);
            ps.setInt(1, 20);
            int count1 = ps.executeUpdate();
            System.out.println("changed: " + count1 + " rows");

            // ResultSet
            PreparedStatement preparedStatement = connection.prepareStatement(query4);
            preparedStatement.setString(1, "%me%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Customer> customers = mapToCustomers(resultSet);
                customers.forEach(customer -> System.out.println("Customer: " + customer));
            }

        } catch (SQLException e) {
            System.err.printf("Error getSQLState: [%s], getErrorCode: [%s], getMessage: [%s]",
                    e.getSQLState(), e.getErrorCode(), e.getMessage() );
        }

    }

    private static List<Customer> mapToCustomers(ResultSet resultSet) {
        List<Customer> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(
                        new Customer(
                                resultSet.getInt("id"),
                                resultSet.getString("user_name"),
                                resultSet.getString("email"),
                                resultSet.getString("surname"),
                                resultSet.getString("date_of_birth"),
                                resultSet.getString("telephone_number")
                        )
//                new Customer(
//                        resultSet.getInt(1),
//                        resultSet.getString(2),
//                        resultSet.getString(3),
//                        resultSet.getString(4),
//                        resultSet.getString(6),
//                        resultSet.getString(7)
//                )
                );
            }
        } catch (SQLException e) {
            System.err.printf("Error getSQLState: [%s], getErrorCode: [%s], getMessage: [%s]",
                    e.getSQLState(), e.getErrorCode(), e.getMessage() );
        }
        return result;
    }

    private static class Customer {

        private final Integer id;
        private final String userName;
        private final String email;
        private final String surname;
        private final String dateOfBirth;
        private final String telephoneNumber;

        public Customer(Integer id, String userName, String email, String surname, String dateOfBirth, String telephoneNumber) {
            this.id = id;
            this.userName = userName;
            this.email = email;
            this.surname = surname;
            this.dateOfBirth = dateOfBirth;
            this.telephoneNumber = telephoneNumber;
        }

        public Integer getId() {
            return id;
        }

        public String getUserName() {
            return userName;
        }

        public String getEmail() {
            return email;
        }

        public String getSurname() {
            return surname;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public String getTelephoneNumber() {
            return telephoneNumber;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "id='" + id + '\'' +
                    ", userName='" + userName + '\'' +
                    ", email='" + email + '\'' +
                    ", surname='" + surname + '\'' +
                    ", dateOfBirth='" + dateOfBirth + '\'' +
                    ", telephoneNumber='" + telephoneNumber + '\'' +
                    '}';
        }
    }
}
