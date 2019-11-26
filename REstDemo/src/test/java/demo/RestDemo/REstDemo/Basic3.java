package demo.RestDemo.REstDemo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Basic3 {
	
	
	@Test
	public void AddandDeletePlace()
	{
		RestAssured.baseURI="http://216.10.245.166";
		String v="{"+

  "\"location\": {"+

    "\"lat\": -33.8669710,"+

    "\"lng\": 151.1958750"+

  "},"+

  "\"accuracy\": 50,"+

  "\"name\": \"Google Shoes!\","+

  "\"phone_number\": \"(02) 9374 4000\","+

  "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+

  "\"types\": [\"shoe_store\"],"+

  "\"website\": \"http://www.google.com.au/\","+

  "\"language\": \"en-AU\""+

"}";
		

		Response res=given().
		
		queryParam("key","qaclick123").

		body(v).

		when().

		post("/maps/api/place/add/json").

		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().

		body("status",equalTo("OK")).
		extract().response();
		
		
		String responseString=res.asString();
		System.out.println(responseString);
		
		//grab the place id from the response
		
		
		//conver to json
		
		JsonPath js=new JsonPath(responseString);
		
		String placeid=js.get("place_id");
		System.out.println(placeid);
		
		
		given().
		queryParam("key","qaclick123")
		.body("{"+
				"\"place_id\": \""+placeid+"\""+
	"}").when().
		post("/maps/api/place/delete/json").then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().

		body("status",equalTo("OK"));
		
		
		
	}

}
