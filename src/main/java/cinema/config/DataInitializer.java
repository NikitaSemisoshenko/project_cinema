package cinema.config;

import cinema.model.Role;
import cinema.model.User;
import cinema.service.RoleService;
import cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.add(userRole);
        User bob = new User();
        bob.setEmail("bob@i.ua");
        bob.setPassword("12345678");
        bob.setRoles(Set.of(roleService.getByName("ADMIN")));
        userService.add(bob);
        User alice = new User();
        alice.setEmail("alice@i.ua");
        alice.setPassword("12345678");
        alice.setRoles(Set.of(roleService.getByName("USER")));
        userService.add(alice);
    }
}
