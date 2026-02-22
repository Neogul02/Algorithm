package SSAFYTest;

public class solution {
    public static void main(String[] args) {
        double myX = 0, myY = 0;       // my ball
        double targetX = 5, targetY = 3; // target(목적구)
        double holeX = 5, holeY = 8;   // Hole
        double r = 2.865;              // 공의 반지름 (지름 5.73 / 2)

        double a = Math.hypot(holeX - myX, holeY - myY);     // my ball ~ Hole
        double b = Math.hypot(holeX - targetX, holeY - targetY); // target ~ Hole
        double c = Math.hypot(targetX - myX, targetY - myY); // my ball ~ target

            // 이미지 6에서: arctan(x/y) → Hole까지의 x거리, y거리
        double x = holeX - myX;   // 가로 거리
        double y = holeY - myY;   // 세로 거리

        double angleGa = Math.atan2(x, y); // 각도 (가), 라디안

        double cosDa = (b*b + c*c - a*a) / (2 * b * c); // 코사인 법칙을 이용한 각도 계산
        double angleDa = Math.acos(cosDa); // 각도 (다), 라디안

        // 각도 다를 기준으로 acosDa((b+2r)^(b+2r)+a*a-d*d) / (2*a*(b+2r))) 인데 우리는 d값을 알고싶음
        // d는 그럼 d = sqrt(a*a+b*b-cos(다)*2*a*b)로 구할 수 있음
        double d = Math.sqrt(a*a + b*b - 2*a*b*cosDa);

        // 그럼 각도 (나) 도 구할 수 있음 어떻게? 코사인법칙
        // 각도 (나) = acos(d*d +a*a - (b+2r)*(b+2r)) / (2*a*d))
        double cosNa = (d*d + a*a - (b+2*r)*(b+2*r)) / (2 * a * d);
        double angleNa = Math.acos(cosNa); // 각도 (나), 라디안

        // 각도 가+나 하면 my ball이 출발해야 하는 각도가 나옴
        double totalAngle = angleGa + angleNa; // 총 출발 각도, 라디안
        double totalAngleDegrees = Math.toDegrees(totalAngle); // 총 출발 각도, 도 단위

        // v = power , u = 마찰계수,
        double v = 70.67755126878367; // 초기 속도 (power)
        double mu = 0.1; // 마찰 계수 (예시값)
        double g = 9.81; // 중력 가속도
        //1. 충돌 후 target의 속도 vt = v * cos(totalAngelDegrees) // 라디안 으로 계산해야 함
        double vt = v * Math.cos(totalAngle);

        // 2. 충돌 후 target의 이동거리
        double distanceTarget = vt*vt / (2 * mu * g); // 마찰로 인한 감속을 고려한 이동거리 계산
            System.out.println("충돌 후 target의 이동거리: " + distanceTarget);
            System.out.println("Hole까지의 거리: " + b);
        // 최적의 value(power)로 target이 Hole에 도달할 수 있는지 확인
        double optimalPower = Math.sqrt(2 * mu * g * b) / Math.cos(totalAngle); // Hole까지 도달하기 위한 최적의 power 계산
        System.out.println("Hole까지 도달하기 위한 최적의 power: " + optimalPower);


        if (distanceTarget > b) {
        System.out.println("Hole에 도달 가능!");
        } else {
            System.out.println("Hole에 도달 불가능...");
        }
    }

}
