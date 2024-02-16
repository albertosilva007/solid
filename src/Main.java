import java.util.HashMap;
import java.util.Map;

public class HotelReservationSystem {

    static class Hotel {
        String name;
        int stars;
        int weekdayRegularRate;
        int weekdayVipRate;
        int weekendRegularRate;
        int weekendVipRate;

        public Hotel(String name, int stars, int weekdayRegularRate, int weekdayVipRate, int weekendRegularRate, int weekendVipRate) {
            this.name = name;
            this.stars = stars;
            this.weekdayRegularRate = weekdayRegularRate;
            this.weekdayVipRate = weekdayVipRate;
            this.weekendRegularRate = weekendRegularRate;
            this.weekendVipRate = weekendVipRate;
        }

        public int calculateCost(boolean isVip, boolean isWeekend, int numDays) {
            int rate = isWeekend ? (isVip ? weekendVipRate : weekendRegularRate) : (isVip ? weekdayVipRate : weekdayRegularRate);
            return rate * numDays;
        }
    }

    public static void main(String[] args) {
        Hotel palmPlaza = new Hotel("Palm Plaza Resor", 3, 110, 80, 90, 80);
        Hotel eleganceHaven = new Hotel("Elegance Haven Hotel", 4, 160, 110, 60, 50);
        Hotel azureHorizon = new Hotel("Azure Horizon Retreat", 5, 220, 100, 150, 40);

        // Sample input dates
        String input = "Regular: 16Mar2020(mon), 17Mar2020(tues), 18Mar2020(wed)";

        // Parse input
        boolean isVip = input.contains("Fidelidade");
        boolean isWeekend = input.contains("sat") || input.contains("sun");
        int numDays = input.split(",").length;

        Map<Hotel, Integer> hotelCosts = new HashMap<>();
        hotelCosts.put(palmPlaza, palmPlaza.calculateCost(isVip, isWeekend, numDays));
        hotelCosts.put(eleganceHaven, eleganceHaven.calculateCost(isVip, isWeekend, numDays));
        hotelCosts.put(azureHorizon, azureHorizon.calculateCost(isVip, isWeekend, numDays));

        // Find the cheapest hotel
        Hotel cheapestHotel = null;
        int minCost = Integer.MAX_VALUE;
        int maxStars = 0;

        for (Map.Entry<Hotel, Integer> entry : hotelCosts.entrySet()) {
            Hotel hotel = entry.getKey();
            int cost = entry.getValue();

            if (cost < minCost || (cost == minCost && hotel.stars > maxStars)) {
                minCost = cost;
                maxStars = hotel.stars;
                cheapestHotel = hotel;
            }
        }

        System.out.println(cheapestHotel.name);
    }
}
