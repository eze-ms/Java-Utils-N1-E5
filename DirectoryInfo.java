import java.io.Serializable;

public class DirectoryInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String state;
    private String[] args;

    public DirectoryInfo(String state, String[] args) {
        this.state = state;
        this.args = args;
    }

    public String getState() {
        return state;
    }

    public String[] getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return "DirectoryInfo[state=" + state + ", args=" + String.join(", ", args) + "]";
    }
}
