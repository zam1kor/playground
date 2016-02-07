package com.dlo.wordpool.prj.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

public class Pool {

	private int id;

	private String name;

	private Table<Integer, String, Word> wordsMap;

	private PoolManager manager;


	protected Pool( int id, String name, PoolManager manager){
		this.id=id;
		this.name=name;
		this.manager=manager;
		wordsMap=HashBasedTable.create() ;
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public Collection< Word> getAllWords() {
		return wordsMap.values();
	}


	public PoolManager getPoolManager() {
		return manager;
	}


	/*
	 * This method should ensure that duplicate words are not stored.
	 * Note: This method is case sensitive
	 */
	public Word addWord(Integer ID, String text){


		if(wordsMap.contains(ID, text)){
			return wordsMap.get(ID, text);
		}

		Word word = new Word(ID,text,this);


		wordsMap.put(ID, text, word);

		return word;


	}

	protected Word addWord(Word word){


		if(wordsMap.contains(word.getID(), word.getText())==false){

			wordsMap.put(word.getID(), word.getText(), word);
		}

		return word;

	}


	public Word getWord(Integer ID){

		Map<String, Word> row1 = wordsMap.row(ID);

		if(row1 != null){

			return row1.values().size()>0?row1.values().toArray(new Word[6])[0]:null;
		}

		return null;
	}

	public Collection<Word> getWords(String  text){

		Map<Integer, Word> column = wordsMap.column(text);

		if(column != null){

			return column.values() ;
		}

		return null;
	}


	public boolean isWordIDExists( Integer id){

		return wordsMap.containsRow(id);
	}

	public boolean isWordExists(String text){
		return wordsMap.containsColumn(text);
	}
	
	/**
	 * This method is used to obtain all the word objects based on the supplied regular expression
	 * @param regex
	 * @return List<Word> Word objects which are matching to the specified regex
	 */
	public List<Word> getWords_regexSearch(final String regex){
		

		Set<String> allWordsSet =wordsMap.columnKeySet();
		
		Predicate<String> matchesWithRegex = new Predicate<String>() {
		        @Override 
		        public boolean apply(String str) {
		            return str.matches(regex);
		        }               
		};
		
		Iterable<String> iterable = Iterables.filter(allWordsSet, matchesWithRegex);
		
		Iterator<String> iterator=iterable.iterator();

		ArrayList<Word> resultList = Lists.newArrayList();
		
		while(iterator.hasNext()){
			Map<Integer, Word> map = wordsMap.columnMap().get(iterator.next());
			
			Collection<Word> values = map.values();
			
			resultList.addAll(values);
			
		}
		
		return resultList;
	}
	
	/**
	 * This method is used to search if words exists based on the supplied regular expression
	 * @param regex
	 * @return boolean true if there exists Word objects matching to the supplied regex   else false is returned
	 */
	public boolean isWordExists_regexSearch(final String regex){
		

		Set<String> allWordsSet =wordsMap.columnKeySet();
		
		Predicate<String> matchesWithRegex = new Predicate<String>() {
		        @Override 
		        public boolean apply(String str) {
		            return str.matches(regex);
		        }               
		};
		
		Iterable<String> iterable = Iterables.filter(allWordsSet, matchesWithRegex);
		
		Iterator<String> iterator=iterable.iterator();

		
		if(iterator.hasNext()){
			 return true;
			
		}
		
		return false;
	}
	

	@Override
	public String toString() {
		return "Pool [id=" + id + ", name=" + name + ", wordsMap=" + wordsMap
				+ ", manager=" + manager + "]";
	}

}
