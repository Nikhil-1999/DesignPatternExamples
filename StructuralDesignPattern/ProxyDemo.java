/*
Proxy is a structural design pattern that lets you provide a substitute or placeholder for another object.
A proxy controls access to the original object, allowing you to perform something either before or after the request gets through to the original object.
Refs - https://www.geeksforgeeks.org/proxy-design-pattern/ , https://refactoring.guru/design-patterns/proxy
*/

import java.util.*;

interface InternetAccess {
    public void connectTo(String url);
}

class RealInternet implements InternetAccess {
    @Override
    public void connectTo(String url) {
        System.out.println("Connected to " + url);
    }
}

class ProxyInternet implements InternetAccess {
    private RealInternet realInternet;
    private static Set<String> bannedSites = Set.of("www.abc.com", "www.def.com");

    public ProxyInternet() {
        this.realInternet = new RealInternet();
    }

    @Override
    public void connectTo(String url) {
        if (bannedSites.contains(url.toLowerCase())) {
            System.out.println("Access Denied with proxy: " + url);
        } else {
            System.out.println("Connecting with proxy to " + url);
            realInternet.connectTo(url);
        }
    }
}

public class ProxyDemo {
    public static void main(String[] args) {
        InternetAccess realInternet = new RealInternet();
        realInternet.connectTo("www.google.com");
        realInternet.connectTo("www.abc.com");
        InternetAccess proxyInternet = new ProxyInternet();
        proxyInternet.connectTo("www.google.com");
        proxyInternet.connectTo("www.abc.com");
    }
}

/*
Steps to create proxy class ->
1. Create a interface and a service class with exact implementation.
2. Create the proxy class. It should have a field for storing a reference to the service. Usually, proxies create and manage the whole life cycle of their services
3. Implement the proxy methods according to their purposes. In most cases, after doing some work, the proxy should delegate the work to the service object.
4. Consider introducing a creation method that decides whether the client gets a proxy or a real service. This can be a simple static method in the proxy class or a full-blown factory method.
*/