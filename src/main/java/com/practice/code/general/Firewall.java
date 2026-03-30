package com.practice.code.general;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
Implement a firewall feature considering following points:
    Some rules are defined ("ALLOW", "192.168.1.0/24"), ("ALLOW", "1.2.3.4"), ("DENY", "10.5.12.10/28")
    Only allow requests from IP addresses that follow the rule. All other requests should be denied.
 **/

public class Firewall {

    // Enum for rule Actions
    enum Action {
        ALLOW,
        DENY
    }

    class Rule {
        Action action;
        String cidr;

        public Rule(Action action, String cidr) {
            this.action = action;
            this.cidr = cidr;
        }
    }

    List<Rule> rules;

    public Firewall() {
        rules = new ArrayList<>();
    }

    public void addRule(String action, String cidr) {
        rules.add(new Rule(Action.valueOf(action), cidr));
    }

    public boolean isAllowed(String ipAddress) {
        for( Rule rule: rules) {
            if(isIpInCidr(ipAddress, rule.cidr)){
                return rule.action == Action.ALLOW;
            }
        }
        return false;
    }

    private boolean isIpInCidr(String ipAddress, String cidr) {
        if(!cidr.contains("/")) {
            return cidr.equals(ipAddress);
        }

        String[] parts = cidr.split("/");
        String baseIp = parts[0];
        int prefixLength = Integer.valueOf(parts[1]);

        // convert ip to byte array
        InetAddress ip = null;
        InetAddress network = null;
        try {
            ip = InetAddress.getByName(ipAddress);
            network = InetAddress.getByName(baseIp);
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException("Invalid IP address or CIDR: " + ipAddress);
        }

        byte[] ipBytes = ip.getAddress();
        byte[] networkBytes = network.getAddress();

        int maskBits = prefixLength;
        for(int i = 0; i < ipBytes.length && maskBits > 0; i++) {
            int mask = (maskBits >= 8) ? 0xFF : (0xFF << (8 - maskBits));
            if((ipBytes[i] & mask) != (networkBytes[i] & mask)) {
                return false;
            }
            maskBits -= 8;
        }

        return true;
    }


    public static void main(String[] args) {
        Firewall firewall = new Firewall();

        // Define rules
        firewall.addRule("ALLOW", "192.168.1.0/24");
        firewall.addRule("ALLOW", "1.2.3.4");
        firewall.addRule("DENY", "10.5.12.10/28");

        // Test IPs
        System.out.println(firewall.isAllowed("192.168.1.5"));  // Should be allowed
        System.out.println(firewall.isAllowed("1.2.3.4"));      // Should be allowed
        System.out.println(firewall.isAllowed("10.5.12.11"));   // Should be denied
        System.out.println(firewall.isAllowed("10.5.12.20"));   // Should be denied by default rule
        System.out.println(firewall.isAllowed("172.16.0.1"));   // Should be denied by default rule
    }
}
