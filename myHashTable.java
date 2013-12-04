//
/**
 * @author Toviah
 * Implementation of HashTable for strings with chaining for collision resolution
 */
public class myHashTable {
	private HashEntry[] entries;
	private int size;

	private class HashEntry {
		private String key;
		private String value;
		private HashEntry next;

		public HashEntry(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public void setNext(HashEntry entry) {
			next = entry;
		}

		public HashEntry getNext() {
			return next;
		}

	}

	public myHashTable(int size) {
		this.size = size;
		entries = new HashEntry[size];
	}

	public void put(String key, String value) {
		int hash = hash(key);
		HashEntry entry = new HashEntry(key, value);
		if (entries[hash] == null) {
			entries[hash] = entry;
		} else {
			HashEntry currentEntry = entries[hash];
			while (currentEntry.getNext() != null) {
				currentEntry = currentEntry.getNext();
			}
			currentEntry.setNext(entry);
		}
	}

	public String get(String key) {
		int hash = hash(key);
		HashEntry entry = entries[hash];
		if (entry != null) {
			while (entry != null) {
				if (entry.getKey().equals(key)) {
					return entry.getValue();
				} else
					entry = entry.getNext();
			}
		}
		return null;
	}

	private int hash(String key) {
		int hashValue = 0;
		char[] keyArray = key.toCharArray();
		for (int i = 0; i < keyArray.length; i++) {
			char c = keyArray[i];
			hashValue += 31*i*Character.getNumericValue(c);
		}
		hashValue = hashValue % size;
		return hashValue;
	}
	
	public static void main(String[] args){
		myHashTable hashy = new myHashTable(50);
		hashy.put("me", "Toviah");
		hashy.put("em", "Zach");
		hashy.put("you", "Bob");
		System.out.println(hashy.get("me"));
		System.out.println(hashy.get("em"));
		System.out.println(hashy.get("you"));
	}
}
