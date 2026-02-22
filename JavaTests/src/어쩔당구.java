public class 어쩔당구 {
    static final double TABLE_W = 254.0;
    static final double TABLE_H = 127.0;
    static final double RADIUS = 2.865; // 공의 반지름 (
    static final double PI = 3.141592;
    static final double MU      = 0.1;    // 마찰계수
    static final double G       = 9.81;   // 중력가속도

    static double savedTheta = 0.0; // 이전에 계산된 최적 각도 (라디안)
    static double savedB = 0.0;     // 이전에 계산된 최적
    static double savedV = 0.0;     // 이전에 계산된 최적 power

    public static double[] hit(int[] myBall, int[] target, int[] hole) {
        double myX = myBall[0], myY = myBall[1];
        double tgX = target[0], tgY = target[1];
        double hX  = hole[0],   hY  = hole[1];

        // 직접 노리기: my ball → target을 쳐서 hole로
        double angle = calcAngleToHole(myX, myY, tgX, tgY, hX, hY);
        double power = calcPower(); // theta, b는 필드에서 가져옴

        savedV = power-1;

        verify(); // 계산된 theta, power로 target이 hole에 도달하는지 검증

        return new double[]{angle, power};
    }
    // 세타는 가 각도 + 나 각도
    private static double calcAngleToHole(double myX, double myY, double tgX, double tgY, double hX, double hY) {
        // 1. 각 거리 구하기
        double a = Math.hypot(hX - myX, hY - myY);     // my ball ~ Hole
        double b = Math.hypot(hX - tgX, hY - tgY); // target ~ Hole
        double c = Math.hypot(tgX - myX, tgY - myY); // my ball ~ target

        // 2. 피타고라스로 각도 구하기 = 각도 (가) = my ball → hole의 각도
        double x = hX - myX;   // 가로 거리
        double y = hY - myY;   // 세로 거리
        double angleGa = Math.atan2(x, y); // 각도 (가), 라디안 값

        double angleGaDegrees = Math.toDegrees(angleGa); // 각도 (가), 도 단위
        if (angleGaDegrees < 0) {
            angleGaDegrees += 360; // 음수인 경우 양수로 변환
        }

        // 3. 코사인 법칙을 이용한 각도 계산 = 각도 (다) = target → hole의 각도
        double cosDa = (b*b + a*a - c*c) / (2 * b * a); // 코사인 법칙을 이용한 각도 계산
        double angleDa = Math.acos(cosDa); // 각도 (다), 라디안 값 -> 0도는 위 90도는 오른쪽 180도는 아래 270도는 왼쪽

        // 4. 각도 (나)를 알면 my ball이 타격해야하는 거리 d 를 구할 수 있음 (코사인 법칙)
        double bPlus2r = b + 2 * RADIUS;
        double d = Math.sqrt(a*a + bPlus2r*bPlus2r - 2*a*bPlus2r*Math.cos(angleDa)); // my ball이 타격해야하는


        // 5. 각도 (나) = acos(d*d +a*a - (b+2r)*(b+2r)) / (2*a*d))
        double cosNa = (d*d + a*a - (b+2*RADIUS)*(b+2*RADIUS)) / (2 * d * a);
        double angleNa = Math.acos(cosNa); // 각도 (나), 라디안 값

        double angleNaDegrees = Math.toDegrees(angleNa); // 각도 (나), 도 단위
        if (angleNaDegrees < 0) {
            angleNaDegrees += 360; // 음수인 경우 양수로 변환
        }

        double theta = angleGa + angleNa; // (가)+(나)_총 출발 각도, 라디안 값

        savedTheta = theta; // 송구 각도 저장
        savedB = b; // 목적구에서 홀까지의 거리 저장

        return theta;
    }

    private static double calcPower() {
        // 저장된 theta, b로 최적 power 계산
        // vt = v * cos(θ), 이동거리 = vt² / (2μg) >= b 이어야 함
        // → v >= sqrt(2μg * b) / cos(θ)
        return Math.sqrt(2 * MU * G * savedB) / Math.cos(savedTheta); // 1~100 클램프
    }

    // ⭐ 검증 메서드
    private static void verify() {
        double v  = savedV;
        double vt = v * Math.cos(savedTheta);           // 충돌 후 target 속도
        double distanceTarget = (vt * vt) / (2 * MU * G); // 충돌 후 이동 거리

        System.out.println("=== 검증 ===");
        System.out.printf("충돌 후 target 이동거리 : %.5f cm%n", distanceTarget);
        System.out.printf("Hole까지 거리(b)       : %.5f cm%n", savedB);

        if (distanceTarget >= savedB) {
            System.out.println("✅ Hole 도달 가능!");
        } else {
            System.out.println("❌ Hole 도달 불가! power 부족");
        }
        System.out.println("============");
    }

    public static void main(String[] args) {
        // 테스트
        int[] myBall = {0, 0};
        int[] target = {100, 50};
        int[] hole   = {254, 127};

        double[] result = hit(myBall, target, hole);
        System.out.printf("angle: %.5f도%n", Math.toDegrees(result[0])); // 라디안 → 도 단위로 출력
        System.out.printf("power: %.5f%n", result[1]);


    }
}
