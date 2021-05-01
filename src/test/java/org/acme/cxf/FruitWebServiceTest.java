package org.acme.cxf;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class FruitWebServiceTest {

    @Test
    public void testAddFruit()
            throws XPathExpressionException, IOException, SAXException, ParserConfigurationException {
        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cxf=\"http://cxf.acme.org/\">\n"
                +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "   <cxf:add>\n" +
                "    <Fruit>\n" +
                "      <name>Pineapple</name>\n" +
                "      <description>Tropical fruit</description>\n" +
                "    </Fruit>	  \n" +
                "   </cxf:add>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        String val = "";

        Response response = RestAssured.given().header("Content-Type", "text/xml").and().body(xml).when().post("/cxf/fruit");
        response.then().statusCode(200);
    }

    @Test
    public void testListFruit()
            throws XPathExpressionException, IOException, SAXException, ParserConfigurationException {
        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cxf=\"http://cxf.acme.org/\">\n"
                +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "    <cxf:list/>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        String val = "";

        Response response = RestAssured.given().header("Content-Type", "text/xml").and().body(xml).when().post("/cxf/fruit");
        response.then().statusCode(200);
    }

    @Test
    void testSoapBinding() {
        given()
                .when().get("/cxf/fruit?wsdl")
                .then()
                .statusCode(200)
                .body(containsString("http://schemas.xmlsoap.org/wsdl"));
    }

}