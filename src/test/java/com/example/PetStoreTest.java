package com.example;

import org.junit.Test;
import java.util.Random;
import java.time.Instant;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import com.github.javafaker.Faker;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PetStoreTest extends BaseTest {

    public static class IdGenerator {
        private static final Random random = new Random();
        
        public static int getNextId() {
            return random.nextInt(1000);
        }
    }

    private static final Faker faker = new Faker();
    private static int mainId;
    private static int categoryId;
    private static String categoryName;
    private static String petName;
    private static String photoUrl;
    private static int tagId;
    private static String tagName;

    @Test
    public void test01_RegisterStoreNewOrderPet() {

        int id = IdGenerator.getNextId();
        int petId = IdGenerator.getNextId();
        String shipDate = Instant.now().toString();

        String storeOrderJson = String.format("{ \"id\": %d, \"petId\": %d, \"quantity\": 1, \"shipDate\": \"%s\", \"status\": \"placed\", \"complete\": true }", 
        id, petId, shipDate);

        given()
            .body(storeOrderJson)
        .when()
            .post("/store/order")
        .then()
            .statusCode(200)
            .body("id", is(id))
            .body("petId", is(petId));
            
    }  

    @Test
    public void test02_RegisterNewPet() {

        mainId = faker.number().randomDigitNotZero();
        categoryId = faker.number().randomDigitNotZero();
        categoryName = faker.commerce().department();
        petName = faker.animal().name();
        tagId = faker.number().randomDigitNotZero();
        tagName = faker.lorem().word();
        photoUrl = "https://vaccinar.com.br/wp-content/uploads/2020/08/cachorro-e-gato.png";

        String registerNewPetJson = String.format(
            "{ \"id\": %d, \"category\": { \"id\": %d, \"name\": \"%s\" }, \"name\": \"%s\", \"photoUrls\": [ \"%s\" ], \"tags\": [ { \"id\": %d, \"name\": \"%s\" } ], \"status\": \"%s\" }",
            mainId, categoryId, categoryName, petName, photoUrl, tagId, tagName, "available");

        mainId = given()
            .body(registerNewPetJson)
        .when()
            .post("/pet")
        .then()
            .statusCode(200)
            .body("id", is(mainId))
            .body("category.id", is(categoryId))
            .body("name", is(petName))
            .body("tags[0].id", is(tagId))
            .extract().path("id");

    }

    @Test
    public void test03_ChangeRegisterPet() {

        String updatedPetName = "New name " + petName;

        String registerNewPetJson = String.format(
            "{ \"id\": %d, \"category\": { \"id\": %d, \"name\": \"%s\" }, \"name\": \"%s\", \"photoUrls\": [ \"%s\" ], \"tags\": [ { \"id\": %d, \"name\": \"%s\" } ], \"status\": \"%s\" }",
            mainId, categoryId, categoryName, updatedPetName, photoUrl, tagId, tagName, "available");

        given()
            .body(registerNewPetJson)
        .when()
            .put("/pet")
        .then()
            .statusCode(200)
            .body("id", is(mainId))
            .body("name", is(updatedPetName));

    }

    @Test
    public void test04_NonExistentPet() {

        given()
            .pathParams("petId", 0)
        .when()
            .get("/pet/{petId}")
        .then()
            .statusCode(404)
            .body("message", is("Pet not found"));

    }

    @Test
    public void test05_FindByStatusPendingPet() {

        String status = "peding";

        given()
            .queryParam("status", status)
        .when()
            .get("/pet/findByStatus")
        .then()
            .statusCode(200)
            .body("status", everyItem(is(status)));

    }
}
