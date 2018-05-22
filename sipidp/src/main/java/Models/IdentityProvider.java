package Models;

public class IdentityProvider {

    private final String providerName;
    private final String providerUrl;
    private final String providerDiscovery;


    public IdentityProvider(String providerName, String providerUrl, String providerDiscovery) {
        this.providerName = providerName;
        this.providerUrl = providerUrl;
        this.providerDiscovery = providerDiscovery;
    }


    public String getProviderName() {
        return providerName;
    }

    public String getProviderUrl() {
        return providerUrl;
    }

    public String getProviderDiscovery() {
        return providerDiscovery;
    }
}
