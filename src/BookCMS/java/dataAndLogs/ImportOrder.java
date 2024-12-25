package dataAndLogs;


import lombok.Data;

@Data
public class ImportOrder {
    public String code;
    public String codeContract;
    public String provider;
    public String orderPerson;
    public String orderDate;
    public String importer;
    public String importDate;
    public String status;
}
