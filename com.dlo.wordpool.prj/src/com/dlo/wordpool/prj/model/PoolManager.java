package com.dlo.wordpool.prj.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class PoolManager {

	private   Table<Integer, String, Pool> poolMap;


	private static PoolManager poolManager;

	private PoolManager(){

		poolMap=HashBasedTable.create();

	}

	/**
	 * Singleton method
	 * @return
	 */
	public static PoolManager getInstance(){

		if(poolManager==null){
			poolManager=new PoolManager();
		}

		return poolManager;
	}

	/**
	 * This method is used to return all unique pool IDS
	 * @return
	 */
	public Set<Integer> getPoolIDs(){

		return poolMap.rowKeySet();
	}

	/**
	 * This method is used to get all Pool names
	 * @return
	 */
	public Collection<String> getPoolNames() {

		Collection<Pool> values = poolMap.values();

		List<String> poolNames = new ArrayList<String>();

		for (Pool pool : values) {

			poolNames.add(pool.getName());
		}

		return poolNames;

	}


	/**
	 * This method is used to get all Pool objects
	 * @return
	 */
	public Collection<Pool> getPools(){
		return poolMap.values();
	}

	/**
	 * This method creates Pool object and returns the same
	 * TODO: find out if validation is to be performed if Pool with the same ID is already existing
	 * @param ID
	 * @param poolName
	 * @return
	 */

	public Pool createPool(int ID, String poolName ){

		if(poolMap.containsRow(ID) && poolMap.containsColumn(poolName)){
			return poolMap.get(ID,poolName);
		}

		Pool pool=new Pool(ID, poolName, this);

		poolMap.put(pool.getId(),poolName, pool);

		return pool;
	}

	public Pool getPool(String name){

		boolean poolExists=poolMap.containsColumn(name);

		if(poolExists){

			Map<Integer, Pool> map=poolMap. column(name);
			return map.values().size()>0? map.values().toArray(new Pool[10])[0]:null;
		}

		return null;
	}

	public Collection<Pool> getAllPoolsUsingWord(Integer wordID){

		Collection<Pool> pools1 = this.getPools();

		List<Pool> targetPools=new ArrayList<Pool>();

		for (Pool p : pools1) {

			boolean v=p.isWordIDExists(wordID);

			if(v){
				targetPools.add(p);
			}
		}

		return targetPools;
	}

	public Collection<Pool> getAllPoolsUsingWord(String text){

		Collection<Pool> pools1 = this.getPools();

		List<Pool> targetPools=new ArrayList<Pool>();

		for (Pool p : pools1) {

			boolean v=p.isWordExists(text);

			if(v){
				targetPools.add(p);
			}
		}

		return targetPools;
	}

	public Collection<Pool> getAllPoolsUsingWord_regexSearch(String regex){

		Collection<Pool> pools1 = this.getPools();

		List<Pool> targetPools=new ArrayList<Pool>();

		for (Pool p : pools1) {

			boolean v=p.isWordExists_regexSearch(regex);

			if(v){
				targetPools.add(p);
			}
		}

		return targetPools;
	}

	public List<Word> getWordObjectsFromAllPools(String wordValue){

		List<Word> targetWords=new ArrayList<Word>();

		Collection<Pool> pools1 = this.getPools();

		for (Pool pool : pools1) {

			Collection<Word> words = pool.getWords(wordValue);

			if(words!=null){
				targetWords.addAll(words);
			}
		}
		return targetWords;
	}

	public List<Word> getWordObjectsFromAllPools_regexSearch(String regex){

		List<Word> targetWords=new ArrayList<Word>();

		Collection<Pool> pools1 = this.getPools();

		for (Pool pool : pools1) {

			Collection<Word> words = pool.getWords_regexSearch(regex);

			if(words!=null){
				targetWords.addAll(words);
			}
		}
		return targetWords;
	}
}
