package idx.kafka.producer;

public class DataRecord {

    String url;
    Integer total_documents;
    Integer document_current_number;

    public DataRecord() {
    }

    public DataRecord(String url,Integer total_documents,Integer document_current_number) {
        this.url = url;
        this.document_current_number = document_current_number;
        this.total_documents = total_documents;
    }

    public String getUrl() {
        return url;

    }

    public Integer getTotalDocuments(){
        return this.total_documents;
    }

    public  Integer getDocument_current_number(){
        return this.document_current_number;
    }

    public  void setUrl(String url){
        this.url = url;
    }

    public  void setTotal_documents(Integer total_documents){
        this.total_documents = total_documents;
    }

    public  void setDocument_current_number(Integer current_number){
        this.document_current_number = current_number;
    }

    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }

}