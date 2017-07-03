import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xml.sax.Attributes;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlParser
{

	public static void main(String[] args)
	{
		try
		{	
			String xmlFilePath = "D:\\Users\\rohit.ra\\Rohit\\Documents\\Test1.xml";

			// We may need different approach for this, below code is for replacing &quot; to get values.
			Path path = Paths.get(xmlFilePath);
			Charset charset = StandardCharsets.UTF_8;
			String content = new String(Files.readAllBytes(path), charset);			 
			content = content.replaceAll( "&quot;", "");

			Pattern pattern = Pattern.compile("&quot;");
			Matcher matcher = pattern.matcher(content);
			while (matcher.find()) {
				content = content.replaceAll(matcher.group(1), matcher.group(1).replace("&quot;", ""));
			}

			// code ends here.

			File inputFile = new File(xmlFilePath);

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			UserHandler userhandler = new UserHandler();
			saxParser.parse(inputFile, userhandler);     

			List<Worksheet> worksheetL= userhandler.worksheetL;
			Datasource datsource = userhandler.datsource;

//			System.out.println("Size "+ datsource.getColorInfoMap().size());

//			Iterator it = datsource.getColorInfoMap().entrySet().iterator();
//			while (it.hasNext()) {
//				Map.Entry pair = (Map.Entry)it.next();
////				System.out.println(pair.getKey() + " = " + pair.getValue());
//				it.remove(); // avoids a ConcurrentModificationException
//			}
			
			Iterator it1 = datsource.getColorFieldMap().entrySet().iterator();
			while (it1.hasNext()) {
				Map.Entry pair = (Map.Entry)it1.next();
				System.out.println( " Key = " +pair.getKey() + "= size" + pair.getValue().toString());
				 Map objMap = (Map)pair.getValue();
				 Iterator it2 =  objMap.entrySet().iterator();
				 /*while (it2.hasNext()) {
						Map.Entry pair2 = (Map.Entry)it2.next();
						System.out.println(pair2.getKey() + " = Color" + pair2.getValue());
						it2.remove(); // avoids a ConcurrentModificationException
					}*/
				 
				it1.remove(); // avoids a ConcurrentModificationException
			}
			
		/*	for(int i=0;i<worksheetL.size();i++)
			{
				System.out.println("Worksheet Name "+worksheetL.get(i).getWorksheetName());

				List<Panes> paneL = worksheetL.get(i).getPanes();

				for(int j=0;j<paneL.size();j++)
				{
					Panes pane = paneL.get(j);

					System.out.println("Worksheet ID "+pane.getId());
					System.out.println("Worksheet Chart "+pane.getChartTyp());
					System.out.println("Worksheet Column "+pane.getColumn());
					System.out.println("Worksheet "+pane.getY_axis_name());
				}

			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}   
}

class UserHandler extends DefaultHandler {

	Datasource datsource;
	Panes pane;
	Worksheet worksheet;

	boolean bMarks = false;
	boolean bConnection = false;
	boolean bColormap = false;
	//	   HashMap<String,String> ConnectionInfo = new HashMap<String,String>();
	String name= null;
	boolean bParmater = false;
	List<Worksheet> worksheetL  = new ArrayList<Worksheet>();


	@Override
	public void startElement(String uri, 
			String localName, String qName, Attributes attributes)
					throws SAXException {
		if (qName.equalsIgnoreCase("datasource")) {
			String isParameter = attributes.getValue("hasconnection")!=null?attributes.getValue("hasconnection"):"NA";
			if(isParameter.equalsIgnoreCase("false")){
				bParmater = true;
				System.out.println("Parameter is true");
			}
		}
		else if (qName.equalsIgnoreCase("connection")) {
			datsource = new Datasource();
			String authentication = attributes.getValue("authentication")!=null?attributes.getValue("authentication"):"NA";
			String server = attributes.getValue("server")!=null?attributes.getValue("server"):"NA";
			String dbName = attributes.getValue("dbname")!=null?attributes.getValue("dbname"):"NA";
			String username = attributes.getValue("username")!=null?attributes.getValue("username"):"NA";

			datsource.setAuthentication(authentication);
			datsource.setServer(server);
			datsource.setDbName(dbName);
			datsource.setUsername(username);
			//			bConnection = true;
		} else if (qName.equalsIgnoreCase("datasource") && attributes.getValue("name").equalsIgnoreCase("Parameters") ) {
			name = attributes.getValue("name")!=null?attributes.getValue("name"):"NA";
			System.out.println("Parameter name " + name);
		}
		else if (qName.equalsIgnoreCase("worksheet")) {
			worksheet = new Worksheet();
			worksheet.setWorksheetName(attributes.getValue("name")!=null?attributes.getValue("name"):"NA");
		}
		else if (qName.equalsIgnoreCase("relation")) {
			datsource.setTableName(attributes.getValue("table")!=null?attributes.getValue("table"):"NA");
		}
		else if (qName.equalsIgnoreCase("column")) {
			if(bParmater )
			{
				List<String> parmList = new ArrayList<String>();
				parmList.add(attributes.getValue("caption")!=null?attributes.getValue("caption"):"NA");
				parmList.add(attributes.getValue("datatype")!=null?attributes.getValue("datatype"):"NA");
				parmList.add(attributes.getValue("name")!=null?attributes.getValue("name"):"NA");
				parmList.add(attributes.getValue("role")!=null?attributes.getValue("role"):"NA");
				parmList.add(attributes.getValue("value")!=null?attributes.getValue("value"):"NA");
				System.out.println("Parameter " + parmList);
			}

		} else if (qName.equalsIgnoreCase("pane")) {
			pane = new Panes();
			pane.setId(attributes.getValue("id")!=null?attributes.getValue("id"):"NA");
			pane.setY_axis_name(attributes.getValue("y-axis-name")!=null?attributes.getValue("y-axis-name"):"NA");
			pane.setX_axis_name(attributes.getValue("x-axis-name")!=null?attributes.getValue("x-axis-name"):"NA");
		}
		else if (qName.equalsIgnoreCase("breakdown")) {
			pane.setBreakdown(attributes.getValue("value")!=null?attributes.getValue("value"):"NA");
		}
		else if (qName.equalsIgnoreCase("mark")) {
			pane.setChartTyp(attributes.getValue("class")!=null?attributes.getValue("class"):"NA");
		}
		else if (qName.equalsIgnoreCase("color")) {
			pane.setColumn(attributes.getValue("column")!=null?attributes.getValue("column"):"NA");
		}
		else if (qName.equalsIgnoreCase("map")) {
			datsource.setMapTo(attributes.getValue("to")!=null?attributes.getValue("to"):"NA");
		}

		else if (qName.equalsIgnoreCase("bucket")) {
			bMarks = true;
		}
		else if (qName.equalsIgnoreCase("encoding") && attributes.getValue("attr").equalsIgnoreCase("color") ) {
			String field = attributes.getValue("field")!=null?attributes.getValue("field"):"NA";
			datsource.setFieldName(field);
			bColormap = true;
			datsource.colorInfoMap = new HashMap<String,String>();
			
			System.out.println("field name " + field);
		}

	}

	@Override
	public void endElement(String uri, 
			String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("connection")) {
			System.out.println("End Element :" + datsource.getAuthentication() + "--" + datsource.getDbName());
		}
		if (qName.equalsIgnoreCase("datasource")) {
			bParmater = false;
		}
		else if (qName.equalsIgnoreCase("pane")) {
			worksheet.setPanes(pane);
		}
		else if (qName.equalsIgnoreCase("breakdown")) {
		}
		else if (qName.equalsIgnoreCase("mark")) {
		}
		else if (qName.equalsIgnoreCase("color")) {
		}
		else if (qName.equalsIgnoreCase("worksheet")) {
			worksheetL.add(worksheet);
		}
		else if (qName.equalsIgnoreCase("encoding") && (bColormap)) {
//			datsource.setColorInfoMap().put();
			datsource.setColorFieldMap(datsource.getFieldName(), datsource.getColorInfoMap());
			bColormap = false;
		}
	}

	@Override
	public void characters(char ch[], 
			int start, int length) throws SAXException {
		if (bMarks) {
//			System.out.println("Key: " + new String(ch, start, length) + "Value::" + datsource.getMapTo());
			//			String content = String.copyValueOf(ch, start, length).trim();
			//		    content = content.replace("&quot;", " ");
			//			System.out.println("Content: " + content);
			datsource.colorInfoMap.put(new String(ch, start, length),datsource.getMapTo());
			bMarks = false;
		}

		new String(ch, start, length);
	}
}
