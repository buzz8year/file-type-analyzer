package analyzer.core.impl;

import java.util.Arrays;
import analyzer.Result;
import analyzer.core.Analyzer;

public class KMPAnalyzer implements Analyzer
{
    @Override
    public <T extends Result> boolean analyze(T result)
    {
        int[] indexes = indexesOf(result.getPattern().toCharArray(), result.getBytes());
        return indexes.length > 0;
    }

    public static int[] indexesOf(char[] pattern, byte[] bytes)
    {
        int[] indexes = new int[bytes.length];
        int[] prefixes = prefixOf(pattern);
        int size = 0, k = 0;

        for (int i = 0; i < bytes.length; ++i)
        {
            while (pattern[k] != (char)bytes[i] && k > 0)
                k = prefixes[k - 1];

            if (pattern[k] == (char)bytes[i])
            {
                k = k + 1;
                if (k == pattern.length)
                {
                    indexes[size] = i + 1 - k;
                    size += 1;
                    k = prefixes[k - 1];
                }
            }
            else k = 0;
        }
        return Arrays.copyOfRange(indexes, 0, size);
    }

    public static int[] prefixOf(char[] chars)
    {
        int[] prefixes = new int[chars.length];
        prefixes[0] = 0;

        for (int i = 1; i < chars.length; ++i)
        {
            int k = prefixes[i - 1];

            while (chars[k] != chars[i] && k > 0)
                k = prefixes[k - 1];

            if (chars[k] == chars[i])
                prefixes[i] = k + 1;
            else prefixes[i] = 0;
        }
        return prefixes;
    }
}