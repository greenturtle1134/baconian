package ciphers.baconian;
import static java.lang.Math.pow;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class Baconian <T, S extends Comparable<S>>{
	public final TreeMap<S,Integer> key;
	public final int base;
	public final int bitLength;
	public final T[] symbolSet;
	
	public Baconian(T[] symbolSet, S[] dataSet) {
		this.key = new TreeMap<S,Integer>();
		this.base = symbolSet.length;
		this.symbolSet = symbolSet;
		int i=0;
		int c=1;
		while(c<dataSet.length) {
			i++;
			c*=base;
		}
		this.bitLength = i;
		ArrayList<S> symbolPool = new ArrayList<S>();
		for(S s : dataSet) {
			symbolPool.add(s);
		}
		while(!symbolPool.isEmpty()) {
			//Assign to a random key
			int dest = (int) (Math.random()*pow(base, bitLength+1));
			while(key.containsValue(dest)) {
				dest = (int) (Math.random()*pow(base, bitLength+1));
			}
			key.put(symbolPool.remove(0), dest);
		}
	}
	
	public int getBase() {
		return base;
	}

	public int getBitLength() {
		return bitLength;
	}

	public T[] getSymbolSet() {
		return symbolSet.clone();
	}

	public List<T> encode(S string) {
		int n = key.get(string);
		List<T> result = new LinkedList<T>();
		while(result.size()<bitLength) {
			result.add(0, symbolSet[n%base]);
			n/=base;
		}
		
		return new ArrayList<T>(result);
	}
}
