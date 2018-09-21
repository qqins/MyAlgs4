package company.dahua;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Hello World
 * @date: 2018/9/20 11:32
 */
public class ShootCar {
    private int adrId;
    private String carNum;
    private Date shootTime;

    class ShootCarService {
        public float getAvgTravel(int beginAddress, int endAddress, List<ShootCar> shootCars) {
            float res = 0;
            int count = 0;
            float start = 0;
            float end = 0;
            HashMap<String, Long> map = new HashMap<>();
            for (ShootCar shoot : shootCars) {
                if (shoot.adrId >= beginAddress && shoot.adrId <= endAddress) {
                    if (map.containsKey(shoot.carNum)) {
                        res += (map.get(shoot.carNum) - shoot.shootTime.getTime()) / 1000;
                        count++;
                    } else {
                        map.put(shoot.carNum, shoot.shootTime.getTime());
                    }
                }
            }
            return (float) (Math.round((res / count) * 100)) / 100;
        }
    }
}
