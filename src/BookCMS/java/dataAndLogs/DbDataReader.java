package dataAndLogs;

import configProject.MysqlConnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DbDataReader {
    MysqlConnect mysqlConnect = new MysqlConnect();

    public List<ImportOrder> orderInformation() throws InterruptedException {
        List<ImportOrder> lstOrder = new ArrayList<ImportOrder>();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String sql = "SELECT a.code, a.CONTRACT_CODE , b.NAME ,a.ORDERER  , a.ORDER_DATE , c.FULL_NAME , a.CREATED_TIME , " +
                "(CASE a.STATUS " +
                "WHEN 1 THEN 'Chờ duyệt' " +
                "ELSE 'Khác' " +
                "END) AS STATUS " +
                "FROM PMGW_TRANS.CARD_BATCH_ORDER a " +
                "JOIN PMGW_CMS.PROVIDER b ON a.PROVIDER_ID = b.ID " +
                "JOIN PMGW_CMS.USER_CMS c ON a.CREATED_BY  = c.ID " +
                "ORDER BY CREATED_TIME DESC FETCH FIRST 1 ROWS ONLY ";
//        System.out.println(sql);
//        Thread.sleep(3000);
        try {
            Statement stmt = mysqlConnect.connect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ImportOrder importOder = new ImportOrder();//
                importOder.code = rs.getString("code") == null ? "" : rs.getString("code"); // STT 0
//                System.out.println(rs.getString("a.code"));
                importOder.codeContract = rs.getString("CONTRACT_CODE") == null ? "" : rs.getString("CONTRACT_CODE");
                importOder.provider = rs.getString("NAME") == null ? "" : rs.getString("NAME");
                importOder.orderPerson = rs.getString("ORDERER") == null ? "" : rs.getString("ORDERER");
                importOder.orderDate = rs.getString("ORDER_DATE") == null ? "" : formatDate.format(rs.getTimestamp("ORDER_DATE"));
                importOder.importer = rs.getString("FULL_NAME") == null ? "" : rs.getString("FULL_NAME");
//                importOder.importDate = rs.getTimestamp("CREATED_TIME") == null ? "" : formatDate.format(rs.getTimestamp("CREATED_TIME"));
                importOder.status = rs.getString("STATUS") == null ? "" : rs.getString("STATUS");

                lstOrder.add(importOder);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        return lstOrder;
    }
}
