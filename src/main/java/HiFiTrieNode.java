import java.util.HashMap;
import java.util.Objects;

public final class HiFiTrieNode {
    private final String value;
    private final HashMap<String, HiFiTrieNode> childNodes = new HashMap<>();

    HiFiTrieNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public HashMap<String, HiFiTrieNode> getChildNodes() {
        return childNodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HiFiTrieNode that = (HiFiTrieNode) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "HiFiTrieNode{" +
                "value='" + value + '\'' +
                ", childNodes=" + childNodes +
                '}';
    }
}

