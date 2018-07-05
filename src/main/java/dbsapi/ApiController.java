package dbsapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@RestController
public class ApiController {

    @Autowired
    private Environment env;

    @RequestMapping(value="sendCode/{code}", method=RequestMethod.GET)
    public JsonResponse sendCode(@PathVariable String code) throws SQLException {

        Connection connection = ConnectionSingleton.getInstance().getConnection(env);

        String selectSql = "SELECT algo FROM algo.otro WHERE tab IN ('A') AND CODE1 = '" + code + "'";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(selectSql);

        JSONArray json = new JSONArray();
        ResultSetMetaData rsmd = rs.getMetaData();
        JSONObject obj = new JSONObject();

        while(rs.next()) {
            int numColumns = rsmd.getColumnCount();
            for (int i=1; i<=numColumns; i++) {
                String column_name = rsmd.getColumnName(i);
                obj.put(column_name, rs.getObject(column_name));
            }
        }

        return new JsonResponse(obj);
    }
}
