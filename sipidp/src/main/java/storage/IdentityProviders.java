package storage;

import Common.Exceptions.FrameworkCheckedException;
import Models.IdentityProvider;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class IdentityProviders {

    private static final Map<String, IdentityProvider> PROVIDER_MAP = new HashMap<>();


    public static void addProvider(final IdentityProvider provider) {
        PROVIDER_MAP.put(provider.getProviderUrl(), provider);
    }

    public static IdentityProvider getIdentityProvider(final String providerUrl) throws FrameworkCheckedException {
        if (PROVIDER_MAP.containsKey(providerUrl)) {
            return PROVIDER_MAP.get(providerUrl);
        }

        throw new FrameworkCheckedException("Client not found");
    }

    public static Collection<IdentityProvider> getProviderList() {
        return PROVIDER_MAP.values();
    }

}
