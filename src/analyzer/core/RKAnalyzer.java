package analyzer.core;

import analyzer.Result;

public class RKAnalyzer implements Analyzer {
    @Override
    public <T extends Result> boolean analyze(T result) {
        return getResult(result.getBytes(), result.getPattern());
    }

    public boolean getResult(byte[] bytes, String pattern) {
        long m = 173_961_102_589_771L;
        int a = 117;

        if (pattern.length() > bytes.length)
            return false;

        long patternHash = 0;
        long hash = 0;
        long pow = 1;

        for (int i = 0; i < pattern.length(); i++) {
            patternHash += (long) pattern.charAt(i) * pow;
            patternHash %= m;

            hash += (long) bytes[bytes.length - pattern.length() + i] * pow;
            hash %= m;

            if (i != pattern.length() - 1)
                pow = pow * a % m;
        }
        for (int i = bytes.length; i >= pattern.length(); i--) {
            if (patternHash == hash) {
                for (int j = 0; j < pattern.length(); j++) {
                    if ((char) bytes[i - pattern.length() + j] != pattern.charAt(j))
                        break;
                }
                return true;
            }
            if (i > pattern.length()) {
                hash = (hash - bytes[i - 1] * pow % m + m) * a % m;
                hash = (hash + bytes[i - pattern.length() - 1]) % m;
            }
        }
        return false;
    }
}