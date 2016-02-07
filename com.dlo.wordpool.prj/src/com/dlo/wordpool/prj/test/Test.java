package com.dlo.wordpool.prj.test;

import java.util.Collection;
import java.util.List;

import com.dlo.wordpool.prj.model.Pool;
import com.dlo.wordpool.prj.model.PoolManager;
import com.dlo.wordpool.prj.model.Word;

public class Test { 

	public static void main(String[] args) {
		
		
		PoolManager poolManager=PoolManager.getInstance();
		
		
			Pool pool = poolManager.createPool(1, "First_Test");
			
			pool.addWord(1, "Hello");
			
			pool.addWord(2, "Germany");
			
			pool.addWord(3, "Italy");
			
			pool.addWord(4, "India");
			
			
	        Pool pool2 = poolManager.createPool(2, "Second_Test");
			
	        pool2.addWord(5, "Wie");
			
	        pool2.addWord(6, "ist");
			
	        pool2.addWord(7, "Deutschland");
			
	        pool2.addWord(8, "jetzt");
			
			
			
			
		System.out.println("***************************************************************************************");
		
		System.out.println("Printing all pool names registerd to the PoolManager: ");
		
		Collection<String> poolNames = poolManager.getPoolNames();
		
		for (String poolName : poolNames) {
			
			System.out.println(poolName);
			
		}
		
		
		System.out.println("***************************************************************************************");

		System.out.println("Fetching pool names based on word ID \"1\": ");

		Collection<Pool> allPoolsHavingWordID = poolManager.getAllPoolsUsingWord(1);
		
		for (Pool pool_ : allPoolsHavingWordID) {

			System.out.println(pool_.getName());

		}


		System.out.println("***************************************************************************************");
		
		System.out.println("Fetching pool names based on word \"Hello\": ");
		
		Collection<Pool> allPoolsUsingWord2 = poolManager.getAllPoolsUsingWord("Hello");
		
		for (Pool pool_ : allPoolsUsingWord2) {

			System.out.println(pool_.getName());

		}
		
		System.out.println("***************************************************************************************");
		
		System.out.println("Fetching pool names which contain word with following regular expression \"G.*\": ");
		
		
		Collection<Pool> allPoolsUsingWord_regexSearch = poolManager.getAllPoolsUsingWord_regexSearch("G.*");
		
		for (Pool pool_ : allPoolsUsingWord_regexSearch) {

			System.out.println(pool_.getName());

		}
		System.out.println("***************************************************************************************");
		
		System.out.println("Fetching all word objects which are matching to regular expression \"I.*\": ");
		
		List<Word> wordObjectsFromAllPools_regexSearch = poolManager.getWordObjectsFromAllPools_regexSearch("I.*");
		
		for (Word word : wordObjectsFromAllPools_regexSearch) {
			
			System.out.println(word.getText());
		}
		
		System.out.println("***************************************************************************************");
		
		
		
		
//		logger.log(Level.ERROR, "hello");
	}
}
