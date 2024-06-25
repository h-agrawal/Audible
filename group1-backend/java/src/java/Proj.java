package java;

import java.util.Arrays;
import java.util.List;

public class Proj {

	public static void main(String[] args) {
		List<String> strList = Arrays.asList("abc", "", "bcd", "", "defg", "jk");
		long ans=strList.stream().filter(m->m.isBlank()).count();
		System.out.println(ans);
	}
}
