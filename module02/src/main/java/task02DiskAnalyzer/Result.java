package task02DiskAnalyzer;

import java.io.File;
import java.util.List;

public class Result {
    private List<File> result;
    private boolean errorOccurs;
    private Exception exception;

    public Result(List<File> result) {
        this.result = result;
    }

    public Result(boolean errorOccurs, Exception exception) {
        this.errorOccurs = errorOccurs;
        this.exception = exception;
    }

    public List<File> get() {
        return result;
    }

    public boolean isErrorOccurs() {
        return errorOccurs;
    }

    public Exception getException() {
        return exception;
    }
}