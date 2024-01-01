package tech.manleysoftware;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import tech.manleysoftware.user.User;

@Singleton
public class Startup {

    @ConfigProperty(name="ADMIN_PASS")
    String password;

    @ConfigProperty(name = "ADMIN_USERNAME")
    String userName;

    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        // reset and load all test users
        User.deleteAll();
        User.add(userName, password, "admin");
    }
}
