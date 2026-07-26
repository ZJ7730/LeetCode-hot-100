# 回溯和 DFS 题解复盘

> 标记说明：`【Hot 100】` 表示该题属于本仓库的 LeetCode Hot 100 题单。

## 索引

- [H_17 电话号码的字母组合【Hot 100】](#h-17)
- [H_22 括号生成【Hot 100】](#h-22)
- [H_39 组合总和【Hot 100】](#h-39)
- [H_40 组合总和 II](#h-40)
- [H_46 全排列【Hot 100】](#h-46)
- [H_47 全排列 II](#h-47)
- [H_51 N 皇后【Hot 100】](#h-51)
- [H_77 组合与剪枝](#h-77)
- [H_78 子集【Hot 100】](#h-78)
- [H_79 单词搜索【Hot 100】](#h-79)
- [H_131 分割回文串【Hot 100】](#h-131)
- [H_216 组合总和 III](#h-216)
- [H_301 删除无效的括号](#h-301)
- [H_491 递增子序列](#h-491)

## 回溯通用框架

回溯固定做三件事：

```text
做选择 -> 递归下一层 -> 撤销选择
```

```java
void backtrack(参数) {
    if (终止条件) {
        result.add(new ArrayList<>(path));
        return;
    }

    for (选择 : 当前层可选范围) {
        path.add(选择);
        backtrack(下一层参数);
        path.remove(path.size() - 1);
    }
}
```

面试先说清四件事：

```text
递归函数表示什么？
当前层能选择什么？
何时收集答案？
递归返回时需要恢复哪些状态？
```

<a id="h-17"></a>
## H_17 - 电话号码的字母组合【Hot 100】

### 核心思路

按数字下标递归。每层只处理当前数字，并枚举该数字映射的全部字母；`index` 到达字符串末尾时得到一条完整组合。

### 面试速记

```text
按位构造字符串：第 index 层枚举当前数字对应字母，选字母后递归 index + 1，走到末尾收集。
```

### 当前题解

```java
Map<Character, String> phoneMap = Map.of(
        '0', "",
        '1', "",
        '2', "abc",
        '3', "def",
        '4', "ghi",
        '5', "jkl",
        '6', "mno",
        '7', "pqrs",
        '8', "tuv",
        '9', "wxyz"
);
List<String> result = new ArrayList<>();
List<String> path = new ArrayList<>();

public List<String> letterCombinations(String digits) {
    backtracking(digits, 0);
    return result;
}

public void backtracking(String digits, int index) {
    if (index == digits.length()) {
        result.add(String.join("", path));
        return;
    }

    char[] charArray = digits.toCharArray();
    char num = charArray[index];
    String letters = phoneMap.get(num);

    for (int i = 0; i < letters.length(); i++) {
        path.add(String.valueOf(letters.charAt(i)));
        backtracking(digits, index + 1);
        path.removeLast();
    }
}
```

### 关键状态

```text
index：当前处理第几个数字
path：当前已选择的字母
phoneMap：数字到字母的映射
```

### 易错点

- 空字符串应按题意直接返回空集合；当前实现会收集一个空字符串，面试时需主动说明处理方式。
- `path` 是可变状态，收集结果时要转成新字符串。

### 复杂度

- 时间复杂度：`O(3^m * 4^n)`，`m/n` 分别是映射 3 个或 4 个字母的数字数量。
- 空间复杂度：`O(digits.length())`，不含结果。

---

<a id="h-22"></a>
## H_22 - 括号生成【Hot 100】

### 核心思路

递归构造括号字符串，并在构造过程中保证任意前缀合法：左括号未用完可以放 `(`；只有右括号数量小于左括号数量时才能放 `)`。

### 面试速记

```text
约束回溯：left < n 时放左括号，right < left 时放右括号，长度到 2 * n 时收集。
```

### 当前题解

```java
List<String> result = new ArrayList<>();
List<String> path = new ArrayList<>();

public List<String> generateParenthesis(int n) {
    dfs(0, 0, n);
    return result;
}

public void dfs(int left, int right, int n) {
    if (path.size() == 2 * n) {
        result.add(String.join("", path.toArray(new String[0])));
        return;
    }

    if (left < n) {
        path.add("(");
        dfs(left + 1, right, n);
        path.removeLast();
    }
    if (right < left) {
        path.add(")");
        dfs(left, right + 1, n);
        path.removeLast();
    }
}
```

### 关键状态

```text
left/right：已放入的左右括号数量
path：当前括号序列
```

### 易错点

- 右括号绝不能先于左括号，即始终满足 `right <= left`。
- `left == n` 只表示不能再放左括号，不代表递归结束；仍可能需要补右括号。

### 复杂度

- 时间复杂度：`O(Cn)`，`Cn` 为第 `n` 个卡特兰数，输出每个字符串还需 `O(n)`。
- 空间复杂度：`O(n)`，不含结果。

---

<a id="h-39"></a>
## H_39 - 组合总和【Hot 100】

### 核心思路

组合问题用 `startIndex` 避免顺序重复；题目允许同一个候选数重复使用，因此选择 `candidates[i]` 后，下一层仍从 `i` 开始。

### 面试速记

```text
可重复组合：startIndex 防重复，递归传 i 允许当前数继续选；sum > target 立即剪枝。
```

### 当前题解

```java
List<List<Integer>> result = new ArrayList<>();
List<Integer> path = new ArrayList<>();

public List<List<Integer>> combinationSum(int[] candidates, int target) {
    backtracking(candidates, target, 0, 0);
    return result;
}

public void backtracking(int[] candidates, int target, int sum, int startIndex) {
    if (sum > target) {
        return;
    }
    if (sum == target) {
        result.add(new ArrayList<>(path));
        return;
    }

    for (int i = startIndex; i < candidates.length; i++) {
        path.add(candidates[i]);
        sum += candidates[i];
        backtracking(candidates, target, sum, i);
        sum -= candidates[i];
        path.removeLast();
    }
}
```

### 关键代码

```java
for (int i = startIndex; i < candidates.length; i++) {
    path.add(candidates[i]);
    sum += candidates[i];
    backtracking(candidates, target, sum, i);
    sum -= candidates[i];
    path.removeLast();
}
```

### 易错点

- 递归传 `i` 才是可重复选择；传 `i + 1` 会变成每个元素只能使用一次。
- `sum == target` 收集后直接返回，`sum > target` 直接剪枝。

### 复杂度

- 时间复杂度：与答案数量和目标值相关。
- 递归栈空间：`O(target / minCandidate)`。

---

<a id="h-40"></a>
## H_40 - 组合总和 II

### 核心思路

候选数组有重复值，每个下标最多使用一次。先排序让相同值相邻，再做同层去重；递归传 `i + 1` 保证下标不复用。

### 面试速记

```text
不可重复组合 + 去重：排序后跳过同层相同值，递归传 i + 1，sum 超目标就剪枝。
```

### 当前题解

```java
List<List<Integer>> result = new ArrayList<>();
List<Integer> path = new ArrayList<>();

public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    int[] userd = new int[candidates.length];
    Arrays.sort(candidates);
    backtrack(candidates, target, 0, 0, userd);
    return result;
}

public void backtrack(int[] candidates, int target, int sum, int startIndex, int[] userd) {
    if (sum > target) {
        return;
    }
    if (sum == target) {
        result.add(new ArrayList<>(path));
        return;
    }

    for (int i = startIndex; i < candidates.length; i++) {
        if (i > 0 && candidates[i] == candidates[i - 1] && userd[i - 1] == 0) {
            continue;
        }
        path.add(candidates[i]);
        sum += candidates[i];
        userd[i] = 1;
        backtrack(candidates, target, sum, i + 1, userd);
        path.removeLast();
        sum -= candidates[i];
        userd[i] = 0;
    }
}
```

### 同层去重

当前代码使用 `userd` 标记路径状态：

```java
if (i > 0 && candidates[i] == candidates[i - 1] && userd[i - 1] == 0) {
    continue;
}
```

当前源码去重写法：

```java
if (i > 0 && candidates[i] == candidates[i - 1] && userd[i - 1] == 0) {
    continue;
}
```

### 易错点

- 去重是同层去重，不是删除所有重复元素。
- 排序是去重前提，且递归必须传 `i + 1`。

### 复杂度

- 时间复杂度：与搜索树和结果数量相关，排序为 `O(n log n)`。
- 空间复杂度：`O(n)`，不含结果。

---

<a id="h-46"></a>
## H_46 - 全排列【Hot 100】

### 核心思路

排列关心顺序，每层都要从全部元素中选择一个尚未使用的元素，不能用 `startIndex`；使用 `used[]` 记录哪些下标已在当前路径中。

### 面试速记

```text
排列不用 startIndex：每层从全部元素中选一个未使用元素，used[] 标记选择和回溯恢复。
```

### 当前题解

```java
List<List<Integer>> result = new ArrayList<>();
List<Integer> path = new ArrayList<>();

public List<List<Integer>> permute(int[] nums) {
    dfs(nums, new boolean[nums.length]);
    return result;
}

public void dfs(int[] nums, boolean[] used) {
    if (path.size() == nums.length) {
        result.add(new ArrayList<>(path));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (used[i]) {
            continue;
        }
        path.add(nums[i]);
        used[i] = true;
        dfs(nums, used);
        used[i] = false;
        path.removeLast();
    }
}
```

### 关键代码

```java
for (int i = 0; i < nums.length; i++) {
    if (used[i]) {
        continue;
    }
    path.add(nums[i]);
    used[i] = true;
    dfs(nums, used);
    used[i] = false;
    path.removeLast();
}
```

### 易错点

- `used[i] = false` 和删除 `path` 都必须回溯。
- 当 `path.size() == nums.length` 时，得到一个完整排列。

### 复杂度

- 时间复杂度：`O(n * n!)`。
- 空间复杂度：`O(n)`，不含结果。

---

<a id="h-47"></a>
## H_47 - 全排列 II

### 核心思路

在全排列模板上加入重复元素去重。先排序；如果当前值和前一个值相同，且前一个相同值不在当前路径中，则当前是同层重复分支，应跳过。

### 面试速记

```text
去重全排列：先排序，遇到 nums[i] == nums[i - 1] 且 !used[i - 1] 时跳过同层重复。
```

### 当前题解

```java
List<List<Integer>> result = new ArrayList<>();
List<Integer> path = new ArrayList<>();

public List<List<Integer>> permuteUnique(int[] nums) {
    Arrays.sort(nums);
    dfs(nums, new boolean[nums.length]);
    return result;
}

public void dfs(int[] nums, boolean[] used) {
    if (path.size() == nums.length) {
        result.add(new ArrayList<>(path));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
            continue;
        }
        if (used[i]) {
            continue;
        }
        path.add(nums[i]);
        used[i] = true;
        dfs(nums, used);
        used[i] = false;
        path.removeLast();
    }
}
```

### 去重条件

```java
if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
    continue;
}
```

`!used[i - 1]` 表示前一个相同值未被当前路径使用，二者属于同一层，选择当前值会产生重复排列。

### 易错点

- `used[i - 1]` 为 `true` 时不能跳过，因为这代表两个相同值位于同一条树枝上。
- 先做同层去重和先判断 `used[i]` 都可以，但都必须完整执行。

### 复杂度

- 时间复杂度：`O(n * n!)`，实际会因去重减少。
- 空间复杂度：`O(n)`，不含结果。

---

<a id="h-51"></a>
## H_51 - N 皇后【Hot 100】

### 核心思路

按行放置皇后，每层只处理一行。枚举当前行的列，只有列、左上对角线、右上对角线都没有其他皇后时才能放置。

### 面试速记

```text
棋盘回溯：一层放一行，枚举列并检查列和两条上方对角线，放 Q 后递归下一行，返回时还原为 .。
```

### 当前题解

```java
public List<List<String>> solveNQueens(int n) {
    List<List<String>> result = new ArrayList<>();
    char[][] board = new char[n][n];
    for (int row = 0; row < n; row++) {
        for (int col = 0; col < n; col++) {
            board[row][col] = '.';
        }
    }
    backtrack(0, board, result);
    return result;
}

private void backtrack(int row, char[][] board, List<List<String>> result) {
    if (row == board.length) {
        result.add(buildBoard(board));
        return;
    }

    for (int col = 0; col < board.length; col++) {
        if (!isValid(row, col, board)) {
            continue;
        }
        board[row][col] = 'Q';
        backtrack(row + 1, board, result);
        board[row][col] = '.';
    }
}

private boolean isValid(int row, int col, char[][] board) {
    for (int i = 0; i < row; i++) {
        if (board[i][col] == 'Q') {
            return false;
        }
    }
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
        if (board[i][j] == 'Q') {
            return false;
        }
    }
    for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
        if (board[i][j] == 'Q') {
            return false;
        }
    }
    return true;
}

private List<String> buildBoard(char[][] board) {
    List<String> result = new ArrayList<>();
    for (char[] row : board) {
        result.add(new String(row));
    }
    return result;
}
```

### 关键状态

```text
row：当前处理的行
board：当前棋盘
```

### 终止条件

```java
if (row == board.length) {
    result.add(buildBoard(board));
    return;
}
```

### 易错点

- 按行向下放置时，只需检查当前行上方的列和两条对角线，无需检查下方。
- 收集答案时要把 `char[][]` 转成独立的字符串列表。

### 复杂度

- 时间复杂度：与搜索树和有效解数量相关，常写为 `O(n!)` 量级。
- 空间复杂度：`O(n^2)` 用于棋盘，递归栈为 `O(n)`。

---

<a id="h-77"></a>
## H_77 - 组合与剪枝

### 核心思路

从 `1..n` 中选择 `k` 个数。组合不关心顺序，因此 `startIndex` 保证下一层只能从当前选择之后继续枚举。

### 面试速记

```text
组合用 startIndex：选 i 后下一层从 i + 1 开始；还差 need 个数时，循环上界收缩为 n - need + 1。
```

### 当前题解

```java
List<List<Integer>> result = new ArrayList<>();
List<Integer> path = new ArrayList<>();

public List<List<Integer>> combine(int n, int k) {
    back(n, k, 1);
    return result;
}

public void back(int n, int k, int startIndex) {
    if (path.size() == k) {
        result.add(new ArrayList<>(path));
        return;
    }

    for (int i = startIndex; i <= n; i++) {
        path.add(i);
        back(n, k, i + 1);
        path.removeLast();
    }
}
```

### 剪枝版源码：H_77_剪枝.java

```java
public void back(int n, int k, int startIndex) {
    if (path.size() == k) {
        result.add(new ArrayList<>(path));
        return;
    }

    for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
        path.add(i);
        back(n, k, i + 1);
        path.removeLast();
    }
}
```

### 为什么能剪枝

若当前还需要：

```text
need = k - path.size()
```

那么从当前 `i` 到 `n` 至少要剩下 `need` 个数字；否则后续无论怎样选择都无法填满长度 `k`。

### 复杂度

- 时间复杂度：`O(k * C(n, k))`。
- 空间复杂度：`O(k)`，不含结果。

---

<a id="h-78"></a>
## H_78 - 子集【Hot 100】

### 核心思路

子集没有固定长度，因此每一个递归节点对应一个合法子集，进入递归函数后立即收集当前 `path`。

### 面试速记

```text
子集回溯：每个递归节点都是答案，先收集 path；用 startIndex 向后选，避免 [1,2] 和 [2,1] 重复。
```

### 当前题解代码

```java
List<List<Integer>> result = new ArrayList<>();
List<Integer> path = new ArrayList<>();

public List<List<Integer>> subsets(int[] nums) {
    back(nums, 0);
    return result;
}

public void back(int[] nums, int startIndex) {
    result.add(new ArrayList<>(path));

    for (int i = startIndex; i < nums.length; i++) {
        path.add(nums[i]);
        back(nums, i + 1);
        path.removeLast();
    }
}
```

### 复杂度

- 时间复杂度：`O(n * 2^n)`。
- 空间复杂度：`O(n)`，不含结果。

---

<a id="h-79"></a>
## H_79 - 单词搜索【Hot 100】

### 核心思路

外层枚举每个格子作为起点，DFS 按单词顺序匹配字符。当前路径走过的格子临时标记为 `'#'`，防止重复使用；四个方向搜索结束后恢复原字符。

### 面试速记

```text
网格 DFS：枚举起点，匹配当前字符后原地标记已访问，向四个方向找下一位，返回前恢复格子。
```

### 当前题解

```java
public boolean exist(char[][] board, String word) {
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
            if (board[i][j] == word.charAt(0) && dfs(board, word, i, j, 0)) {
                return true;
            }
        }
    }
    return false;
}

public boolean dfs(char[][] board, String word, int x, int y, int index) {
    if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
        return false;
    }
    if (board[x][y] != word.charAt(index)) {
        return false;
    }
    if (index == word.length() - 1) {
        return true;
    }

    char chara = board[x][y];
    board[x][y] = '#';

    boolean result = dfs(board, word, x + 1, y, index + 1) ||
            dfs(board, word, x - 1, y, index + 1) ||
            dfs(board, word, x, y + 1, index + 1) ||
            dfs(board, word, x, y - 1, index + 1);

    board[x][y] = chara;
    return result;
}
```

### 递归函数含义

```text
dfs(board, word, x, y, index)
表示从坐标 (x, y) 开始，能否匹配 word[index..]。
```

### 回溯流程

```java
char chara = board[x][y];
board[x][y] = '#';

boolean result = dfs(board, word, x + 1, y, index + 1) ||
        dfs(board, word, x - 1, y, index + 1) ||
        dfs(board, word, x, y + 1, index + 1) ||
        dfs(board, word, x, y - 1, index + 1);

board[x][y] = chara;
return result;
```

### 易错点

- 必须先判断越界，再访问 `board[x][y]`。
- 匹配到最后一个字符后直接返回 `true`，无需继续向四周搜索。
- 原地标记必须恢复，否则会影响其他路径和其他起点。

### 复杂度

- 时间复杂度：`O(m * n * 4^L)`，`L` 是单词长度。
- 空间复杂度：`O(L)`，递归深度。

---

<a id="h-131"></a>
## H_131 - 分割回文串【Hot 100】

### 核心思路

枚举当前段的结束位置 `end`。若 `s[start..end]` 是回文，就将该段加入路径，并从 `end + 1` 开始递归切分剩余字符串。

### 面试速记

```text
分割回溯：固定 start，枚举 end；当前段是回文才加入 path，下一层从 end + 1 开始切。
```

### 当前题解

```java
public List<List<String>> partition(String s) {
    List<List<String>> result = new ArrayList<>();
    backtrack(s, 0, new ArrayList<>(), result);
    return result;
}

private void backtrack(String s, int start, List<String> path, List<List<String>> result) {
    if (start == s.length()) {
        result.add(new ArrayList<>(path));
        return;
    }

    for (int end = start; end < s.length(); end++) {
        if (!isPalindrome(s, start, end)) {
            continue;
        }
        path.add(s.substring(start, end + 1));
        backtrack(s, end + 1, path, result);
        path.remove(path.size() - 1);
    }
}

private boolean isPalindrome(String s, int left, int right) {
    while (left < right) {
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        left++;
        right--;
    }
    return true;
}
```

### 关键状态

```text
start：下一段切分起点
end：当前尝试的切分终点
path：已切出的回文段
```

### 终止条件

```java
if (start == s.length()) {
    result.add(new ArrayList<>(path));
    return;
}
```

### 易错点

- `substring(start, end + 1)` 的右边界是开区间。
- 回文判断必须覆盖完整闭区间 `[start, end]`。

### 复杂度

- 时间复杂度：与切分方案数相关，最坏接近 `O(n * 2^n)`。
- 空间复杂度：`O(n)`，不含结果。

---

<a id="h-216"></a>
## H_216 - 组合总和 III

### 核心思路

在 `1..9` 中选出 `k` 个不重复数字，使和为 `n`。这是固定范围、不可重复的组合题，用 `startIndex` 控制向后选择，用 `sum` 剪枝。

### 面试速记

```text
固定范围组合：从 startIndex 到 9 选数，递归传 i + 1；sum > n 剪枝，数量和总和同时满足才收集。
```

### 当前题解

```java
List<List<Integer>> result = new ArrayList<>();
List<Integer> path = new ArrayList<>();

public List<List<Integer>> combinationSum3(int k, int n) {
    backtracking(k, n, 0, 1);
    return result;
}

public void backtracking(int k, int n, int sum, int startIndex) {
    if (sum > n) {
        return;
    }
    if (path.size() == k && sum == n) {
        result.add(new ArrayList<>(path));
        return;
    }

    for (int i = startIndex; i <= 9; i++) {
        path.add(i);
        sum += i;
        backtracking(k, n, sum, i + 1);
        sum -= i;
        path.removeLast();
    }
}
```

### 收集条件

```java
if (path.size() == k && sum == n) {
    result.add(new ArrayList<>(path));
    return;
}
```

### 易错点

- `sum == n` 但数量不等于 `k` 时不能收集。
- 当前实现只对 `sum > n` 剪枝；还可结合剩余数量做进一步边界剪枝。

### 复杂度

- 时间复杂度：候选范围固定为 9，搜索规模有限；与有效组合数量相关。
- 空间复杂度：`O(k)`，不含结果。

---

<a id="h-301"></a>
## H_301 - 删除无效的括号

### 当前状态

`H_301.java` 当前没有实现，因此本节不写入非源码题解；下方仅保留复习策略。

### 核心思路

先扫描字符串，得到必须删除的左、右括号数量：

```text
leftRemove：多余的 '('
rightRemove：多余的 ')'
```

DFS 过程中维护：

```text
index：当前字符下标
left/right：已保留的左右括号数量
leftRemove/rightRemove：还需删除的括号数量
```

只有当：

```text
right <= left
```

时，保留当前右括号才合法。

### 面试速记

```text
最少删除括号：先算多余左右括号数，DFS 决定删或留；保留 ')' 时必须 right < left，同层跳过重复删除。
```

### 当前题解代码

```java
public class H_301 {
}
```

### 易错点

- 只删除最少数量的括号，才能保证结果有效且删除数最少。
- 删除相同连续括号时要做同层去重，避免重复结果。
- 非括号字符必须保留。

### 复杂度

- 时间复杂度：与字符串长度和合法结果数量相关，最坏为指数级。
- 空间复杂度：`O(n)`，不含结果。

---

<a id="h-491"></a>
## H_491 - 递增子序列

### 核心思路

寻找长度至少为 2 的非递减子序列，必须保持原数组相对顺序，因此不能排序。每层递归新建一个 `Set`，只允许当前层的每个数值开启一次分支。

### 面试速记

```text
子序列 + 去重：不能排序；路径保持非递减，每层新建 Set 跳过同层重复，长度至少 2 就收集。
```

### 当前题解

```java
List<List<Integer>> result = new ArrayList<>();
List<Integer> path = new ArrayList<>();

public List<List<Integer>> findSubsequences(int[] nums) {
    dfs(nums, 0);
    return result;
}

public void dfs(int[] nums, int startIndex) {
    if (path.size() > 1) {
        result.add(new ArrayList<>(path));
    }

    Set<Integer> uset = new HashSet<>();
    for (int i = startIndex; i < nums.length; i++) {
        if ((!path.isEmpty() && nums[i] < path.getLast()) || uset.contains(nums[i])) {
            continue;
        }
        path.add(nums[i]);
        uset.add(nums[i]);
        dfs(nums, i + 1);
        path.removeLast();
    }
}
```

### 关键代码

```java
Set<Integer> uset = new HashSet<>();
for (int i = startIndex; i < nums.length; i++) {
    if ((!path.isEmpty() && nums[i] < path.getLast()) || uset.contains(nums[i])) {
        continue;
    }
    path.add(nums[i]);
    uset.add(nums[i]);
    dfs(nums, i + 1);
    path.removeLast();
}
```

### 易错点

- `Set` 必须每一层新建，不能定义为全局 Set；它用于同层去重。
- 不能排序，否则会破坏子序列必须保持原数组顺序的要求。
- 当前路径长度大于等于 2 时就可以收集，但仍要继续向下搜索更长子序列。

### 复杂度

- 时间复杂度：`O(n * 2^n)`。
- 空间复杂度：`O(n)`，不含结果。

---

## 对比记忆

| 题目 | Hot 100 | 核心模板 | 下一层参数 / 状态 | 面试判断点 |
| --- | --- | --- | --- | --- |
| H_17 电话字母组合 | 是 | 按位字符串回溯 | `index + 1` | 一层处理一个数字，枚举该数字映射字母 |
| H_22 括号生成 | 是 | 约束回溯 | `left/right` | 始终保证 `right < left` 后才放右括号 |
| H_39 组合总和 | 是 | 可重复组合 | 递归传 `i` | 当前数允许重复使用，`sum > target` 剪枝 |
| H_40 组合总和 II | 否 | 不可重复组合 + 去重 | 递归传 `i + 1` | 排序后跳过同层重复值 |
| H_46 全排列 | 是 | 排列 | `used[]` | 每层从全部未使用元素中选，不能用 `startIndex` |
| H_47 全排列 II | 否 | 排列 + 去重 | `used[]` | `!used[i - 1]` 表示同层重复，需跳过 |
| H_51 N 皇后 | 是 | 棋盘放置回溯 | `row + 1` | 当前行枚举列，检查列和两条上方对角线 |
| H_77 组合 | 否 | 组合 + 剪枝 | `startIndex = i + 1` | 还差 `need` 个数时，循环上界为 `n - need + 1` |
| H_78 子集 | 是 | 子集回溯 | `startIndex = i + 1` | 每个递归节点都是答案，先收集 `path` |
| H_79 单词搜索 | 是 | 网格 DFS | 四方向 + `index + 1` | 原地标记已访问，返回前恢复格子 |
| H_131 分割回文串 | 是 | 分割回溯 | `start = end + 1` | 当前段是回文才递归后续字符串 |
| H_216 组合总和 III | 否 | 固定范围组合 | `startIndex = i + 1` | 数量和总和同时满足才收集 |
| H_301 删除无效括号 | 否 | 最少删除 + DFS | 删除数、左右括号数 | 保留 `)` 前必须保证右括号数小于左括号数 |
| H_491 递增子序列 | 否 | 子序列 + 同层 Set 去重 | `startIndex = i + 1` | 不能排序，每层新建 Set，路径保持非递减 |

---

## 排列、组合、子集题解模板

### 先判断题型

```text
结果是否关心顺序？
  关心：排列，用 used[]。
  不关心：组合或子集，用 startIndex。

同一个元素能否重复选？
  能：下一层从 i 开始。
  不能：下一层从 i + 1 开始。

输入有重复值且答案不能重复？
  组合/子集：排序后跳过同层重复值。
  排列：排序后跳过“前一个相同值未使用”的情况。
```

### 1. 组合：顺序无关，元素不可重复

适用：H_77、H_216、H_40（加同层去重）。`startIndex` 保证只向后选择，因此 `[1, 2]` 与 `[2, 1]` 不会重复出现。

```java
void dfs(int[] nums, int startIndex) {
    if (满足收集条件) {
        result.add(new ArrayList<>(path));
        return;
    }

    for (int i = startIndex; i < nums.length; i++) {
        path.add(nums[i]);
        dfs(nums, i + 1);
        path.removeLast();
    }
}
```

数组含重复值时，先排序，在循环开头加入同层去重：

```java
if (i > startIndex && nums[i] == nums[i - 1]) {
    continue;
}
```

### 2. 组合：顺序无关，元素可重复使用

适用：H_39。与普通组合唯一的关键差别是递归传 `i`，表示当前元素还可以再次选。

```java
void dfs(int[] nums, int startIndex, int sum, int target) {
    if (sum > target) {
        return;
    }
    if (sum == target) {
        result.add(new ArrayList<>(path));
        return;
    }

    for (int i = startIndex; i < nums.length; i++) {
        path.add(nums[i]);
        dfs(nums, i, sum + nums[i], target);
        path.removeLast();
    }
}
```

### 3. 排列：顺序有关

适用：H_46、H_47。每一层都要从全部元素中挑一个未使用元素，不能使用 `startIndex`。

```java
void dfs(int[] nums, boolean[] used) {
    if (path.size() == nums.length) {
        result.add(new ArrayList<>(path));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (used[i]) {
            continue;
        }
        used[i] = true;
        path.add(nums[i]);
        dfs(nums, used);
        path.removeLast();
        used[i] = false;
    }
}
```

数组有重复值时，先排序，再加入：

```java
if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
    continue;
}
```

`!used[i - 1]` 表示前一个相同值没有在当前路径中使用，也就是当前正在同一层选重复值，应跳过。

### 4. 子集：每个递归节点都是一个答案

适用：H_78。与组合不同，子集不要求固定长度或固定和，因此一进入递归就收集当前 `path`。

```java
void dfs(int[] nums, int startIndex) {
    result.add(new ArrayList<>(path));

    for (int i = startIndex; i < nums.length; i++) {
        path.add(nums[i]);
        dfs(nums, i + 1);
        path.removeLast();
    }
}
```

### 面试速记

```text
排列看顺序：used[]，每层从 0 开始选未使用元素。
组合不看顺序：startIndex，下一层 i + 1；可重复使用则传 i。
子集：每个节点先收集，再从 startIndex 向后选。
去重：组合/子集用 i > startIndex；排列用 !used[i - 1]。
```
