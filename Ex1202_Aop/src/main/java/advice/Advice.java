package advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

public class Advice {

	long start;
	
	public void before(JoinPoint jp) {
		Signature s = jp.getSignature();
		
		start = System.currentTimeMillis();
		
		System.out.println("--- before : " + s);
		
		
	}

	public void after(JoinPoint jp) {
		Signature s = jp.getSignature();
		
		long end = System.currentTimeMillis();
		
		System.out.println("--- after : " + s);
		
		System.out.printf("[수행시간]: %d\n", end - start);
	}
}
