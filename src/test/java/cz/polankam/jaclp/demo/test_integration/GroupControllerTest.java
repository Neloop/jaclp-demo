package cz.polankam.jaclp.demo.test_integration;

import cz.polankam.jaclp.demo.model.dto.GroupDTO;
import cz.polankam.jaclp.demo.model.dto.GroupUpdateDTO;
import cz.polankam.jaclp.demo.model.dto.UserDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
public class GroupControllerTest {

    @Autowired
    TestRestTemplate rest;

    /**
     * Test /groups endpoint with with admin account which has should have
     * access to everything.
     */
    @Test
    public void test01_AllWithAdmin() {
        ResponseEntity<GroupDTO[]> response = rest
                .withBasicAuth("admin", "password")
                .getForEntity("/groups", GroupDTO[].class);

        GroupDTO[] body = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, body.length);
    }

    /**
     * Test /groups endpoint with with user account, this endpoint should not be
     * accessible to normal user account.
     */
    @Test
    public void test02_AllWithUser() {
        ResponseEntity<Map> response = rest
                .withBasicAuth("manager", "password")
                .getForEntity("/groups", Map.class);

        Map body = response.getBody();
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals(403, body.get("status"));
        assertEquals("Forbidden", body.get("message"));
    }

    /**
     * Test /groups/mine endpoint with with user account.
     */
    @Test
    public void test03_MineWithUser() {
        ResponseEntity<GroupDTO[]> response = rest
                .withBasicAuth("manager", "password")
                .getForEntity("/groups/mine", GroupDTO[].class);

        GroupDTO[] body = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, body.length);
    }

    /**
     * Test /groups/{id} endpoint with with admin account which should have
     * access to everything.
     */
    @Test
    public void test04_GetWithAdmin() {
        ResponseEntity<GroupDTO> response = rest
                .withBasicAuth("admin", "password")
                .getForEntity("/groups/5", GroupDTO.class);

        GroupDTO body = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5L, body.getId());
        assertEquals("demo-group", body.getName());
        assertEquals("description", body.getDescription());
    }

    /**
     * Test /groups/{id} endpoint with with user account, user has permission
     * to access this group.
     */
    @Test()
    public void test05_GetWithUser() {
        ResponseEntity<GroupDTO> response = rest
                .withBasicAuth("user-1", "password")
                .getForEntity("/groups/5", GroupDTO.class);

        GroupDTO body = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5L, body.getId());
        assertEquals("demo-group", body.getName());
        assertEquals("description", body.getDescription());
    }

    /**
     * Test /groups/{id} endpoint with with user-2 account, this user should not
     * have access to the group.
     */
    @Test()
    public void test06_GetWithUser2() {
        ResponseEntity<Map> response = rest
                .withBasicAuth("user-2", "password")
                .getForEntity("/groups/5", Map.class);

        Map body = response.getBody();
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals(403, body.get("status"));
        assertEquals("Forbidden", body.get("message"));
    }

    /**
     * Test PUT /groups/{id} endpoint with with manager account, this user
     * should have access to the update functionality.
     */
    @Test()
    public void test07_UpdateWithManager() {
        GroupUpdateDTO updateDTO = new GroupUpdateDTO();
        updateDTO.setName("demo-group-updated");
        updateDTO.setDescription("updated description");

        ResponseEntity<GroupDTO> response = rest
                .withBasicAuth("manager", "password")
                .exchange("/groups/5", HttpMethod.PUT, new HttpEntity<>(updateDTO), GroupDTO.class);

        GroupDTO body = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5L, body.getId());
        assertEquals("demo-group-updated", body.getName());
        assertEquals("updated description", body.getDescription());
    }

    /**
     * Test PUT /groups/{id} endpoint with with user account, this user
     * should not be able to update group.
     */
    @Test()
    public void test08_UpdateWithUser() {
        GroupUpdateDTO updateDTO = new GroupUpdateDTO();
        updateDTO.setName("demo-group-updated");
        updateDTO.setDescription("updated description");

        ResponseEntity<Map> response = rest
                .withBasicAuth("user-1", "password")
                .exchange("/groups/5", HttpMethod.PUT, new HttpEntity<>(updateDTO), Map.class);

        Map body = response.getBody();
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals(403, body.get("status"));
        assertEquals("Forbidden", body.get("message"));
    }
}
