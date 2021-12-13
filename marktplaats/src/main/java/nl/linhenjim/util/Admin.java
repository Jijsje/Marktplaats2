package nl.linhenjim.util;
import org.apache.commons.lang3.RandomStringUtils;

import javax.enterprise.context.ApplicationScoped;
import java.security.SecureRandom;

@ApplicationScoped
public class Admin {
    public String genereerWachtwoord() {
        return RandomStringUtils.random(20, 0, 0, true, true, null,
                new SecureRandom());
    }
}
