> # 学习笔记

>> 1. 移动零一开始想到的是产生一个新的数组，把原数组中非0的元素复制过去，但不符合题目空间复杂度的要求。

>> 2. 然后想到的是采用两个指针，一个快，一个慢，把快指针中非0元素移动到慢指针，值得注意的是最后慢指针后面的元素都要设置为0.

>> 3. 另外一种方法是交换快慢指针的内容，快指针指向的元素为非0.慢指针指向的内容为0，交换后，把非0元素都移动到前面，后面的元素都是0.

>> 4. 选择数组首先想到的是分段逆转，再总体逆转，这种最简洁。
还有其他方式：

>>>  * 采用交换两段的方式，其中可以用循环，也可用递归。

>>> *  循环移动的方式，基于一个数学思想，一段环形的数组，移动其中m个相连数字，每个数字与相隔k的数字组成一组，旋转一组数字，总是能找到合适的位置。

>> 5. 刚开始的时候每个题目都采用几种不同的语言实现，例如python，java，go，cpp。但几次下来，发现效率较低，
看题解的时间就少了，不能吸收先进生产力。因此改为采用java与python实现，
选择java的原因是java是使用最广泛的语言，选择python是由于python使用也很广泛，并且代码通常很简洁。

>> 6. 学习方法很重要。控制每道题目的时间。


# 分析 Queue 和 Priority Queue 的源码

## 分析Queue

    Queue是一个接口，定义了添加，删除，返回头元素的方法。
	
### 提供了以下方法：
* 添加元素：

    add(E e) 空间不足抛异常
	
	offer(E e) 空间不足不抛异常
	
* 删除元素：

    remove() 队列为空抛异常
	
	poll()   队列为空不抛异常
	
* 返回队列头元素：

    element() 队列为空抛异常
	
	peek()    队列为空返回null

## 分析Priority Queue

PriorityQueue是一个是类，继承了抽象类AbstractQueue。

里面有数据成员表示容量大小，以及当前的使用数量。底层存储结构是数组。

用户可以提供一个比较器，比较器为空时使用自然排序。

### 初始化几种方式：

* PriorityQueue(Collection<? extends E> c)，Collection<? extends E>对象
* PriorityQueue(int cap)， 指定容量
* PriorityQueue(int cap, Comparator<? super E> comp)，指定容量，带上比较器
* PriorityQueue(PriorityQueue<? extends E> c)，另一个优先队列
* PriorityQueue(SortedSet<? extends E> c)，SortedSet<? extends E>对象

### 迭代器

Iterator<E> iterator()，返回一个迭代器

### 插入元素

···  
   public boolean offer(E o)
 181:   {
 182:     if (o == null)
 183:       throw new NullPointerException();
 184: 
 185:     int slot = findSlot(-1);
 186: 
 187:     storage[slot] = o;
 188:     ++used;
 189:     bubbleUp(slot);
 190: 
 191:     return true;
 192:   }
 ···
 
 先查找空闲槽位，时间复杂度为O(n),赋值O(1),
 调整位置O(log(N))
 
 ### 取出元素
 
 ···
 public E peek()
 195:   {
 196:     return used == 0 ? null : storage[0];
 197:   }
 198: 
 199:   public E poll()
 200:   {
 201:     if (used == 0)
 202:       return null;
 203:     E result = storage[0];
 204:     remove(0);
 205:     return result;
 206:   }
 ···
 
 时间复杂度O(1)
 
 ### 删除元素：
 
 ···
 208:   public boolean remove(Object o)
 209:   {
 210:     if (o != null)
 211:       {
 212:         for (int i = 0; i < storage.length; ++i)
 213:           {
 214:             if (o.equals(storage[i]))
 215:               {
 216:                 remove(i);
 217:                 return true;
 218:               }
 219:           }
 220:       }
 221:     return false;
 222:   }
 ···
 
 ### 调整容量大小
 
 ···
  330:   void resize()
 331:   {
 332:     E[] new_data = (E[]) new Object[2 * storage.length];
 333:     System.arraycopy(storage, 0, new_data, 0, storage.length);
 334:     storage = new_data;
 335:   }
 
 ···
 






