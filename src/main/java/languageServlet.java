import Entity.Language;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class languageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Statement statement=null;
        ResultSet set=null;
        jdbcUtils utils=jdbcUtils.getInstance();

        Connection connection=utils.getConnection();
        String sql="select * from language";
        List<Language> languages=new ArrayList<>();
        try {
            statement=connection.createStatement();
            set=statement.executeQuery(sql);
            while (set.next()){
                Language language=new Language();
                language.setId(set.getInt("id"));
                language.setName(set.getString("name"));
                languages.add(language);
            }
            Gson gson = new Gson();
            String json = gson.toJson(languages);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.append(json);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            utils.free(set,statement,connection);
        }

    }
}
