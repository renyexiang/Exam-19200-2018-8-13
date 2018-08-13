import Entity.Film;
import Entity.Language;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        Statement statement=null;
        ResultSet set=null;
        jdbcUtils utils=jdbcUtils.getInstance();

        String userName=req.getParameter("userName");
        Connection connection=utils.getConnection();
        String sql="select * from customer";

        boolean flag=false;
        try {
            statement=connection.createStatement();
            set=statement.executeQuery(sql);
            while (set.next()){
                if(userName.equals(set.getString("firstname"))){
                   flag=true;
                   break;
                }
            }
            if(flag){
                utils.free(set,statement,connection);
                //设置参数，得到所有的电影
                req.getSession().setAttribute("films",getfilm());
                req.getSession().setAttribute("languages",getLanguages());
                req.getSession().setAttribute("userName",userName);
                resp.sendRedirect("main.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

public static List<Language> getLanguages(){

    List<Language> languages = new ArrayList<Language>();
    Statement statement = null;
    ResultSet set = null;
    jdbcUtils utils = jdbcUtils.getInstance();
    Connection connection = utils.getConnection();
    String sql = "select * from language";
    try {
        statement=connection.createStatement();
        set=statement.executeQuery(sql);
        while(set.next()){
            Language language=new Language();
            language.setId(set.getInt("id"));
            language.setName(set.getString("name"));
            languages.add(language);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }finally {
        utils.free(set,statement,connection);
    }
    return  languages;
}
}
