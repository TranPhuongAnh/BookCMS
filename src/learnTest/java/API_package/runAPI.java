package API_package;

import kong.unirest.JsonNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class runAPI extends commonAPI{
    public static String token_login() throws ParseException, IOException {
        String urlLogin = "https://book-cms-api.vmgmedia.vn/v1/public/auth/login";
        Object body = new JSONParser().parse(new FileReader("src/learnTest/java/API/bodyLogin.json"));

        JsonNode json = runAPI.getToken(urlLogin, body);
//        System.out.println("In response của api trả về: \n" + json);

        JSONObject data = runAPI.getJsonKey_Obj(json, "data");
//        System.out.println("data: " + data);

        String token = runAPI.getJsonKey_String(data, "accessToken");
//        System.out.println("token: " + token);

        boolean isFirstLogin = runAPI.getJsonKey_boolean(data, "isFirstLogin[0]");
//        System.out.println("isFirstLogin: " + isFirstLogin);

        long id = runAPI.getJsonKey_long(data, "id");
//        System.out.println("id: " + id);

        return token;
    }

    public static List<String> data_category_field() throws ParseException, IOException {
        String urlAPI = "https://book-cms-api.vmgmedia.vn/v1/category?";
        String token = token_login();

        JsonNode json = runAPI.getApi(urlAPI, token);
        JSONObject data = runAPI.getJsonKey_Obj(json,"data");
        // Lấy giá trị 1 mảng obj trong 1 obj cha: JSONArray hoặc List<JSONObject>
        JSONArray items = (JSONArray) data.get("items");
        //Khai báo chuỗi kết quả trả về
        List<String> strings = new ArrayList<>();
        for (int i = 0 ; i < items.size() ; i++){
            JSONObject obj = (JSONObject) items.get(i);
            //Lấy tên của categori cha
            String name = (String) obj.get("name");
            strings.add(name);
            // Lấy tên của category con
            JSONArray children = (JSONArray) obj.get("children");
            if (String.valueOf(children) != null){
                for (int j = 0 ; j < children.size() ; j++){
                    JSONObject objChildren = (JSONObject) children.get(j);
                    String nameChildren = (String) objChildren.get("name");
                    strings.add(nameChildren);
                }
            }
        }

        return strings;
    }

    public static void main(String[] args) throws IOException {
        try {
            String s = runAPI.token_login();
            List<String> list = runAPI.data_category_field();
            System.out.println(list);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
