package ucll.project.domain.user;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void CreateUserTest(){
        User user = new User(
                "userName",
                "firstName",
                "lastName",
                "email@example.com",
                Gender.FEMALE,
                Role.ADMIN
        );
        assertEquals(user.getUserName(), "userName");
    }
}
