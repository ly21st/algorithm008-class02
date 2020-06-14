
# 191. 位1的个数

编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。 示例 1：输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。


## 解法1 循环移位
```
        
    public class Solution {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int count = 0; 
            int mask = 1;
            for (int i = 0; i < 32; i++) {
                if ((n & mask) != 0) {
                    count++;
                }
                mask <<= 1;
            }
            return count;
        }
    }

```

## 解法2 循环移位

```
   
    public class Solution {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int count = 0; 
            for (int i = 0; i < 32; i++) {
                if ((n & 0x01) == 1) {
                    count++;
                }
                n >>= 1;
            }
            return count;
        }
    }
```

## 解法3 位操作的小技巧
```

    public class Solution {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int count = 0; 
            while(n != 0) {
                n = n & (n - 1);
                count++;
            }
            return count;
        }
    }
```

# 231. 2的幂
给定一个整数，编写一个函数来判断它是否是 2 的幂次方

## 思路
求出整数中含有1的个数，如果整数是2的幂次方只含有一个1.
注意要区分n是整数还是负数的情况，负数不可能是2的幂次方。

```

    class Solution {
        public boolean isPowerOfTwo(int n) {
            if (n < 0) return false;
            return oneCount(n) == 1;
        }
        public int oneCount(int n) {
            int count = 0;
            while(n != 0) {
                n = n & (n - 1);
                count++;
            }
            return count;
        }
    }

```


# 338. 比特位计数难度中等

给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。示例 1:输入: 2
输出: [0,1,1]

## 暴力法
分别求出每一个数字包含1的个数，并分别存入数组。
```
        
    class Solution {
        public int[] countBits(int num) {
            int []res = new int[num + 1];
            for (int i = 0; i <= num; i++) {
                res[i] = zeroCount(i);
            }
            return res;
        }
        private int zeroCount(int num) {
            int count = 0; 
            while (num != 0) {
                num = num & (num - 1);
                count++;
            }
            return count;
        }
    }

```

## 最高有效位+动态规划
```
    
    // ans[i + b] = ans[i] + 1;
    class Solution {
        public int[] countBits(int num) {
            int []ans = new int[num + 1];
            int i = 0;
            int b = 1;
            while (b <= num) {
                while (i < b && i + b <= num) {
                    ans[i + b] = ans[i] + 1;
                    i++;
                }
                i = 0;
                b <<= 1;
            }
            return ans;
        }
    }
```

## 最低有效位+动态规划
```
    // 动态规划 dp[x] = dp[x & (x-1)] + 1
    class Solution {
        public int[] countBits(int num) {
            int []dp = new int[num + 1];
            for (int i = 1; i <= num; i++) {
                dp[i] = dp[i & (i - 1)] + 1;
            }
            return dp;
        }
    }

    或者
    // dp[x] = dp[x / 2] + x % 2
    class Solution {
        public int[] countBits(int num) {
            int []dp = new int[num + 1];
            for (int i = 1; i <= num; i++) {
                dp[i] = dp[i >> 1] + (i & 1);
            }
            return dp;
        }
    }
```


# 146. LRU缓存机制难度中等

运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。

## 解法1 使用已有数据结构LinkedList与HashMap
```

    class LRUCache {
        class CacheNode {
            int key;
            int val;
            CacheNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
        int size = 0; 
        int capacity = 0;
        LinkedList<CacheNode> list = new LinkedList<>();
        Map<Integer, CacheNode> m = new HashMap<>();

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            if (!m.containsKey(key)) {
                return -1;
            }
            CacheNode node = m.get(key);
            list.remove(node);
            list.addFirst(node);
            return node.val;
        }

        public void put(int key, int value) {
            CacheNode node = m.getOrDefault(key, null);
            if (node != null) {
                node.val = value;
                list.remove(node);
                list.addFirst(node);
            } else {
                node = new CacheNode(key, value);
                list.addFirst(node);
                m.put(key, node);
                size++;
                if (size > capacity) {
                    node = list.removeLast();
                    m.remove(node.key);
                }
            }
        }
    }

```

## 解法2 自己实现双向链表
```
    
    class LRUCache {
        class CacheNode {
            int key;
            int val;
            CacheNode pre = null;
            CacheNode next = null;
            CacheNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
        class DoubleLinked {
            CacheNode head = null;
            CacheNode tail = null;
            public DoubleLinked() {
                head = new CacheNode(-1, -1);
                tail = new CacheNode(-1, -1);
                head.next = tail;
                head.pre = tail;
                tail.next = head;
                tail.pre = head;
            }
        }
        int size = 0; 
        int capacity = 0;
        DoubleLinked list = new DoubleLinked();
        Map<Integer, CacheNode> m = new HashMap<>();

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            if (!m.containsKey(key)) {
                return -1;
            }
            CacheNode node = m.get(key);
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.next = list.head.next;
            list.head.next.pre = node;
            list.head.next = node;
            node.pre = list.head;
            return node.val;
        }

        public void put(int key, int value) {
            CacheNode node = m.getOrDefault(key, null);
            if (node == null) {
                node = new CacheNode(key, value);
                node.next = list.head.next;
                list.head.next.pre = node;
                list.head.next = node;
                node.pre = list.head;
                m.put(key, node);
                size++;
                if (size > capacity) {
                    node = list.tail.pre;
                    node.pre.next = list.tail;
                    list.tail.pre = node.pre;
                    m.remove(node.key);
                }
            } else {
                node.pre.next = node.next;
                node.next.pre = node.pre;
                node.val = value;
                node.next = list.head.next;
                list.head.next.pre = node;
                list.head.next = node;
                node.pre = list.head;
            }
        }
    }

```

# 排序算法概览

![566270af7c1ab0f62949e0be16c819ad.png](en-resource://database/5450:1)


## 时间复杂度
![579698a5b1da2d90df9d4882f9604457.png](en-resource://database/5452:1)


# 排序算法
# 冒泡排序
```

    public class BubbleSort {
        public int[] bubbleSort(int []arr) {
            int n = arr.length;
            boolean flag;
            for (int i = 0; i < n - 1; i++) {
                flag = false;
                for (int j = 1; j < n - i; j++) {
                    if (arr[j - 1] > arr[j]) {
                        int tmp = arr[j - 1];
                        arr[j - 1] = arr[j];
                        arr[j] = tmp;
                        flag = true;
                    }
                }
                if (!flag) {
                    break;
                }
            }
            return arr;
        }
    }
```

## 选择排序
```
    
    public class SelectionSort {
        public int[] selectionSort(int []arr) {
            int n = arr.length;
            int minIndex;
            for (int i = 0; i < n - 1; i++) {
                minIndex = i;
                for (int j = i + 1; j < n; j++) {
                    if (arr[minIndex] > arr[j]) {
                        minIndex = j;
                    }
                }
                if (minIndex != i) {
                    int tmp = arr[i];
                    arr[i] = arr[minIndex];
                    arr[minIndex] = tmp;
                }
            }
            return arr;
        }


        public static void main(String[] args) {
            int []arr = {30, 60, 10, 40, 90, 50, 20, 80, 70, 100};
            SelectionSort sort = new SelectionSort();
            sort.selectionSort(arr);
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
        }
    }
```

# 插入排序
```
    
    public class InsertionSort {
        public int[] insertionSort(int []arr) {
            int n = arr.length;
            for (int i = 0; i < n; i++) {
                int tmp = arr[i];
                int j = i - 1;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = tmp;
            }
            return arr;
        }

        public static void main(String[] args) {
            int []arr = {30, 60, 10, 40, 90, 50, 20, 80, 70, 100};
            InsertionSort sort = new InsertionSort();
            sort.insertionSort(arr);
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
        }
    }
```

## 希尔排序（插入排序高级版）
```
    
    public class ShellSort {
        public int[] shellSort(int []arr) {
            int n = arr.length;
            int grap = n / 2;
            for (; grap > 0; grap = grap / 2) {
                for (int i = grap; i < n; i++) {
                    int j = i;
                    int cur = arr[i];
                    while (j - grap >= 0 && arr[j - grap] > arr[j]) {
                        arr[j] = arr[j - grap];
                        j -= grap;
                    }
                    arr[j] = cur;
                }
            }
            return arr;
        }

        public static void main(String[] args) {
            int []arr = {30, 60, 10, 40, 90, 50, 20, 80, 70, 100};
            ShellSort sort = new ShellSort();
            sort.shellSort(arr);
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
        }
    }
```
## 归并排序
```
    
    public class MergeSort {
        public void mergeSort(int []arr, int left, int right) {
            if (left >= right) {
                return;
            }
            int mid = (left + right) >> 1;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
        public void merge(int []arr, int left, int mid, int right) {
            int[] tmp = new int[right - left + 1];
            int i = left, j = mid + 1, k = 0;
            while (i <= mid && j <= right) {
                tmp[k++] = arr[i] <= arr[j]? arr[i++] : arr[j++];
            }
            while (i <= mid) {
                tmp[k++] = arr[i++];
            }
            while (j <= right) {
                tmp[k++] = arr[j++];
            }
            int p = 0;
            for (i = left; i <= right; i++) {
                arr[i] = tmp[p++];
            }
        }



        public static void main(String[] args) {
            int []arr = {30, 60, 10, 40, 90, 50, 20, 80, 70, 100};
            MergeSort sort = new MergeSort();
            sort.mergeSort(arr, 0, arr.length - 1);
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
        }
    }
```

## 快速排序
```
    
    public class QuickSort {
        public void quickSort(int []arr, int begin, int end) {
            if (begin >= end) {
                return;
            }
            int pivot = partition(arr, begin, end);
            quickSort(arr, begin, pivot - 1);
            quickSort(arr, pivot + 1, end);
        }
        public int partition(int []arr, int begin, int end) {
            int count = begin;
            int v = arr[end];
            for (int i = begin; i <= end; i++) {
                if (arr[i] < v) {
                    int tmp = arr[count];
                    arr[count] = arr[i];
                    arr[i] = tmp;
                    count++;
                }
            }
            int tmp = arr[count];
            arr[count] = v;
            arr[end] = tmp;
            return count;
        }

        public static void main(String[] args) {
            int []arr = {30, 60, 10, 40, 90, 50, 20, 80, 70, 100};
            QuickSort sort = new QuickSort();
            sort.quickSort(arr, 0, arr.length - 1);
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
        }
    }
```

## 堆排序
```

    public class HeapSort {
        static void heapify(int[] array, int length, int i) {
            int left = 2 * i + 1, right = 2 * i + 2;
            int largest = i;

            if (left < length && array[left] > array[largest]) {
                largest = left;
            }
            if (right < length && array[right] > array[largest]) {
                largest = right;
            }

            if (largest != i) {
                int temp = array[i]; array[i] = array[largest]; array[largest] = temp;
                heapify(array, length, largest);
            }
        }

        public void heapSort(int[] array) {
            if (array.length == 0) return;

            int length = array.length;
            for (int i = length / 2-1; i >= 0; i--) {
                heapify(array, length, i);
            }

            for (int i = length - 1; i >= 0; i--) {
                int temp = array[0]; array[0] = array[i]; array[i] = temp;
                heapify(array, i, 0);
            }
        }

        public static void main(String[] args) {
            int []arr = {30, 60, 10, 40, 90, 50, 20, 80, 70, 100};
            HeapSort sort = new HeapSort();
            sort.heapSort(arr);
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
        }
    }
```