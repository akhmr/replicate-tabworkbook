import java.util.HashMap;

public class Datasource {

	
	String aliases;
	String column;
	String calculation;
	String connection;
	
	HashMap<String,String> connectionInfo;
	HashMap<String,String> colorInfoMap;
	
	HashMap<String,HashMap<String,String>> colorFieldMap;
	
	String authentication;
    String server;
    String dbName;
    String username;
     
    String bucket;
    String mapTo;
    
	String colorCode;
    String colorField;
    String dbtable;
    
    String fieldName;
    
	public Datasource(){
		
		connectionInfo = new HashMap<String,String>();
		colorFieldMap = new HashMap<String,HashMap<String,String>>();
	}

	public String getAliases() {
		return aliases;
	}

	public void setAliases(String aliases) {
		this.aliases = aliases;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getCalculation() {
		return calculation;
	}

	public void setCalculation(String calculation) {
		this.calculation = calculation;
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public HashMap<String, String> getConnectionInfo() {
		return connectionInfo;
	}

	public void setConnectionInfo(HashMap<String, String> connectionInfo) {
		this.connectionInfo = connectionInfo;
	}

	public String getAuthentication() {
		return authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public HashMap<String, String> getColorInfoMap() {
		return colorInfoMap;
	}

	public void setColorInfoMap(HashMap<String, String> colorInfoMap) {
		this.colorInfoMap = colorInfoMap;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getColorField() {
		return colorField;
	}

	public void setColorField(String colorField) {
		this.colorField = colorField;
	}
	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}
	
	public String getTableName() {
		return dbtable;
	}

	public void setTableName(String dbtable) {
		this.dbtable = dbtable;
	}
	
	public String getMapTo() {
		return mapTo;
	}

	public void setMapTo(String mapTo) {
		this.mapTo = mapTo;
	}

	public HashMap<String, HashMap<String, String>> getColorFieldMap() {
		return colorFieldMap;
	}

	public void setColorFieldMap(String fieldName, HashMap<String, String> colorFieldMap) {
		this.colorFieldMap.put(fieldName, colorFieldMap);
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	
}
