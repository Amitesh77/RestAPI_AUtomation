package demo.RestDemo.REstDemo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import  io.restassured.matcher.RestAssuredMatchers;

import  static org.hamcrest.Matchers.*;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stubA
		
		RestAssured.baseURI=("https://maps.googleapis.com");
		
		given().
		param("location","-33.8670522,151.1957362").
		param("radius","1500").
		param("key","-33.8670522,151.1957362").
		
		when().
		get("/maps/api/place/nearbysearch/json").
		
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("results[0].geometry.location.lat",equalTo("-33.8561088"));
		

	}

}
