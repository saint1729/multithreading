package com.cop.courses.multithreading;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class _13CallableAndFuture {
	
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		Future<?> future = executor.submit(new Callable<Integer>() {
			
			public Integer call() throws Exception {
				
				System.out.println("Starting ...");
				
				final int duration = new Random().nextInt(4000);
				
				if(duration > 2000) {
					throw new IOException("Sleeping for too long.");
				}
				
				Thread.sleep(duration);
				
				System.out.println("Finished.");
				
				return duration;
			}
		});
		
		
		executor.shutdown();
		
		try {
			System.out.println("Result is: " + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			
			IOException ex = (IOException) e.getCause();
			
			System.out.println(ex.getMessage());
		}
		
	}
	
}
