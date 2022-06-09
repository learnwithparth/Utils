import java.util.*;

class ChefScheduler {
    public static void main(String args[]) {

        HashMap<String, ArrayList<String>> requests = new HashMap<>();
        // for (Map.Entry<String,String> entry : gfg.entrySet())
        requests.put(
            "emma", new ArrayList<>(Arrays.asList(
                new String[] {"sun"}
            ))
        );
        requests.put(
            "remy", new ArrayList<>(Arrays.asList(
                new String[] {"sun"}
            ))
        );
        requests.put(
            "zach", new ArrayList<>(Arrays.asList(new String[] {}))
        );
        schedulable(requests);
    }

    public static boolean schedulable(HashMap<String, ArrayList<String>> requests) {
        // int alloted[] = new int[requests.size()];
        Map<String, ArrayList<String>> convertedToDayWiseAvailability = new HashMap<String, ArrayList<String>>();
        Map<String, Integer> allocation = new HashMap<>();

        convertedToDayWiseAvailability.put("mon", new ArrayList<String>(requests.keySet()));
        convertedToDayWiseAvailability.put("tue", new ArrayList<String>(requests.keySet()));
        convertedToDayWiseAvailability.put("wed", new ArrayList<String>(requests.keySet()));
        convertedToDayWiseAvailability.put("thu", new ArrayList<String>(requests.keySet()));
        convertedToDayWiseAvailability.put("fri", new ArrayList<String>(requests.keySet()));
        convertedToDayWiseAvailability.put("sat", new ArrayList<String>(requests.keySet()));
        convertedToDayWiseAvailability.put("sun", new ArrayList<String>(requests.keySet()));

        // for (int i = 0; i < alloted.length; i++)
        //     alloted[i] = 0;

        for (Map.Entry<String, ArrayList<String>> entry : requests.entrySet()) {
            allocation.put(entry.getKey(), 7);
            for (String day : entry.getValue()) {
                convertedToDayWiseAvailability.get(day).remove(entry.getKey());
                allocation.put(entry.getKey(), allocation.get(entry.getKey()) - 1);
            }
        }

        for (Map.Entry<String, ArrayList<String>> entry : convertedToDayWiseAvailability.entrySet()) {
            System.out.print("Key is " + entry.getKey() + "  Value is ");
            for (String day : entry.getValue()) {
                System.out.print(day + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------");
        for (Map.Entry<String, Integer> checkAllocation : allocation.entrySet()) {
            System.out.println(checkAllocation.getKey() + " is assigned " + checkAllocation.getValue());
            if (checkAllocation.getValue() > 5) {
                for (Map.Entry<String, ArrayList<String>> entry : convertedToDayWiseAvailability.entrySet()) {
                    if (entry.getValue().contains(checkAllocation.getKey()) && entry.getValue().size() > 2) {
                        entry.getValue().remove(checkAllocation.getKey());
                        allocation.put(checkAllocation.getKey(), checkAllocation.getValue() - 1);
                    }
                }
            }

            
        }

        System.out.println("-----------------------------------------------------------");

        // for (Map.Entry<String, ArrayList<String>> entry : convertedToDayWiseAvailability.entrySet()) {
        //     if(entry.getValue().size()<2){
        //         String keys[] = requests.keySet().toArray(new String[requests.keySet().size()]);
        //         for(int i=0; i < keys.length;i++){
        //             if(!keys[i].equals(entry.getValue())){

        //             }
        //         }
        //     }
        // }

        for (Map.Entry<String, Integer> checkAllocation : allocation.entrySet()) {
            System.out.println(checkAllocation.getKey() + " is assigned " + checkAllocation.getValue());
            // if (checkAllocation.getValue() > 5) {
            //     return false;
            // }
        }

        for (Map.Entry<String, ArrayList<String>> entry : convertedToDayWiseAvailability.entrySet()) {
            System.out.print("Key is " + entry.getKey() + "  Value is ");
            for (String day : entry.getValue()) {
                System.out.print(day + " ");
            }
            System.out.println();
        }

        for (Map.Entry<String, ArrayList<String>> entry : convertedToDayWiseAvailability.entrySet()) {
            if(entry.getValue().size()<2){
                return false;
            }
        }

        return true;

    }
}
