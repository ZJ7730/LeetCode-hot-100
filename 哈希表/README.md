# 哈希表专题面试复习

## 1. 题目总览

| 文件 | 题号 | 题目 | 核心方法 | 模板类型 | 复杂度 |
| --- | --- | --- | --- | --- | --- |
| `H_1.java` | 1 | 两数之和 | `HashMap` 存已遍历数字到下标，查找补数 | 哈希查找 | 时间 `O(n)`，空间 `O(n)` |
| `H_49.java` | 49 | 字母异位词分组 | 排序后的字符串作为 key，把异位词放入同一组 | 哈希分组 | 时间 `O(n * klogk)`，空间 `O(nk)` |
| `H_128.java` | 128 | 最长连续序列 | `HashSet` 去重，只从序列起点向后扩展 | 哈希集合 | 时间 `O(n)`，空间 `O(n)` |
| `H_347.java` | 347 | 前 K 个高频元素 | `HashMap` 统计频率，大小为 `k` 的小顶堆保留高频元素 | 哈希计数 + Top K | 时间 `O(n log k)`，空间 `O(n)` |
| `H_438.java` | 438 | 找到字符串中所有字母异位词 | 固定长度滑动窗口维护字符频次 | 滑动窗口 + 计数 | 时间 `O(n)`，空间 `O(1)` |
| `H_560.java` | 560 | 和为 K 的子数组 | `HashMap` 统计历史前缀和出现次数 | 前缀和 + 哈希 | 时间 `O(n)`，空间 `O(n)` |
| `H_15.java` | 15 | 三数之和 | 排序后固定一个数，再用左右指针找另外两个数 | 排序 + 双指针去重 | 时间 `O(n^2)`，空间 `O(1)` 不计结果 |

说明：`H_15.java` 当前放在哈希表目录下，但代码核心不是哈希表，而是排序 + 双指针 + 去重。面试复习时建议把它同时归到“双指针 / 数组去重”模板里理解。

## 2. 本专题核心思想

- 哈希表适合快速判断“某个值是否出现过”或“某个值对应的位置”。
- 两数之和的关键是查 `target - nums[i]` 是否已经出现。
- 字母异位词分组的关键是设计统一 key，排序后的字符串可以作为同一组异位词的标准形式。
- 找到字符串中所有字母异位词的关键是固定窗口长度，用字符频次数组判断窗口和目标是否一致。
- 最长连续序列的关键是只从 `num - 1` 不存在的起点开始扩展。
- 前 K 个高频元素先统计频率，再用堆维护频率最高的 k 个元素。
- 和为 K 的子数组的关键是把“子数组和”转换成两个前缀和的差。
- 涉及多个数之和时，要根据题目要求选择哈希、排序双指针或回溯。
- 三数之和的难点不是查找，而是去重。
- 面试中要明确 Map 的 key 和 value 分别表示什么。

## 5. 每题笔记

### H_1 - 两数之和

- 题目定位：在数组中找到两个数，使它们之和等于 `target`，并返回下标。
- 当前代码思路：遍历当前元素时，先查补数是否已经出现，再把当前数字记录到 map。
- 关键变量：`map` 的 key 是数字本身，value 是这个数字的下标。
- 解题步骤：计算补数；如果补数在 map 中，直接返回；否则把当前数字和下标存入 map。
- 复杂度：时间 `O(n)`，空间 `O(n)`。
- 面试易错点：必须先查再放，避免同一个元素被重复使用。
- 可复述话术：我用哈希表记录已遍历数字和位置，当前数字只需要看它的补数是否已经出现过。

## 3. 通用解题模板

### HashMap 查补数模板

对应 `H_1.java`：

```java
Map<Integer, Integer> map = new HashMap<>();

for (int i = 0; i < nums.length; i++) {
    int key = target - nums[i];
    if (map.containsKey(key)) {
        return new int[]{map.get(key), i};
    } else {
        map.put(nums[i], i);
    }
}
return null;
```

核心点：先查再放，避免同一个元素被使用两次。

### HashMap 分组模板

对应 `H_49.java`：

```java
Map<String, List<String>> res = new HashMap<>();

for (String str : strs) {
    char[] charArray = str.toCharArray();
    Arrays.sort(charArray);
    String sortedStr = new String(charArray);

    res.computeIfAbsent(sortedStr, k -> new ArrayList<>()).add(str);
}
return new ArrayList<>(res.values());
```

核心点：异位词排序后结果相同，所以可以用排序后的字符串作为分组 key。

### 滑动窗口 + 字符计数模板

对应 `H_438.java`：

```java
int[] targetCount = new int[26];
int[] windowCount = new int[26];

for (char e : p.toCharArray()) {
    targetCount[e - 'a']++;
}

int windowSize = p.length();
int left = 0;

for (int right = 0; right < s.length(); right++) {
    char charred = s.charAt(right);
    windowCount[charred - 'a']++;

    if (right - left + 1 > windowSize) {
        char leftChar = s.charAt(left);
        windowCount[leftChar - 'a']--;
        left++;
    }

    if (right - left + 1 == windowSize && Arrays.equals(targetCount, windowCount)) {
        res.add(left);
    }
}
```

核心点：窗口长度固定为 `p.length()`，每次只比较当前窗口的字符频次是否和目标一致。

### HashSet 连续序列起点模板

对应 `H_128.java`：

```java
Set<Integer> set = new HashSet<>();
for (int num : nums) {
    set.add(num);
}

int result = 0;
for (int num : set) {
    if (!set.contains(num - 1)) {
        int current = num;
        int length = 1;
        while (set.contains(current + 1)) {
            current++;
            length++;
        }
        result = Math.max(result, length);
    }
}
return result;
```

核心点：只有 `num - 1` 不存在时才开始扩展，保证每段连续序列只被扫描一次。

### 前缀和 + HashMap 计数模板

对应 `H_560.java`：

```java
Map<Integer, Integer> preSumCount = new HashMap<>();
preSumCount.put(0, 1);

int result = 0;
int preSum = 0;
for (int num : nums) {
    preSum += num;
    int need = preSum - k;
    result += preSumCount.getOrDefault(need, 0);
    preSumCount.put(preSum, preSumCount.getOrDefault(preSum, 0) + 1);
}
return result;
```

核心点：如果 `preSum - oldSum = k`，那么 `oldSum` 后面到当前位置的子数组和就是 `k`。

### HashMap 计数 + 小顶堆模板

对应 `H_347.java`：

```java
Map<Integer, Integer> countMap = new HashMap<>();
for (int num : nums) {
    countMap.put(num, countMap.getOrDefault(num, 0) + 1);
}

PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
    minHeap.offer(new int[]{entry.getKey(), entry.getValue()});
    if (minHeap.size() > k) {
        minHeap.poll();
    }
}
```

核心点：堆按频率排序，只保留频率最高的 `k` 个元素。

### 排序 + 双指针去重模板

对应 `H_15.java`：

```java
Arrays.sort(nums);

for (int i = 0; i < nums.length; i++) {
    if (nums[i] > 0) {
        return res;
    }
    if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
    }

    int left = i + 1;
    int right = nums.length - 1;
    while (left < right) {
        int sum = nums[i] + nums[left] + nums[right];
        if (sum > 0) {
            right--;
        } else if (sum < 0) {
            left++;
        } else {
            res.add(List.of(nums[i], nums[left], nums[right]));
            while (left < right && nums[right] == nums[right - 1]) {
                right--;
            }
            while (left < right && nums[left] == nums[left + 1]) {
                left++;
            }
            left++;
            right--;
        }
    }
}
```

## 4. 题目分类

- 哈希查找：`H_1.java`
- 哈希分组：`H_49.java`
- 哈希集合去重：`H_128.java`
- 哈希计数 + Top K：`H_347.java`
- 滑动窗口 + 字符计数：`H_438.java`
- 前缀和 + 哈希计数：`H_560.java`
- 排序 + 双指针去重：`H_15.java`

## 5. 每题详解

### H_1 - 两数之和

- 题目定位：找数组中两个数，使它们之和等于 `target`，返回下标。
- 当前代码思路：遍历数组，对当前数字 `nums[i]`，先查补数 `target - nums[i]` 是否已经在 `map` 中。
- 关键变量：`map` 的 key 是已遍历的数字，value 是该数字的下标；`key` 表示当前数字需要的补数。
- 解题步骤：创建 HashMap；遍历数组；计算补数；如果补数存在则返回两个下标；否则把当前数字和下标放入 map。
- 复杂度：时间 `O(n)`，空间 `O(n)`。
- 面试易错点：必须先查再放，避免 `target = 2 * nums[i]` 时把同一个元素用两次。
- 可复述话术：我用哈希表记录已经看过的数字和下标，遍历到当前数字时只需要看它的补数是否出现过。

### H_15 - 三数之和

- 题目定位：找所有不重复三元组，使三数之和为 0。
- 当前代码思路：先排序；固定 `i`；用 `left/right` 在右侧区间找两数之和等于 `-nums[i]`；命中后跳过重复值。
- 关键变量：`res` 保存答案；`left = i + 1`，`right = nums.length - 1`；`nums[i] > 0` 时可提前结束。
- 解题步骤：排序；枚举固定数；跳过重复固定数；左右指针求和；根据和大小移动指针；找到答案后左右都去重。
- 复杂度：时间 `O(n^2)`，空间 `O(1)` 不计答案。
- 面试易错点：外层 `i` 要去重，找到答案后 `left/right` 也都要去重。
- 可复述话术：排序后固定一个数，剩下两数用双指针找目标和；因为排序让重复值相邻，所以可以在固定数和左右指针处分别去重。

### H_49 - 字母异位词分组

- 题目定位：把由相同字母组成、但排列顺序不同的字符串分到同一组。
- 当前代码思路：对每个字符串排序，排序后的结果作为哈希表 key，原字符串加入对应分组。
- 关键变量：`res` 的 key 是排序后的标准字符串，value 是属于这一类异位词的原字符串列表。
- 解题步骤：遍历字符串数组；转字符数组并排序；转回字符串作为 key；用 `computeIfAbsent` 初始化分组并加入原字符串。
- 复杂度：设字符串个数为 `n`，单个字符串最大长度为 `k`，时间 `O(n * klogk)`，空间 `O(nk)`。
- 面试易错点：分组里保存的是原字符串 `str`，不是排序后的 `sortedStr`。
- 可复述话术：异位词的字符组成相同，排序后会变成同一个标准形式，所以我用这个标准形式做 key 来分组。

### H_128 - 最长连续序列

- 题目定位：在无序数组中找最长的连续整数序列长度。
- 当前代码思路：先把所有数字放入 `HashSet`，然后只从“序列起点”开始往后扩展。
- 关键变量：`numSet` 用于快速判断数字是否存在；`current` 记录当前扩展到的位置；`result` 记录最长长度。
- 解题步骤：先去重并建立集合；遍历集合；如果 `num - 1` 不存在，说明 `num` 是起点；从起点向后连续扩展。
- 复杂度：时间 `O(n)`，空间 `O(n)`。
- 面试易错点：不能每个数字都向后扩展，否则会退化成 `O(n^2)`。
- 可复述话术：只从连续段的起点开始数，这样每一段连续序列只会被扫描一次。

### H_438 - 找到字符串中所有字母异位词

- 题目定位：在 `s` 中找到所有和 `p` 字符组成相同的连续子串起始下标。
- 当前代码思路：用两个长度为 26 的数组分别记录 `p` 的字符频次和当前滑动窗口的字符频次。
- 关键变量：`targetCount` 表示目标字符串频次；`windowCount` 表示当前窗口频次；`left/right` 维护窗口边界。
- 解题步骤：先统计 `p` 的字符频次；右指针遍历 `s` 并加入窗口；窗口超过 `p.length()` 时移除左侧字符；窗口长度相等时比较两个计数数组。
- 复杂度：时间 `O(26 * n)`，可视为 `O(n)`；空间 `O(1)`。
- 面试易错点：只有窗口长度等于 `p.length()` 时才判断；窗口过长时要先移除左侧字符，保证比较的是固定长度窗口。
- 可复述话术：异位词只看字符频次，所以我用固定长度窗口扫描 s，每个窗口和 p 的频次数组一致时，就记录左边界。

### H_347 - 前 K 个高频元素

- 题目定位：返回数组中出现频率最高的 `k` 个元素，不要求结果顺序。
- 当前代码思路：先统计频率，再用大小为 `k` 的小顶堆保留当前最重要的 `k` 个元素。
- 关键变量：`countMap` 保存数字到频率的映射；`minHeap` 存 `[数字, 频率]`。
- 解题步骤：统计频率；遍历频率表入堆；堆大小超出 `k` 时弹出频率最低的元素；最后取堆中剩余元素。
- 复杂度：时间 `O(n log k)`，空间 `O(n)`。
- 面试易错点：堆比较的是频率，不是数字大小；结果题目不要求排序时，不必额外整理顺序。
- 可复述话术：我先把问题变成频率统计，再用小顶堆只保留前 k 个高频元素，避免对所有元素做完整排序。

### H_560 - 和为 K 的子数组

- 题目定位：统计数组中和为 `k` 的连续子数组个数。
- 当前代码思路：用前缀和表示从起点到当前位置的总和，再用哈希表统计历史前缀和出现次数。
- 关键变量：`preSum` 是当前前缀和；`preSumCount` 记录某个前缀和出现了几次；`need = preSum - k`。
- 解题步骤：初始化 `preSumCount.put(0, 1)`；遍历数组更新前缀和；查找历史上有多少个 `preSum - k`；再记录当前前缀和。
- 复杂度：时间 `O(n)`，空间 `O(n)`。
- 面试易错点：数组里可能有负数，不能用普通滑动窗口；要先统计答案，再更新当前前缀和。
- 可复述话术：任意子数组和都能拆成两个前缀和的差，所以我只要知道历史前缀和出现过多少次，就能统计出和为 k 的子数组数量。

-### H_128 - 最长连续序列
-
-- 题目定位：在无序数组中找最长的连续整数序列长度，要求时间复杂度为 `O(n)`。
-- 当前代码思路：先用 `HashSet` 存所有数字；遍历集合时，只从 `num - 1` 不存在的数字开始向后扩展。
-- 关键变量：`numSet` 存所有数字；`current` 表示当前扩展到的数字；`length` 表示当前连续序列长度；`result` 记录最大长度。
-- 解题步骤：把数组加入 set；遍历 set；判断当前数是否是序列起点；从起点向后查 `current + 1`；更新最大长度。
-- 复杂度：时间 `O(n)`，空间 `O(n)`。
-- 面试易错点：不能每个数字都向后扩展，否则遇到 `1,2,3,4,...,n` 会退化成 `O(n^2)`。
-- 可复述话术：我先用哈希集合支持快速查询，然后只从连续序列的起点开始扩展，这样每个连续段只扫描一次。
-
-### H_347 - 前 K 个高频元素
-
-- 题目定位：返回数组中出现频率最高的 `k` 个元素，不要求结果顺序。
-- 当前代码思路：先用 `HashMap` 统计每个数字出现次数，再用大小为 `k` 的小顶堆保存当前频率最高的 `k` 个数字。
-- 关键变量：`countMap` 的 key 是数字，value 是出现次数；`minHeap` 中每个元素是 `[数字, 频率]`，堆顶是当前保留元素里频率最低的。
-- 解题步骤：统计频率；遍历频率表入堆；堆大小超过 `k` 时弹出低频元素；最后从堆中取出答案。
-- 复杂度：时间 `O(n log k)`，空间 `O(n)`。
-- 面试易错点：堆比较器要按频率比较，不是按数字大小比较；结果不要求排序，所以直接弹出即可。
-- 可复述话术：我先把问题转成频率统计，再用小顶堆只保留频率最高的 k 个元素，避免对所有不同元素按频率完整排序。
-
-### H_560 - 和为 K 的子数组
-
-- 题目定位：统计数组中和为 `k` 的连续子数组个数。
-- 当前代码思路：维护当前前缀和 `preSum`，用 `HashMap` 统计历史前缀和出现次数；如果历史上出现过 `preSum - k`，说明存在对应子数组。
-- 关键变量：`preSumCount` 的 key 是前缀和，value 是该前缀和出现次数；`preSum` 是当前前缀和；`need = preSum - k`。
-- 解题步骤：初始化 `preSumCount.put(0, 1)`；遍历数组更新前缀和；查 `preSum - k` 的出现次数并加入答案；再记录当前前缀和。
-- 复杂度：时间 `O(n)`，空间 `O(n)`。
-- 面试易错点：数组可能有负数，不能用滑动窗口；必须先统计答案，再把当前前缀和放入 map，避免边界含义混乱。
-- 可复述话术：任意子数组和都可以表示成两个前缀和的差，所以我用哈希表统计历史前缀和出现次数，遍历时查当前前缀和差多少能得到 k。

## 6. 面试高频易错点

- HashMap 的 value 如果要返回下标，就必须存下标而不是只存次数。
- 两数之和要先查补数再放当前数。
- 最长连续序列要只从起点扩展，起点判断是 `!set.contains(num - 1)`。
- 前 K 个高频元素的小顶堆比较的是频率，不是元素值。
- `347` 的结果顺序不重要，除非面试官额外要求排序。
- 前缀和计数题要初始化 `map.put(0, 1)`，否则漏掉从下标 0 开始的合法子数组。
- 和为 K 的子数组存在负数，不能用普通滑动窗口。
- 三数之和排序后如果 `nums[i] > 0`，后面都非负，可以直接结束。
- 三数之和不是找到一个答案就结束，要继续移动左右指针找完整结果集。
- 三数之和的去重有三处：固定数去重、左指针去重、右指针去重。
- 字母异位词分组要保存原字符串，排序后的字符串只用于当 key。
- 找到字符串中所有字母异位词要用固定长度窗口，不能在长度不等时比较频次。

## 7. 推荐刷题顺序

1. `H_1.java`：掌握 HashMap 查补数。
2. `H_49.java`：掌握 HashMap 分组和 key 设计。
3. `H_438.java`：掌握固定长度滑动窗口 + 字符计数。
4. `H_128.java`：掌握 HashSet 去重和只从起点扩展的优化。
5. `H_347.java`：掌握哈希计数 + Top K 小顶堆。
6. `H_560.java`：掌握前缀和 + HashMap 计数。
7. `H_15.java`：掌握排序 + 双指针 + 去重，并理解它和两数之和的区别。

## 8. 当前完成状态

| 统计 | 数量 |
| --- | ---: |
| 已实现 | 6 |
| 待补 | 0 |
| 总计 | 6 |

| 状态 | 题目 |
| --- | --- |
| 已实现 | `H_1.java` 两数之和 |
| 已实现 | `H_49.java` 49. 字母异位词分组 |
| 已实现 | `H_128.java` 128. 最长连续序列 |
| 已实现 | `H_347.java` 347. 前 K 个高频元素 |
| 已实现 | `H_438.java` 438. 找到字符串中所有字母异位词 |
| 已实现 | `H_560.java` 560. 和为 K 的子数组 |

## 9. 面试高频题

| 优先级 | 题目 | 高频原因 |
| ---: | --- | --- |
| 1 | `1. 两数之和` | 哈希查找入门必会 |
| 2 | `49. 字母异位词分组` | 哈希分组和 key 设计高频 |
| 3 | `128. 最长连续序列` | `HashSet` 去重和起点扩展，HOT 100 高频 |
| 4 | `560. 和为 K 的子数组` | 前缀和 + 哈希计数高频 |
| 5 | `347. 前 K 个高频元素` | 哈希计数 + Top K 高频 |
| 6 | `438. 找到字符串中所有字母异位词` | 滑动窗口 + 计数高频 |
