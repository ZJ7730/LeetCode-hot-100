import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: suanfa
 * @ClassName: H_17
 * @description: 电话号码的字母组合
 * @author: zhoujie07
 * @create: 2026-04-30
 **/
public class H_17 {

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

    static void main(String[] args) {
        H_17 solution = new H_17();
        String digits = "23";
        List<String> result = solution.letterCombinations(digits);
        System.out.println(result);
    }

}
