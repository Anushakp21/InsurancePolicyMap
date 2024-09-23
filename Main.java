package insurancemanagentusingmap;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws ParseException {
        PolicyManager manager = new PolicyManager();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        manager.addPolicy(new Policy("P001", "John Doe", sdf.parse("2024-10-15"), "Health", 1000.0));
        manager.addPolicy(new Policy("P002", "Jane Smith", sdf.parse("2024-09-30"), "Auto", 1500.0));
        manager.addPolicy(new Policy("P003", "Alice Johnson", sdf.parse("2024-10-05"), "Home", 2000.0));
        manager.addPolicy(new Policy("P004", "Bob Brown", sdf.parse("2024-11-01"), "Auto", 1200.0));

        System.out.println("Policy with number P001:");
        System.out.println(manager.getPolicyByNumber("P001"));

        System.out.println("\nPolicies Expiring Soon:");
        manager.getPoliciesExpiringSoon().forEach(System.out::println);

        System.out.println("\nPolicies for Jane Smith:");
        manager.getPoliciesByPolicyholder("Jane Smith").forEach(System.out::println);

        manager.removeExpiredPolicies();
        System.out.println("\nAll Policies after removing expired ones:");
        manager.displayAllPolicies();
    }
}

