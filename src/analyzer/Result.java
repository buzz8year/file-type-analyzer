package analyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Result
{
    private String path, expected, pattern;
    private byte[] bytes;
    private File file;
    private int rank;

    public boolean isMatch;

    Result(String path, String[] pattern) throws IOException
    {
        setRank(Integer.parseInt(pattern[0]));
        setExpected(pattern[2]);
        setPattern(pattern[1]);
        setPath(path);
        setBytes();
        setFile();
    }
    private void setBytes() throws IOException {
        this.bytes = Files.readAllBytes(Path.of(path));
    }
    private void setExpected(String expected) {
        this.expected = expected.replaceAll("\"", "");
    }
    private void setPattern(String pattern) {
        this.pattern = pattern.replaceAll("\"", "");
    }
    private void setPath(String path) {
        this.path = path;
    }
    private void setFile() {
        this.file = new File(path);
    }
    private void setRank(int rank) {
        this.rank = rank;
    }

    //public String getPath() {return path;}
    public int getRank() {
        return rank;
    }
    public File getFile() {
        return file;
    }
    public byte[] getBytes() {
        return bytes;
    }
    public String getPattern() {
        return pattern;
    }
    public String getExpected() {
        return expected;
    }
    public String getUnknown() {
        return "Unknown file type";
    }
}