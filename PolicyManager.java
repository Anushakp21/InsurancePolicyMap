package insurancemanagentusingmap;

import java.util.*;

public class PolicyManager {
    private Map<String, Policy> policyMap;          // HashMap
    private Map<String, Policy> linkedPolicyMap;    // LinkedHashMap
    private Map<Date, Policy> treePolicyMap;        // TreeMap

    public PolicyManager() {
        policyMap = new HashMap<>();
        linkedPolicyMap = new LinkedHashMap<>();
        treePolicyMap = new TreeMap<>();
    }

    public void addPolicy(Policy policy) {
        policyMap.put(policy.getPolicyNumber(), policy);
        linkedPolicyMap.put(policy.getPolicyNumber(), policy);
        treePolicyMap.put(policy.getExpiryDate(), policy);
    }

    public Policy getPolicyByNumber(String policyNumber) {
        return policyMap.get(policyNumber);
    }

    public List<Policy> getPoliciesExpiringSoon() {
        List<Policy> expiringSoon = new ArrayList<>();
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 30);
        Date thirtyDaysFromNow = cal.getTime();

        for (Policy policy : treePolicyMap.values()) {
            if (!policy.getExpiryDate().before(now) && policy.getExpiryDate().before(thirtyDaysFromNow)) {
                expiringSoon.add(policy);
            }
        }
        return expiringSoon;
    }

    public List<Policy> getPoliciesByPolicyholder(String policyholderName) {
        List<Policy> result = new ArrayList<>();
        for (Policy policy : policyMap.values()) {
            if (policy.getPolicyholderName().equalsIgnoreCase(policyholderName)) {
                result.add(policy);
            }
        }
        return result;
    }

    public void removeExpiredPolicies() {
        Date now = new Date();
        policyMap.values().removeIf(policy -> policy.getExpiryDate().before(now));
        linkedPolicyMap.values().removeIf(policy -> policy.getExpiryDate().before(now));
        treePolicyMap.values().removeIf(policy -> policy.getExpiryDate().before(now));
    }

    public void displayAllPolicies() {
        System.out.println("All Policies:");
        for (Policy policy : policyMap.values()) {
            System.out.println(policy);
        }
    }
}

