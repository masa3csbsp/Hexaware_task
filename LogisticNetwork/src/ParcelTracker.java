
import java.util.HashMap;
import java.util.HashSet;

public class ParcelTracker {

    // Tracking number -> Set of scanned hubs
    private HashMap<String, HashSet<String>> parcelMap =
            new HashMap<>();

    // Check whether a parcel was scanned twice at same hub
    public void scanParcel(String trackingNumber, String hubCode) {

        // Get existing hubs for this parcel
        HashSet<String> hubs = parcelMap.get(trackingNumber);

        // If parcel is scanned for first time
        if (hubs == null) {
            hubs = new HashSet<>();
            parcelMap.put(trackingNumber, hubs);
        }

        // Check duplicate scan
        if (hubs.contains(hubCode)) {
            System.out.println(
                "Duplicate scan detected: " +
                trackingNumber + " at " + hubCode
            );
        } else {
            hubs.add(hubCode);

            System.out.println(
                "Scan recorded: " +
                trackingNumber + " at " + hubCode
            );
        }
    }

    // Display all parcel tracking information
    public void displayTracking() {

        System.out.println("\n--- PARCEL TRACKING DETAILS ---");

        for (String trackingNumber : parcelMap.keySet()) {
            System.out.println(
                trackingNumber + " -> " + parcelMap.get(trackingNumber)
            );
        }
    }
}
