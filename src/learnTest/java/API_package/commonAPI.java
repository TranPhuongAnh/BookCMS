package API_package;

import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

public class commonAPI {

    /**
     * POST request: Thực hiện gửi request để lấy accessToken
     * @param api
     * @param body
     * @return
     */
    public static JsonNode getToken(String api, Object body){
        JsonNode resp = null;
        try {
            resp = Unirest.post(api).header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(body).asJson().getBody();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return resp;
    }

    /**
     * Lấy 1 object của 1 JsonNode (response api dạng JsonNode)
     * @param json
     * @param key
     * @return
     */
    public static JSONObject getJsonKey_Obj(JsonNode json, String key){
        JSONObject obj = null;
        try {
            // 1. Tạo ra một JSONParser
            JSONParser jParser = new JSONParser();
            // 2. Parser chuỗi JSON về một JSONObject
            JSONObject bodyObj = (JSONObject) jParser.parse(String.valueOf(json));
            // 3. Lấy các giá trị trong jsonObject thông qua các Key
            // key là kiểu dữ liệu nào thì lấy kiểu dữ liệu đó
            obj = (JSONObject) bodyObj.get(key);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * Lấy 1 String value theo key của 1 Object
     * Dành cho trường hợp key nằm trong 1 object của response api, không thể lấy giá trị trực tiếp từ response
     * @param json
     * @param key
     * @return
     */
    public static String getJsonKey_String(JSONObject json, String key){
        String s = null;
        try {
            // 1. Tạo ra một JSONParser
            JSONParser jParser = new JSONParser();
            // 2. Parser chuỗi JSON về một JSONObject
            JSONObject bodyObj = (JSONObject) jParser.parse(String.valueOf(json));
            // 3. Lấy các giá trị trong jsonObject thông qua các Key
            // key là kiểu dữ liệu nào thì lấy kiểu dữ liệu đó
            s = (String) bodyObj.get(key);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return s;
    }

    /**
     * Lấy 1 Integer/Long value theo key của 1 Object
     * Dành cho trường hợp key nằm trong 1 object của response api, không thể lấy giá trị trực tiếp từ response
     * @param json
     * @param key
     * @return
     */
    public static long getJsonKey_long(JSONObject json, String key){
        long a = 0;
        try {
            // 1. Tạo ra một JSONParser
            JSONParser jParser = new JSONParser();
            // 2. Parser chuỗi JSON về một JSONObject
            JSONObject bodyObj = (JSONObject) jParser.parse(String.valueOf(json));
            // 3. Lấy các giá trị trong jsonObject thông qua các Key
            // key là kiểu dữ liệu nào thì lấy kiểu dữ liệu đó
            a = (Long) bodyObj.get(key);
        } catch (ParseException e){
            e.printStackTrace();
        }

        return a;
    }

    /**
     * Lấy 1 Boolean value theo key của 1 Object
     * Dành cho trường hợp key nằm trong 1 object của response api, không thể lấy giá trị trực tiếp từ response
     * @param json
     * @param key
     * @return
     */
    public static boolean getJsonKey_boolean(JSONObject json, String key){
        boolean b = true;
        try {
            // 1. Tạo ra một JSONParser
            JSONParser jParser = new JSONParser();
            // 2. Parser chuỗi JSON về một JSONObject
            JSONObject bodyObj = (JSONObject) jParser.parse(String.valueOf(json));
            // 3. Lấy các giá trị trong jsonObject thông qua các Key
            // key là kiểu dữ liệu nào thì lấy kiểu dữ liệu đó
            b = Boolean.parseBoolean((String) bodyObj.get(key));
        } catch (ParseException e){
            e.printStackTrace();
        }

        return b;
    }


    /**
     * GET request: Thực hiện gửi request để lấy kết quả trả về là response api
     * @param api
     * @param token
     * @return
     */
    public static JsonNode getApi(String api, String token){
        JsonNode resp = null;
        try {
            resp = Unirest.get(api).header("Authorization", "Bearer " + token)
                    .asJson().getBody();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return resp;
    }

    /**
     * File thông tin cố định.
     * Post request: Thực hiện gửi request api theo path file payload .json
     *
     * @param api
     * @param fileName
     */
    public void postApi(String api, String token, String fileName) {
        JtwigTemplate temp = JtwigTemplate.classpathTemplate(fileName);
        try {
            Unirest.post(api).header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .body(temp)
                    .asJson();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * File thông tin cố định.
     * PUT request: Thực hiện gửi request api sửa theo path file payload .json.
     * Thêm thông tin bản ghi cần sửa tại param api, thường sẽ là id bản ghi.
     * @param api
     * @param token
     * @param fileName
     * @param paramName
     * @param paramValue
     */
    public void putApi(String api, String token, String fileName, String paramName, String paramValue){
        JtwigTemplate temp = JtwigTemplate.classpathTemplate(fileName);
        try {
            Unirest.put(api).routeParam(paramName, paramValue)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .body(temp)
                    .asJson();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * File thông tin linh động (đặt biến data).
     * Post request: Thực hiện gửi request api với data payload "model", theo path file payload .json
     * @param api
     * @param token
     * @param fileName
     * @param model
     */
    public void postApiModel(String api, String token, String fileName, JtwigModel model){
        JtwigTemplate temp = JtwigTemplate.classpathTemplate(fileName);
        try {
            Unirest.post(api).header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .body(temp.render(model))
                    .asJson();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * File thông tin linh động (đặt biến data).
     * PUT request: Thực hiện gửi request api sửa với data payload "model", theo path file payload .json.
     * Thêm thông tin bản ghi cần sửa tại param api, thường sẽ là id bản ghi.
     * @param api
     * @param token
     * @param fileName
     * @param paramName
     * @param paramValue
     * @param model
     */
    public void putApiModel(String api, String token, String fileName, String paramName, String paramValue, JtwigModel model){
        JtwigTemplate temp = JtwigTemplate.classpathTemplate(fileName);
        try {
            Unirest.put(api).routeParam(paramName, paramValue)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .body(temp.render(model))
                    .asJson();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Delete request: Thực hiện xoá data.
     * Thêm thông tin bản ghi cần xoá tại param api, thường sẽ là id bản ghi
     * @param api
     * @param token
     * @param paramName
     * @param paramValue
     */
    public void deleteApi(String api, String token, String paramName, String paramValue){
        try {
            Unirest.delete(api).header("Authorization", "Bearer " + token)
                    .routeParam(paramName, paramValue)
                    .asJson();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
