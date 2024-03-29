package cz.polankam.jaclp.demo.test_integration;

import cz.polankam.jaclp.demo.model.dto.UserDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    TestRestTemplate rest;

    /**
     * Test /users endpoint with with admin account which has should have access
     * to everything.
     */
    @Test
    void test01_AllWithAdmin() {
        ResponseEntity<UserDTO[]> response = rest
                .withBasicAuth("admin", "password")
                .getForEntity("/users", UserDTO[].class);

        UserDTO[] body = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(4, body.length);
    }

    /**
     * Test /users endpoint with with user account, this endpoint should not be
     * accessible to normal user account.
     */
    @Test
    void test02_AllWithUser() {
        ResponseEntity<Map> response = rest
                .withBasicAuth("user-1", "password")
                .getForEntity("/users", Map.class);

        Map body = response.getBody();
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals(403, body.get("status"));
        assertEquals("Forbidden", body.get("error"));
    }

    /**
     * Test /users/{id} endpoint with admin account which should have
     * access to everything.
     */
    @Test
    void test03_GetWithAdmin() {
        ResponseEntity<UserDTO> response = rest
                .withBasicAuth("admin", "password")
                .getForEntity("/users/2", UserDTO.class);

        UserDTO body = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2L, body.getId());
        assertEquals("manager", body.getUsername());
        assertEquals("user", body.getRole());
    }

    /**
     * Test /users/{id} endpoint with with user account, retrieval is on the
     * user which should not be accessible to user account.
     */
    @Test()
    void test04_GetWithUser() {
        ResponseEntity<Map> response = rest
                .withBasicAuth("user-1", "password")
                .getForEntity("/users/2", Map.class);

        Map body = response.getBody();
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals(403, body.get("status"));
        assertEquals("Forbidden", body.get("error"));
    }

    /**
     * Test /users/{id} endpoint with with user account, retrieval is on the
     * current user and should be normally accessible.
     */
    @Test
    void test05_GetWithCurrentUser() {
        ResponseEntity<UserDTO> response = rest
                .withBasicAuth("manager", "password")
                .getForEntity("/users/2", UserDTO.class);

        UserDTO body = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2L, body.getId());
        assertEquals("manager", body.getUsername());
        assertEquals("user", body.getRole());
    }
}
