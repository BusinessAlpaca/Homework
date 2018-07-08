import java.util.ArrayList;

public class crap {

	public static void main(String[] args) {
		ArrayList<int[]> numbers1 = new ArrayList<int[]>();
		int[] a = {1, 2};
		numbers1.add(a);
		System.out.println(numbers1.get(0)[0]);
		System.out.println(numbers1.get(0)[1]);
		
		ArrayList<Integer[]> numbers2 = new ArrayList<Integer[]>();
		Integer[] b = new Integer[2];
		b[0] = Integer.valueOf(1);
		b[1] = Integer.valueOf(2);
		numbers2.add(b);
		System.out.println(numbers2.get(0)[0]);
		System.out.println(numbers2.get(0)[1]);

	}

}
