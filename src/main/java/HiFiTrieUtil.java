import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HiFiTrieUtil {

    private static String[][] data = {
            {"a", "b", "c", "d", "1"},
            {"a", "e", "c", "d", "2"},
            {"a", "f", "c", "d", "3"},
            {"a", "h", "c", "d", "4"},
            {"a", "b", "a", "d", "5"},
            {"a", "b", "v", "d", "6"},
            {"a", "b", "c", "c", "7"},
            {"a", "s", "c", "d", "8"},
            {"c", "b", "c", "d", "9"},
            {"b", "b", "c", "d", "10"},
            {"y", "b", "c", "d", "11"},
            {"o", "b", "c", "d", "12"},
            {"l", "b", "c", "d", "13"},
            {"s", "b", "c", "d", "14"},
            {"a", "k", "c", "d", "15"},
            {"a", "*", "c", "*", "16"},
            {"a", "b", "*", "d", "17"},
            {"*", "*", "*", "*", "18"}
    };

    public static void main(String[] args) {
        HiFiTrieNode root = buildHiFiTrie();
        System.out.println(getValue(root, Arrays.asList("a", "b1", "c", "da")));
    }

    private static HiFiTrieNode buildHiFiTrie() {
        HiFiTrieNode root = new HiFiTrieNode("root");
        HiFiTrieNode prevNode = root;
        for (String[] row : data) {
            for (String value : row) {
                if (!prevNode.getChildNodes().containsKey(value)) {
                    HiFiTrieNode node = new HiFiTrieNode(value);
                    prevNode.getChildNodes().put(value, node);
                }
                prevNode = prevNode.getChildNodes().get(value);
            }
            prevNode = root;
        }
        return root;
    }

    private static String getValue(HiFiTrieNode node, List<String> tokens) {
        if (tokens.isEmpty()) {
            return node.getChildNodes().values().iterator().next().getValue();
        }
        String token = tokens.get(0);
        HiFiTrieNode matchingNode = node.getChildNodes().get(token);
        if (null == matchingNode) {
            matchingNode = node.getChildNodes().get("*");
        }
        if (null == matchingNode) {
            return null;
        }
        List<String> newTokens = tokens.size() == 1 ? new ArrayList<>(0) : tokens.subList(1, tokens.size());
        String value = getValue(matchingNode, newTokens);
        if (null == value) {
            matchingNode = node.getChildNodes().get("*");
            return null == matchingNode ? null : getValue(matchingNode, newTokens);
        }
        return value;
    }
}
