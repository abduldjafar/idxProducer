package idx.kafka.producer;

public class DataRecord {

    String url;
    Integer total_documents;
    Integer document_current_number;
    String path;
    String filename;
    String idx_group_id;
    String idx_method;

    public DataRecord() {
    }

    public DataRecord(String url,Integer total_documents,Integer document_current_number) {
        this.url = url;
        this.document_current_number = document_current_number;
        this.total_documents = total_documents;
    }

    public DataRecord(String url,Integer total_documents,Integer document_current_number,String path,String filrname) {
        this.url = url;
        this.document_current_number = document_current_number;
        this.total_documents = total_documents;
        this.path = path;
        this.filename = filrname;
    }

    public Integer getTotal_documents() {
        return this.total_documents;
    }

    public String getIdx_group_id() {
        return this.idx_group_id;
    }

    public String getIdx_method() {
        return this.idx_method;
    }

    public String getPath() {
        return this.path;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;

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


    public void setIdx_group_id(String idx_group_id) {
        this.idx_group_id = idx_group_id;
    }

    public void setIdx_method(String idx_method) {
        this.idx_method = idx_method;
    }
}

