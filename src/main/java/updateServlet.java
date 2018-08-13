import Entity.Film;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class updateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        int id=Integer.parseInt(req.getParameter("id")) ;
        String title=req.getParameter("title");
        String description=req.getParameter("description");
        String language=req.getParameter("language");



        PreparedStatement statement = null;

        jdbcUtils utils = jdbcUtils.getInstance();
        Connection connection = utils.getConnection();

        String sql="update film set title=?,description=?,language_name=? where id=?";
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,title);
            statement.setString(2,description);
            statement.setString(3,language);
            statement.setInt(4,id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(statement!=null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(connection!=null){
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    req.getSession().setAttribute("films",getfilm());
                    req.getRequestDispatcher("main.jsp").forward(req,resp);
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
