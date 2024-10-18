class Calculate_HK {

    Object[][] array = {
            { "mod#", "a", "b", "c", "d", "e", "f", "g", "h" },
            { "in  ", 4, 3, 1, 5, 2, 5, 6, 1 },
            { "out ", 3, 3, 4, 3, 4, 4, 2, 6 }
    };

    double[] HKArray = new double[8];

    public void calculate() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public void calculate_HK_For_each_module(int weight) {
        // Iterate over each module (from column 1 to the end)
        for (int j = 1; j < array[0].length; j++) {
            String moduleName = (String) array[0][j];
            int inValue = (int) array[1][j];
            int outValue = (int) array[2][j];

            // Calculate HK = weight * (out * in)^2
            double HK = weight * Math.pow(outValue * inValue, 2);

            // Store the result in the HKArray
            HKArray[j - 1] = HK;

            // Print the result
            System.out.println("HK" + moduleName + " = " + HK);

        }
    }

    public void calculate_HK() {
        System.out.println("\n");
        double sum = 0;
        for (int i = 0; i < HKArray.length; i++) {
            sum += HKArray[i];
        }
        System.out.println("HK Information Factor = " + sum);
    }
}

public class HK_Factor {
    public static void main(String[] args) {
        Calculate_HK hk = new Calculate_HK();
        hk.calculate();

        int weight = 1;

        hk.calculate_HK_For_each_module(weight);

        hk.calculate_HK();
    }
}

// Output
// mod# a b c d e f g h
// in 4 3 1 5 2 5 6 1
// out 3 3 4 3 4 4 2 6

// HKa = 144.0
// HKb = 81.0
// HKc = 16.0
// HKd = 225.0
// HKe = 64.0
// HKf = 400.0
// HKg = 144.0
// HKh = 36.0

// HK Information Factor = 1110.0