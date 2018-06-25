package Security;

import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

public class Secret {
    public static final Key SECRET = MacProvider.generateKey();
}
