package MinMaxAlgo;

import java.util.Comparator;



public class APoint implements Comparable<APoint> {
    public int x;
    public int y;
    public static Comparator<APoint> XComparator = new Comparator() {


        @Override
        public int compare(Object lhs, Object rhs) {
            return 0;
        }

        public int compare(APoint var1, APoint var2) {
            return var1.x - var2.x;
        }
    };
    public static Comparator<APoint> YComparator = new Comparator() {
        @Override
        public int compare(Object lhs, Object rhs) {
            return 0;
        }

        public int compare(APoint var1, APoint var2) {
            return var1.y - var2.y;
        }
    };

    public APoint(int var1, int var2) {
        this.x = var1;
        this.y = var2;
    }

    public String toString() {
        return "" + this.x + "," + this.y;
    }

    public int compareTo(APoint var1) {
        return this.x - var1.x;
    }

    public static APoint[] concat(APoint[] var0, APoint[] var1) {
        int var2 = var0.length;
        int var3 = var1.length;
        APoint[] var4 = new APoint[var2 + var3];

        int var5;
        for(var5 = 0; var5 <= var2 - 1; ++var5) {
            var4[var5] = var0[var5];
        }

        for(var5 = 0; var5 <= var3 - 1; ++var5) {
            var4[var5 + var2] = var1[var5];
        }

        return var4;
    }
}

