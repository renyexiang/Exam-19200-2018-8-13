import java.sql.*;

public class jdbcUtils {
    private String url = "jdbc:mysql://localhost:3306/jdbc";
    private String user = "root";
    private String password = "root";
    private static jdbcUtils instance=null;
    private jdbcUtils(){}
    public static jdbcUtils getInstance(){
        if (instance==null){
            synchronized (jdbcUtils.class){
                if(instance==null){
                    instance=new jdbcUtils();
                }
            }
        }
        return instance;
    }
    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        Connection connection=null;
        try {
            connection= DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public void free(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null)
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

}
