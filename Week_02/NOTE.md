> # 学习笔记

> ## HashMap小结

> * get分析

```
 294:   public V get(Object key)
 295:   {
 296:     int idx = hash(key);
 297:     HashEntry<K, V> e = buckets[idx];
 298:     while (e != null)
 299:       {
 300:         if (equals(key, e.key))
 301:           return e.value;
 302:         e = e.next;
 303:       }
 304:     return null;
 305:   }
 ```
 
 > 首先对key求出哈希值，根据hash值求出在桶中的位置，每个桶的位置维护一条链表，
 遍历这条链表找到key所对应的值。如果找不到，返回null。由于值也有可能是null，因此不能根据
 返回值是否为null来判断该key是否存在。
 
 > * put分析
 
 ```
  public V put(K key, V value)
	...
	...
	...
```

> 对key求出hash值，在桶中找对应的位置。如果找到的位置对应元素非空，遍历该位置维护的链表，
如果找到对应的值，用新值替换掉老的值，并且返回老的值。

>  如果没找到旧值，元素个数加1，判断是否需要扩大桶的大小，如果需要，对桶大小进行调整。根据key的hash值插入一个新的元素，返回null。

> * 时间复杂度

> 平均情况下，查找、插入、删除的时间复杂度都是O(1); 

> 最坏情况下，查找、插入、删除的时间复杂度都是O(n).



	