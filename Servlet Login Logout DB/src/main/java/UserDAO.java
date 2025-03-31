import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public Connection jdbc_Con() throws SQLException, ClassNotFoundException {
        String jdbcURL = "jdbc:postgresql://localhost:5432/login_logout";
        String dbUser = "postgres";
        String dbPassword = "system";
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
        return connection;
    }

    public User checkLogin(String email, String password) throws SQLException, ClassNotFoundException {
        try (Connection con = jdbc_Con()) {
            String sql = "SELECT * FROM users WHERE email = ? and password = ?";
            try (PreparedStatement statement = con.prepareStatement(sql)) {
                statement.setString(1, email);
                statement.setString(2, password);
                try (ResultSet result = statement.executeQuery()) {
                    User user = null;
                    if (result.next()) {
                        user = new User();
                        user.setFullname(result.getString("fullname"));
                        user.setEmail(email);
                    }
                    return user;
                }
            }
        }
    }

    public boolean registerUser(User user) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO users (fullname,email, password) VALUES ( ? , ? , ? )";
        try (Connection connection = jdbc_Con(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getFullname());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            int rowsAffected = statement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            return rowsAffected > 0;
        }
    }

    public boolean authenticateUser(Connection connection, String email, String username, String password)
            throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}