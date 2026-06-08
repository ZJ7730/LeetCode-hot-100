# 哈希表专题面试复习

## 1. 题目总览

| 文件 | 题号 | 题目 | 核心方法 | 模板类型 | 复杂度 |
| --- | --- | --- | --- | --- | --- |
| `H_1.java` | 1 | 两数之和 | `HashMap` 存已遍历数字到下标，查找补数 | 哈希查找 | 时间 `O(n)`，空间 `O(n)` |
| `H_128.java` | 128 | 最长连续序列 | `HashSet` 去重，只从序列起点向后扩展 | 哈希集合 | 时间 `O(n)`，空间 `O(n)` |
| `H_560.java` | 560 | 和为 K 的子数组 | `HashMap` 统计历史前缀和出现次数 | 前缀和 + 哈希 | 时间 `O(n)`，空间 `O(n)` |
| `H_15.java` | 15 | 三数之和 | 排序后固定一个数，再用左右指针找另外两个数 | 排序 + 双指针去重 | 时间 `O(n^2)`，空间 `O(1)` 不计结果 |

说明：`H_15.java` 当前放在哈希表目录下，但代码核心不是哈希表，而是排序 + 双指针 + 去重。面试复习时建议把它同时归到“双指针 / 数组去重”模板里理解。

## 2. 本专题核心思想

- 哈希表适合快速判断“某个值是否出现过”或“某个值对应的位置”。
- 两数之和的关键是查 `target - nums[i]` 是否已经出现。
- 最长连续序列的关键是只从 `num - 1` 不存在的起点开始扩展。
- 和为 K 的子数组的关键是把“子数组和”转换成两个前缀和的差。
- 涉及多个数之和时，要根据题目要求选择哈希、排序双指针或回溯。
- 三数之和的难点不是查找，而是去重。
- 面试中要明确 Map 的 key 和 value 分别表示什么。

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
- 哈希集合去重：`H_128.java`
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

### H_128 - 最长连续序列

- 题目定位：在无序数组中找最长的连续整数序列长度，要求时间复杂度为 `O(n)`。
- 当前代码思路：先用 `HashSet` 存所有数字；遍历集合时，只从 `num - 1` 不存在的数字开始向后扩展。
- 关键变量：`numSet` 存所有数字；`current` 表示当前扩展到的数字；`length` 表示当前连续序列长度；`result` 记录最大长度。
- 解题步骤：把数组加入 set；遍历 set；判断当前数是否是序列起点；从起点向后查 `current + 1`；更新最大长度。
- 复杂度：时间 `O(n)`，空间 `O(n)`。
- 面试易错点：不能每个数字都向后扩展，否则遇到 `1,2,3,4,...,n` 会退化成 `O(n^2)`。
- 可复述话术：我先用哈希集合支持快速查询，然后只从连续序列的起点开始扩展，这样每个连续段只扫描一次。

### H_560 - 和为 K 的子数组

- 题目定位：统计数组中和为 `k` 的连续子数组个数。
- 当前代码思路：维护当前前缀和 `preSum`，用 `HashMap` 统计历史前缀和出现次数；如果历史上出现过 `preSum - k`，说明存在对应子数组。
- 关键变量：`preSumCount` 的 key 是前缀和，value 是该前缀和出现次数；`preSum` 是当前前缀和；`need = preSum - k`。
- 解题步骤：初始化 `preSumCount.put(0, 1)`；遍历数组更新前缀和；查 `preSum - k` 的出现次数并加入答案；再记录当前前缀和。
- 复杂度：时间 `O(n)`，空间 `O(n)`。
- 面试易错点：数组可能有负数，不能用滑动窗口；必须先统计答案，再把当前前缀和放入 map，避免边界含义混乱。
- 可复述话术：任意子数组和都可以表示成两个前缀和的差，所以我用哈希表统计历史前缀和出现次数，遍历时查当前前缀和差多少能得到 k。

## 6. 面试高频易错点

- HashMap 的 value 如果要返回下标，就必须存下标而不是只存次数。
- 两数之和要先查补数再放当前数。
- 最长连续序列要只从起点扩展，起点判断是 `!set.contains(num - 1)`。
- 前缀和计数题要初始化 `map.put(0, 1)`，否则漏掉从下标 0 开始的合法子数组。
- 和为 K 的子数组存在负数，不能用普通滑动窗口。
- 三数之和排序后如果 `nums[i] > 0`，后面都非负，可以直接结束。
- 三数之和不是找到一个答案就结束，要继续移动左右指针找完整结果集。
- 三数之和的去重有三处：固定数去重、左指针去重、右指针去重。

## 7. 推荐刷题顺序

1. `H_1.java`：掌握 HashMap 查补数。
2. `H_128.java`：掌握 HashSet 去重和只从起点扩展的优化。
3. `H_560.java`：掌握前缀和 + HashMap 计数。
4. `H_15.java`：掌握排序 + 双指针 + 去重，并理解它和两数之和的区别。
