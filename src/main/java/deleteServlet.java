import Entity.Film;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class deleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        int id = Integer.parseInt(req.getParameter("id"));

        PreparedStatement statement = null;
        ResultSet set = null;

        jdbcUtils utils = jdbcUtils.getInstance();
        Connection connection = utils.getConnection();
        String sql = "delete from film where id= ? ";


        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    req.getSession().setAttribute("films", getfilm());
                    req.getRequestDispatcher("main.jsp").forward(req, resp);
                }
            }
        }
    }
    //电影列表
    public static List<Film> getfilm() {
        List<Film> films = new ArrayList<Film>();
        Statement statement = null;
        ResultSet set = null;
        jdbcUtils utils = jdbcUtils.getInstance();
        Connection connection = utils.getConnection();
        String sql = "select * from film";
        try {
            statement=connection.createStatement();
            set=statement.executeQuery(sql);
            while(set.next()){
                Film film=new Film();
                film.setTitle(set.getString("title"));
                film.setId(set.getInt("id"));
                film.setLanguage_name(set.getString("language_name"));
                film.setDescription(set.getString("description"));
                films.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            utils.free(set,statement,connection);
        }
        return  films;
    }
}
